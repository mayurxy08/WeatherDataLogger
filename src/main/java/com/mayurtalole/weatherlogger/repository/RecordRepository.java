package com.mayurtalole.weatherlogger.repository;

import java.util.List;

import com.mayurtalole.weatherlogger.entity.Record;

public interface RecordRepository {

	public Record storeRecords(Record records);
	public List<Record> findAll();
	public Record findRecord(String Location);
	public List<String> findRecordedCities();
	public Record findLatestWeather(String Location);
	public String findSelectedWeather(String location, String param);
	public String getHourlyAvgTemp(String location);
	public String getDailyAvgTemperature(String location);
	
}
