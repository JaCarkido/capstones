package com.techelevator.site;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.Campground;
import com.techelevator.park.Park;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;



public class JDBCSiteDAO implements SiteDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	
	public JDBCSiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Site> getAllSites() {
		
		List<Site> newSiteList = new ArrayList<Site>();
			
		String getAllSitesStr = "SELECT * FROM Site";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(getAllSitesStr);

		while (results.next()) {
			Site newSite = mapRowToSite(results);
				
			newSiteList.add(newSite);
		}
			
		return newSiteList;
	}
	
	@Override
	public List<Site> getAvailableSites (String arrivalDate, String departureDate, Long campId, Campground selectedCampground) {
		
		
		List<Site> availableSites = new ArrayList<Site>();
		String getAvailableSites = "SELECT site_id "
								 + "FROM site "
								 + "WHERE campground_id = ? AND site_id NOT IN (SELECT site_id "
								 											 + "FROM reservation "
								 											 + "WHERE ( ( to_date BETWEEN ?::date AND ?::date ) "
								 											 + "OR (from_date BETWEEN ?::date AND ?::date ) ) "
								 											 + "GROUP BY site_id) "
								 											 + "LIMIT 5 ";	
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(getAvailableSites, campId, arrivalDate, departureDate, arrivalDate, departureDate);
	
		System.out.printf("Site No.");
		System.out.printf("   Max Occup.");
		System.out.printf("      Accessible?");
		System.out.printf("      Max RV Length");
		System.out.printf("      Utility");
		System.out.printf("      Cost\n");
		System.out.println("----------------------------------------------------------------------------------");
		
			while(results.next()) {
				Site newSite = getSiteById(results.getLong("site_id"));
				
				printSite(newSite, selectedCampground, getLengthOfStay(arrivalDate, departureDate));
			}
		
		
		//printAvailableSites(availableSites, campId, getLengthOfStay(arrivalDate, departureDate));
		
		return availableSites;
	}
	
	public void printSite (Site list, Campground selectedCampground, Long lengthOfStay) { 
		BigDecimal bd = new BigDecimal(0);
		bd = BigDecimal.valueOf(lengthOfStay);
		
		// Not pretty but happy
		System.out.printf(" %-3d", list.getSite_number());
		System.out.printf("         %-3d", list.getMax_occupancy()); 
		
		if(!list.isAccessible()) {
			System.out.printf("             %-10s" , "No");
		}
		else {
			System.out.printf("             %-10s" , "Yes");
		}
		if(list.getMax_rv_length() == 0) {
			System.out.printf("          %-10s" , "N/A");
		}
		else {
			System.out.printf("          %-10s" , list.getMax_rv_length().toString());
		}
		if(!list.isUtilities()) {
			System.out.printf("     %-10s" , "N/A");
		}
		else {
			System.out.printf("     %-10s" , "Yes");
		}													   //************************************
		System.out.printf("%5s\n", bd.multiply(selectedCampground.getDaily_fee()));// <-------------** BigDecimal * lengthOfStay here **  get from campground
															   //************************************
				
	}
	
	/*public void printAvailableSites(List<Site> list, Long campId, Long lengthOfStay) {
		
		BigDecimal bd = new BigDecimal(0);
		bd = BigDecimal.valueOf(lengthOfStay);
		
		
		System.out.println("\nResults Matching Your Search Criteria");
		System.out.println("Site No.    Max Occup.    Accessible?    Max RV Length   Utility   Cost");
		
		for (int i = 0; i < list.size();i++ ) {
		System.out.println(list.get(i).getSite_number() + " " + list.get(i).getMax_occupancy() + " " + list.get(i).isAccessible()
					       + " " + list.get(i).getMax_rv_length() + " " + list.get(i).isUtilities() + " "    //getCost * days
		);																												 
		
		}
		
	}*/
	
	public Long getLengthOfStay(String arrivalDate, String departureDate) {
		String getLengthOfStay = "Select ?::date - ?::date AS daycount FROM reservation GROUP BY daycount";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(getLengthOfStay, departureDate, arrivalDate);
		results.next();
		Long lengthOfStay = results.getLong(1);
		
		return lengthOfStay;
		
	}
	@Override
	public Site getSiteById(Long siteId) {
		Site mappedSite = new Site();
		String getSiteIdString = "Select * FROM site WHERE site_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(getSiteIdString, siteId);
		
		results.next();
		mappedSite = mapRowToSite(results);
		
		return mappedSite;
	}
	
	public Site getSiteByNumber(Long siteNumber) {
		Site mappedSite = new Site();
		String getSiteNumberString = "SELECT * FROM site WHERE site_number = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(getSiteNumberString, siteNumber);
		
		results.next();
		mappedSite = mapRowToSite(results);
		
		return mappedSite;
		
	}
	
	public String printAccessibility(boolean accessibility) {
		
		if (accessibility == true) {
			return "Yes";
		}
		
		else {
			return "False";
		}
	}
	
	public String printMaxRVLength(Long maxRVLength) {
		
		String RVLength = maxRVLength.toString();
		
		if (RVLength.equals("0")) {
			RVLength = "N/A";
			return RVLength;
		}
		
		else {
			return RVLength;
		}
	}
	
	public String printUtility(boolean utility) {
		
		String utilityStatus = "";
		
		if (utility == false) {
			utilityStatus = "N/A";
			return utilityStatus;
		}
		
		else {
			utilityStatus = "Yes";
			return utilityStatus;
		}
	
	}

	@Override
	public Site mapRowToSite(SqlRowSet results) {
		
		Site mappedSite = new Site();
		mappedSite.setSite_id(results.getLong("site_id"));
		mappedSite.setCampground_id(results.getLong("campground_id"));
		mappedSite.setSite_number(results.getLong("site_number"));
		mappedSite.setMax_occupancy(results.getLong("max_occupancy"));
		mappedSite.setAccessible(results.getBoolean("accessible"));
		mappedSite.setMax_rv_length(results.getLong("max_rv_length"));
		mappedSite.setUtilities(results.getBoolean("utilities"));
		
		return mappedSite;
	}

}
