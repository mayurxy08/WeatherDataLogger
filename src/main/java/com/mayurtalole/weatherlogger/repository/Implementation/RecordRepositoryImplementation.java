package com.mayurtalole.weatherlogger.repository.Implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.mayurtalole.weatherlogger.entity.Record;
import com.mayurtalole.weatherlogger.repository.RecordRepository;

@Repository
public class RecordRepositoryImplementation implements RecordRepository{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Record storeRecords(Record records) {
		em.persist(records.getWind());
		em.persist(records);
		return records;
	}

	@Override
	public List<Record> findAll() {
		// TODO Auto-generated method stub
		TypedQuery<Record> query = em.createNamedQuery("Record.findAll", Record.class);
		return query.getResultList();
	}

	@Override
	public Record findRecord(String id) {
		// TODO Auto-generated method stub
		return em.find(Record.class, id);
	}

	@Override
	public List<String> findRecordedCities() {
		// TODO Auto-generated method stub
		TypedQuery<String> query = em.createNamedQuery("Record.findRecordedCities", String.class);
		List<String> list = query.getResultList();
		if(list.isEmpty()) {
			return null;
		}
		return list;
	}

	@Override
	public Record findLatestWeather(String location) {
		// TODO Auto-generated method stub
		TypedQuery<Record> query = em.createNamedQuery("Record.findLatestWeather", Record.class);
		query.setParameter("pcity", location.toString());
		List<Record> list = query.getResultList();
		if(list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public String findSelectedWeather(String location, String param) {
		// TODO Auto-generated method stub
		TypedQuery<Record> query = em.createNamedQuery("Record.findLatestWeather", Record.class);
		query.setParameter("pcity", location.toString());
		List<Record> list = query.getResultList();
		Record record = list.get(0);
		String recordValue = null;
		if(list.isEmpty()) {
			return null;
		}
		
		if (!param.isEmpty()) {
			if(param.equals("temperature")){
				recordValue= Double.toString(record.getTemperature());
			}
			else if(param.equals("pressure")){
				recordValue= Double.toString(record.getPressure());
				}
			else if(param.equals("humidity")){
				recordValue= Double.toString(record.getHumidity());
				}
			else if(param.equals("wind")){
				recordValue= Double.toString(record.getWind().getSpeed()) +" "+Double.toString(record.getWind().getDegree());
			}
		
			return recordValue;
		}
		return recordValue;
	}

	@Override
	public String getHourlyAvgTemp(String location) {
		Query query=em.createNamedQuery("Record.hourlyAvgTemp");
		query.setParameter("pcity",location);
		return query.getSingleResult().toString();
		
	}

	@Override
	public String getDailyAvgTemperature(String location) {

		TypedQuery<String> query=em.createNamedQuery("Record.dailyAvgWeather", String.class);
		query.setParameter("pcity",location);
		List<String> list= query.getResultList();
		String report="DEFAULT";
		
		if(list.isEmpty()){
			return null;
		}
		
		for(int i=0;i<list.size();i++){
			if(list.get(i).compareToIgnoreCase("light rain")==0||(list.get(i).compareToIgnoreCase("moderate rain")==0)){
				report="Rainy";
				return report;
			}
		}
		for(int i=0;i<list.size();i++){
			if((list.get(i).compareToIgnoreCase("mist")==0)||(list.get(i).compareToIgnoreCase("fog")==0)){
				report="Cold";
				return report;
			}
		}
		
		report="Sunny";
		return report;
	
		
	}
	
	
	
}
