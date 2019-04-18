package com.techelevator.campground;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface CampgroundDAO {
	
	public List<Campground> getAllCampgrounds();
	
	public Campground mapRowToCampground(SqlRowSet results);
	
	public List<Campground> getCampgroundsByParkId (Long parkId);
	
	public Campground getCampgroundById(Long id);
	
}
