package com.techelevator.park;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.Campground;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class JDBCParkDAO implements ParkDAO {
	
	private JdbcTemplate jdbcTemplate;
	DecimalFormat df = new DecimalFormat("#,###");
	DateTimeFormatter dtformatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	
	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Park getParkByName(String name) {
		
		String getParkString = "SELECT * FROM park WHERE name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(getParkString, name);
		
		results.next();
		Park newPark = mapRowToPark(results);
		
		return newPark;
	}
	
	@Override
	public void printPark (Park parkName) {
		System.out.println(parkName.getName() + " National Park");
		System.out.println("-------------");
		System.out.printf("Location:            %-10s\n", parkName.getLocation());
		System.out.printf("Established:         %-10s\n", parkName.getEstablish_date().format(dtformatter));
		System.out.printf("Area:                %-10s\n", df.format(parkName.getArea()));
		System.out.printf("Annual Visitors:     %-10s\n", df.format(parkName.getVisitors()));
		System.out.println();
		System.out.println(parkName.getDescription());
		System.out.println();
			
	}
	

	@Override
	public List<Park> getAllParks() {
		List<Park> newParkList = new ArrayList<Park>();
		
		String getAllParksStr = "SELECT * FROM Park ORDER BY name";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(getAllParksStr);

		while (results.next()) {
			Park newPark = mapRowToPark(results);
			
			newParkList.add(newPark);
		}
		
		return newParkList;
	}

	@Override
	public Park mapRowToPark(SqlRowSet results) {
		
		Park mappedPark = new Park();
		mappedPark.setPark_id(results.getLong("park_id"));
		mappedPark.setName(results.getString("name"));
		mappedPark.setLocation(results.getString("location"));
		mappedPark.setEstablish_date(results.getDate("establish_date").toLocalDate());
		mappedPark.setArea(results.getLong("area"));
		mappedPark.setVisitors(results.getLong("visitors"));
		mappedPark.setDescription(results.getString("description"));
		
		return mappedPark;
	}
	
	
	
}
