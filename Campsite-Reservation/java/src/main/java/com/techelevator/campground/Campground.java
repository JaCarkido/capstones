package com.techelevator.campground;

import java.math.BigDecimal;

public class Campground {
	
	private Long campground_id;
	private Long park_id;
	private String name;
	private String open_from_mm;
	private String open_to_mm;
	private BigDecimal daily_fee;
	
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
	 * @return the open_from_mm
	 */
	public String getOpen_from_mm() {
		return open_from_mm;
	}
	/**
	 * @param open_from_mm the open_from_mm to set
	 */
	public void setOpen_from_mm(String open_from_mm) {
		this.open_from_mm = open_from_mm;
	}
	/**
	 * @return the open_to_mm
	 */
	public String getOpen_to_mm() {
		return open_to_mm;
	}
	/**
	 * @param open_to_mm the open_to_mm to set
	 */
	public void setOpen_to_mm(String open_to_mm) {
		this.open_to_mm = open_to_mm;
	}
	/**
	 * @return the daily_fee
	 */
	public BigDecimal getDaily_fee() {
		return daily_fee;
	}
	/**
	 * @param daily_fee the daily_fee to set
	 */
	public void setDaily_fee(BigDecimal daily_fee) {
		this.daily_fee = daily_fee;
	}
	
	public String getMonth(String mm) {
		
		String month = "";
		
		switch (mm) {
		
		case "01": month = "January";
				   break;
		case "02": month = "February";
		 		   break;
		case "03": month = "March";
				   break;
		case "04": month = "April";
		 		   break;
		case "05": month = "May";
		 		   break;
		case "06": month = "June";
		 		   break;
		case "07": month = "July";
		 		   break;
		case "08": month = "August";
		 		   break;
		case "09": month = "September";
		 		   break;
		case "10": month = "October";
		 		   break;
		case "11": month = "November";
		 		   break;
		case "12": month = "December";
		 		   break;
		 
		
		}
		return month;
	}


}
