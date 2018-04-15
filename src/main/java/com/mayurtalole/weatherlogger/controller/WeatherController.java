package com.mayurtalole.weatherlogger.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mayurtalole.weatherlogger.constants.URI;
import com.mayurtalole.weatherlogger.entity.Record;
import com.mayurtalole.weatherlogger.service.WeatherService;

@RestController
public class WeatherController {

	private WeatherService service;

	//Constructor based injection
	public WeatherController(WeatherService service) {
		//super();
		this.service = service;
	}
	
	@CrossOrigin
	@RequestMapping(method= RequestMethod.POST, value = URI.RECORDS, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Record storeRecords(@RequestBody Record records ) {
		//method to get data from mocker 
		
		return service.storeRecords(records);
		
	}
	
	
	//public Record findRecord(String location);
	@CrossOrigin
	@RequestMapping(method= RequestMethod.GET, value = URI.LISTCITIES, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<String> findRecordedCities(){
	//method to list all cities in db
		
		return service.findRecordedCities();
		
	}
	
	@CrossOrigin
	@RequestMapping(method= RequestMethod.GET, value = URI.CITY, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Record findLatestWeather(@PathVariable("city") String myCity) {
		//find weather respective to city
		return service.findLatestWeather(myCity);
	}

	@CrossOrigin
	@RequestMapping(method= RequestMethod.GET, value = URI.CITYLISTING, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String findSelectedWeather(@PathVariable("city") String location, @PathVariable("param") String param) {
		//get parameteraized weather for city
		
		return service.findSelectedWeather(location, param);
	}
	
	@CrossOrigin
	@RequestMapping(method= RequestMethod.GET, value = URI.PERHOUR, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String hourlyAvgTemp(@PathVariable("city") String location) {
		//get an hourly weather averagelog for city
		return service.hourlyAvgTemp(location);
	}
	
	@CrossOrigin
	@RequestMapping(method= RequestMethod.GET, value = URI.PERDAY, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String dailyAvgWeather(@PathVariable("city") String location) {
		//method to get daily average update for city
		return service.dailyAvgWeather(location);
	}
	
	
	
	
}
