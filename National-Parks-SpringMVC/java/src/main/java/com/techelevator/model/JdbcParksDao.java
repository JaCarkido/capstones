package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcParksDao implements ParksDao {

	private JdbcTemplate jdbctemplate;
	
	@Autowired
	public JdbcParksDao(DataSource dataSource) {
		this.jdbctemplate = new JdbcTemplate(dataSource);
	}
	
//Park methods
	@Override
	public List<Parks> getAllParks() {
		List<Parks> allParks = new ArrayList<Parks>();
		String sqlSelectAllParks = "SELECT * FROM park";
		SqlRowSet results =jdbctemplate.queryForRowSet(sqlSelectAllParks);
		while(results.next()) {
			Parks aPark = new Parks();
			aPark.setCode(results.getString("parkcode"));
			aPark.setName(results.getString("parkname"));
			aPark.setState(results.getString("state"));
			aPark.setAcreage(results.getInt("acreage"));
			aPark.setElevation(results.getInt("elevationinfeet"));
			aPark.setMiles(results.getDouble("milesoftrail"));
			aPark.setCampsites(results.getInt("numberofcampsites"));
			aPark.setClimate(results.getString("climate"));
			aPark.setFounded(results.getInt("yearfounded"));
			aPark.setVisitors(results.getInt("annualvisitorcount"));
			aPark.setQuote(results.getString("inspirationalquote"));
			aPark.setSource(results.getString("inspirationalquotesource"));
			aPark.setDescription(results.getString("parkdescription"));
			aPark.setFee(results.getInt("entryfee"));
			aPark.setAnimals(results.getInt("numberofanimalspecies"));
			allParks.add(aPark);
		}
		return allParks;
	}
	
	@Override
	public Parks getParkDetails(String parkCode) {
		Parks aPark = new Parks();
		String sqlSelectParkDetails = "SELECT * FROM park WHERE parkcode = ?";
		SqlRowSet results =jdbctemplate.queryForRowSet(sqlSelectParkDetails, parkCode);
		while(results.next()) {
			aPark.setCode(results.getString("parkcode"));
			aPark.setName(results.getString("parkname"));
			aPark.setState(results.getString("state"));
			aPark.setAcreage(results.getInt("acreage"));
			aPark.setElevation(results.getInt("elevationinfeet"));
			aPark.setMiles(results.getDouble("milesoftrail"));
			aPark.setCampsites(results.getInt("numberofcampsites"));
			aPark.setClimate(results.getString("climate"));
			aPark.setFounded(results.getInt("yearfounded"));
			aPark.setVisitors(results.getInt("annualvisitorcount"));
			aPark.setQuote(results.getString("inspirationalquote"));
			aPark.setSource(results.getString("inspirationalquotesource"));
			aPark.setDescription(results.getString("parkdescription"));
			aPark.setFee(results.getInt("entryfee"));
			aPark.setAnimals(results.getInt("numberofanimalspecies"));
		}
		return aPark;
	}
	
//Weather methods
	
	@Override
	public List<Weather> nextFour(String parkCode) {
		List<Weather> fourDayWeather = new ArrayList<Weather>();
		String sqlSelectFourWeathers = "SELECT * FROM weather WHERE parkcode = ? AND fivedayforecastvalue != 1";
		SqlRowSet results =jdbctemplate.queryForRowSet(sqlSelectFourWeathers, parkCode);
		while(results.next()) {
			Weather day = new Weather();
			day.setCode(results.getString("parkcode"));
			day.setLow(results.getInt("low"));
			day.setHigh(results.getInt("high"));
			day.setForecast(results.getString("forecast"));
			day.setDay(results.getInt("fivedayforecastvalue"));
			fourDayWeather.add(day);
		}
		return fourDayWeather;
	}
	
	@Override
	public Weather getTodayWeather(String parkCode) {
		Weather today = new Weather();
		String sqlSelectWeather = "SELECT * FROM weather WHERE parkcode = ? AND fivedayforecastvalue = 1";
		SqlRowSet results =jdbctemplate.queryForRowSet(sqlSelectWeather, parkCode);
		while(results.next()) {
			today.setCode(results.getString("parkcode"));
			today.setLow(results.getInt("low"));
			today.setHigh(results.getInt("high"));
			today.setForecast(results.getString("forecast"));
			today.setDay(results.getInt("fivedayforecastvalue"));
		}
		return today;
	}
	
	@Override
	public List<Weather> nextFourCelcius(String parkCode) {
		List<Weather> fourDayWeather = new ArrayList<Weather>();
		String sqlSelectFourWeathers = "SELECT * FROM weather WHERE parkcode = ? AND fivedayforecastvalue != 1";
		SqlRowSet results =jdbctemplate.queryForRowSet(sqlSelectFourWeathers, parkCode);
		while(results.next()) {
			Weather day = new Weather();
			day.setCode(results.getString("parkcode"));
			day.setLow(convertTempToCelcius(results.getInt("low")));
			day.setHigh(convertTempToCelcius(results.getInt("high")));
			day.setForecast(results.getString("forecast"));
			day.setDay(results.getInt("fivedayforecastvalue"));
			fourDayWeather.add(day);
		}
		return fourDayWeather;
	}
	
	@Override
	public Weather getTodayWeatherCelcius(String parkCode) {
		Weather today = new Weather();
		String sqlSelectWeather = "SELECT * FROM weather WHERE parkcode = ? AND fivedayforecastvalue = 1";
		SqlRowSet results =jdbctemplate.queryForRowSet(sqlSelectWeather, parkCode);
		while(results.next()) {
			today.setCode(results.getString("parkcode"));
			today.setLow(convertTempToCelcius(results.getInt("low")));
			today.setHigh(convertTempToCelcius(results.getInt("high")));
			today.setForecast(results.getString("forecast"));
			today.setDay(results.getInt("fivedayforecastvalue"));
		}
		return today;
	}
	
	@Override
		public int convertTempToCelcius(int tempFahrenheit) {
			int tempCelcius = (tempFahrenheit - 32) * (5/9);
		return tempCelcius;
}
	
	
	@Override
	public void save(Survey survey) {
		Long id = getNextId();
		String sqlInsertSurvey = "INSERT INTO survey_result(surveyid, parkcode, emailaddress, state, activitylevel) VALUES (?,?,?,?,?)";
		jdbctemplate.update(sqlInsertSurvey, id, survey.getPark(), survey.getEmail(), survey.getState(), survey.getLevel());
		survey.setId(id);
	}
	
	private Long getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_surveyid')";
		SqlRowSet results = jdbctemplate.queryForRowSet(sqlSelectNextId);
		Long id = null;
		if(results.next()) {
			id = results.getLong(1);
		} else {
			throw new RuntimeException("ERROR, wrong next ID.");
		}
		return id;
	}
	
	@Override
	public List<Parks> getFavoriteParks() {
		List<Parks> favoriteParks = new ArrayList<Parks>();
		String sqlSelectFavoriteParks = "SELECT COUNT (survey_result.parkcode) AS count, parkname, park.parkcode AS parkcode FROM survey_result INNER JOIN park ON park.parkcode = survey_result.parkcode GROUP BY parkname, park.parkcode ORDER BY COUNT DESC, parkname";
		SqlRowSet results =jdbctemplate.queryForRowSet(sqlSelectFavoriteParks);
		while(results.next()) {
			Parks surveyedPark = new Parks();
			surveyedPark.setCode(results.getString("parkcode"));
			surveyedPark.setCount(results.getInt("count"));
			surveyedPark.setName(results.getString("parkname"));
			favoriteParks.add(surveyedPark);
		}
		return favoriteParks;
	}
	
}
