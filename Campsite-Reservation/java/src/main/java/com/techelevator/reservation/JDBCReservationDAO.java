package com.techelevator.reservation;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.park.Park;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class JDBCReservationDAO implements ReservationDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Reservation> getAllReservations() {
	List<Reservation> newReservationList = new ArrayList<Reservation>();
		
		String getAllReservationsStr = "SELECT * FROM Reservation";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(getAllReservationsStr);

		while (results.next()) {
			Reservation newReservation = mapRowToReservation(results);
			
			newReservationList.add(newReservation);
		}
		
		return newReservationList;
	}
	@Override
	public void createReservation (Long siteId,String reservationName, String arrivalDate, String departureDate) {
		String createReservation = "INSERT INTO reservation (site_id, name, from_date, to_date, create_date) VALUES ( ?, ?, ?::DATE, ?::DATE, CURRENT_DATE)";
		
		jdbcTemplate.update(createReservation, siteId,reservationName, arrivalDate, departureDate );
		
		
	}
	
	public String getMonth(String mm) {
		return "returning wrong getMonth -- reservation";
	}

	@Override
	public Reservation mapRowToReservation(SqlRowSet results) {
		Reservation mappedReservation = new Reservation();
		
		mappedReservation.setReservation_id(results.getLong("reservation_id"));
		mappedReservation.setSite_id(results.getLong("site_id"));
		mappedReservation.setName(results.getString("name"));
		mappedReservation.setFrom_date(results.getDate("from_date").toLocalDate());
		mappedReservation.setTo_date(results.getDate("to_date").toLocalDate());
		mappedReservation.setCreate_date(results.getDate("create_date").toLocalDate());
		
		return null;
	}

}
