package com.mayurtalole.weatherlogger.constants;

//constants form records endpoints 
public class URI {

	public static final String RECORDS = "records";
	public static final String CITY = "city/{city}";
	public static final String LISTCITIES = "cities"; 	//listing cities endpoint
	public static final String PERHOUR = "perhour/{city}"; //per hour average endpont 
	public static final String PERDAY = "perday/{city}"; //per day average for city endpoint
	public static final String CITYLISTING = "readings/{city}/{param}";
	
}
