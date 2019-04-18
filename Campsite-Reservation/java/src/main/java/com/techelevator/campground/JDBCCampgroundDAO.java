package com.techelevator.campground;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.park.Park;
import com.techelevator.site.Site;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class JDBCCampgroundDAO implements CampgroundDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCCampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Campground> getAllCampgrounds() {
		List<Campground> newCampgroundList = new ArrayList<Campground>();
		
		String getAllCampgroundsStr = "SELECT * FROM campground";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(getAllCampgroundsStr);

		while (results.next()) {
			
			
			Campground newCampground = mapRowToCampground(results);
			
			newCampgroundList.add(newCampground);
		}
		
		return newCampgroundList;
	}
	
	@Override
	public List<Campground> getCampgroundsByParkId (Long parkId){
		
		List<Campground> newCampgroundList = new ArrayList<Campground>();
			
			String getAllCampgroundsStr = "SELECT * FROM campground WHERE park_id = ? ORDER BY name";
		
			SqlRowSet results = jdbcTemplate.queryForRowSet(getAllCampgroundsStr, parkId);

			while (results.next()) {
				Campground newCampground = mapRowToCampground(results);
			
				newCampgroundList.add(newCampground);
			}
		
			return newCampgroundList;
		
	}
	
	public String getMonth(String mm) {
	return "returning wrong getMonth -- campground";
	
	}
	
	@Override
	public Campground getCampgroundById(Long id) {
		
		String getCampgroundStr = "SELECT * FROM campground WHERE campground_id = ?";
	
		SqlRowSet results = jdbcTemplate.queryForRowSet(getCampgroundStr, id);

		results.next();
		Campground newCampground = mapRowToCampground(results);
		
		return newCampground;
	}
	
	
	@Override
	public Campground mapRowToCampground(SqlRowSet results) {
		
		Campground mappedCampground = new Campground();
		mappedCampground.setCampground_id(results.getLong("campground_id"));
		mappedCampground.setPark_id(results.getLong("park_id"));
		mappedCampground.setName(results.getString("name"));
		mappedCampground.setOpen_from_mm(results.getString("open_from_mm"));
		mappedCampground.setOpen_to_mm(results.getString("open_to_mm"));
		mappedCampground.setDaily_fee(results.getBigDecimal("daily_fee"));
		
		return mappedCampground;
	}
	
	

}
