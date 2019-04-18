package com.techelevator.site;

public class Site {
	
	private Long site_id;
	private Long campground_id;
	private Long site_number;
	private Long max_occupancy;
	private boolean accessible;
	private Long max_rv_length;
	private boolean utilities;
	
	/**
	 * @return the site_id
	 */
	public Long getSite_id() {
		return site_id;
	}
	/**
	 * @param site_id the site_id to set
	 */
	public void setSite_id(Long site_id) {
		this.site_id = site_id;
	}
	/**
	 * @return the campground_id
	 */
	public Long getCampground_id() {
		return campground_id;
	}
	/**
	 * @param campground_id the campground_id to set
	 */
	public void setCampground_id(Long campground_id) {
		this.campground_id = campground_id;
	}
	/**
	 * @return the site_number
	 */
	public Long getSite_number() {
		return site_number;
	}
	/**
	 * @param site_number the site_number to set
	 */
	public void setSite_number(Long site_number) {
		this.site_number = site_number;
	}
	/**
	 * @return the max_occupancy
	 */
	public Long getMax_occupancy() {
		return max_occupancy;
	}
	/**
	 * @param max_occupancy the max_occupancy to set
	 */
	public void setMax_occupancy(Long max_occupancy) {
		this.max_occupancy = max_occupancy;
	}
	/**
	 * @return the accessible
	 */
	public boolean isAccessible() {
		return accessible;
	}
	/**
	 * @param accessible the accessible to set
	 */
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	/**
	 * @return the max_rv_length
	 */
	public Long getMax_rv_length() {
		return max_rv_length;
	}
	/**
	 * @param max_rv_length the max_rv_length to set
	 */
	public void setMax_rv_length(Long max_rv_length) {
		this.max_rv_length = max_rv_length;
	}
	/**
	 * @return the utilities
	 */
	public boolean isUtilities() {
		return utilities;
	}
	/**
	 * @param utilities the utilities to set
	 */
	public void setUtilities(boolean utilities) {
		this.utilities = utilities;
	}
	
	

}
