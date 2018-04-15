package com.mayurtalole.weatherlogger.service.implementation;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mayurtalole.weatherlogger.entity.Record;
import com.mayurtalole.weatherlogger.exception.NotFoundException;
import com.mayurtalole.weatherlogger.repository.RecordRepository;
import com.mayurtalole.weatherlogger.service.*;
@Service
public class WeatherServiceImplementation implements WeatherService {

	
	//constructor based injection
	private RecordRepository repository;
	public WeatherServiceImplementation(RecordRepository repository){
		this.repository=repository;
	}

	@Override
	@Transactional
	
	//get data from mocker api
	public Record storeRecords(Record records) {
		// TODO Auto-generated method stub
		return repository.storeRecords(records);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Record> findAll() {
		// TODO Auto-generated method stub
		List<Record> list =repository.findAll();
		if(list==null) {
			throw new NotFoundException("no record found");
			
		}
		return list;
	}

	@Override
	@Transactional(readOnly=true)
	public Record findRecord(String location) {
		Record record = repository.findRecord(location);
		if(record == null) {
			throw new NotFoundException("no record exists");
		}
		return record;
	}

	@Override
	@Transactional(readOnly=true)
	public Record findLatestWeather(String location) {
Record record=repository.findLatestWeather(location);
		
		if(record==null){
			//throw runtime exception to return ERROR 404: No such city is recorded!
			throw new NotFoundException("User requested location: " + location + " is not in records");
		}
		return record;
		
	}

	@Override
	@Transactional(readOnly=true)
	public String findSelectedWeather(String location, String param) {
		
		String record=repository.findSelectedWeather(location, param);
		
		if(record==null){
				//throw runtime exception to return ERROR 404: No such city is recorded!
				throw new NotFoundException("User requested location: " + location + " is not in records");
			}
			
		return record;
	}

	@Override
	@Transactional(readOnly=true)
	public String hourlyAvgTemp(String location) {
		// TODO Auto-generated method stub
		return repository.getDailyAvgTemperature(location);
	}

	@Override
	@Transactional(readOnly=true)
	public String dailyAvgWeather(String location) {

		String record=repository.getDailyAvgTemperature(location);
		
		if(record==null){
				//throw runtime exception to return ERROR 404: No such city is recorded!
				throw new NotFoundException("User requested location: " + location + " is not in records");
			}
			
		return record;
	}

	@Override
	@Transactional(readOnly=true)
	public List<String> findRecordedCities() {
		List<String> listCities=repository.findRecordedCities();
		return listCities;
	}

}
