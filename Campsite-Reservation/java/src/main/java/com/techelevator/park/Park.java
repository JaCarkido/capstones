package com.techelevator.park;

import java.time.LocalDate;

public class Park {
	
	private Long park_id;
	private String name;
	private String location;
	private LocalDate establish_date;
	private Long area;
	private Long visitors;
	private String description;
	
	/**
	 * @return the park_id
	 */
	public Long getPark_id() {
		return park_id;
	}
	/**
	 * @param park_id the park_id to set
	 */
	public void setPark_id(Long park_id) {
		this.park_id = park_id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the establish_date
	 */
	public LocalDate getEstablish_date() {
		return establish_date;
	}
	/**
	 * @param establish_date the establish_date to set
	 */
	public void setEstablish_date(LocalDate establish_date) {
		this.establish_date = establish_date;
	}
	/**
	 * @return the area
	 */
	public Long getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(Long area) {
		this.area = area;
	}
	/**
	 * @return the visitors
	 */
	public Long getVisitors() {
		return visitors;
	}
	/**
	 * @param visitors the visitors to set
	 */
	public void setVisitors(Long visitors) {
		this.visitors = visitors;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
