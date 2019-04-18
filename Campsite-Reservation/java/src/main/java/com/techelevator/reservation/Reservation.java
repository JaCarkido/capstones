package com.techelevator.reservation;

import java.time.LocalDate;

public class Reservation {
	
	private Long reservation_id;
	private Long site_id;
	private String name;
	private LocalDate from_date;
	private LocalDate to_date;
	private LocalDate create_date;
	
	/**
	 * @return the reservation_id
	 */
	public Long getReservation_id() {
		return reservation_id;
	}
	/**
	 * @param reservation_id the reservation_id to set
	 */
	public void setReservation_id(Long reservation_id) {
		this.reservation_id = reservation_id;
	}
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
	 * @return the from_date
	 */
	public LocalDate getFrom_date() {
		return from_date;
	}
	/**
	 * @param from_date the from_date to set
	 */
	public void setFrom_date(LocalDate from_date) {
		this.from_date = from_date;
	}
	/**
	 * @return the to_date
	 */
	public LocalDate getTo_date() {
		return to_date;
	}
	/**
	 * @param to_date the to_date to set
	 */
	public void setTo_date(LocalDate to_date) {
		this.to_date = to_date;
	}
	/**
	 * @return the create_date
	 */
	public LocalDate getCreate_date() {
		return create_date;
	}
	/**
	 * @param create_date the create_date to set
	 */
	public void setCreate_date(LocalDate create_date) {
		this.create_date = create_date;
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
