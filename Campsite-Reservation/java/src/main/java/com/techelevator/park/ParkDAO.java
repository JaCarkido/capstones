package com.techelevator.park;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.Campground;

public interface ParkDAO {

	public List<Park> getAllParks();
	public Park mapRowToPark(SqlRowSet results);
	public Park getParkByName(String name);
	public void printPark (Park parkName);
	
	
}
