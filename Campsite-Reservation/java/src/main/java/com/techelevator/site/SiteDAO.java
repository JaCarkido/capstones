package com.techelevator.site;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.Campground;


public interface SiteDAO {

	public List<Site> getAllSites();
	public Site mapRowToSite(SqlRowSet results);
	public List<Site> getAvailableSites (String arrivalDate, String departureDate, Long campId, Campground selectedCampground);
	public Site getSiteById(Long siteId);
	
}
