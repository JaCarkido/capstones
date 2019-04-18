package com.techelevator.model;

import java.util.List;

public interface ParksDao {
	
//Park methods
	public List <Parks> getAllParks();
	public Parks getParkDetails(String parkCode);
	
//Weather methods
	public List<Weather> nextFour(String parkCode);
	public Weather getTodayWeather(String parkCode);
	public List<Weather> nextFourCelcius(String parkCode);
	public Weather getTodayWeatherCelcius(String parkCode);
	public int convertTempToCelcius(int tempFahrenheit);

	
//Survey methods
	public void save(Survey survey);
	public List<Parks> getFavoriteParks();
	
}
