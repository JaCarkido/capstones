package com.techelevator.reservation;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface ReservationDAO {

	public List<Reservation> getAllReservations();
	public Reservation mapRowToReservation(SqlRowSet results);
	public void createReservation (Long siteId,String reservationName, String arrivalDate, String departureDate);

	
}
