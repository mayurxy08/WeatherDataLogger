package com.mayurtalole.weatherlogger.service;

import java.util.List;

import com.mayurtalole.weatherlogger.entity.Record;

public interface WeatherService {

	
	public Record storeRecords(Record records);
	public List<Record> findAll();
	public Record findRecord(String location);
	public List<String> findRecordedCities();
	public Record findLatestWeather(String location);
	public String findSelectedWeather(String location,String param);
	public String hourlyAvgTemp(String location);
	public String dailyAvgWeather(String location);
}
