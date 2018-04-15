package com.mayurtalole.weatherlogger;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class JPAConfig {
	
	@Autowired
	private Environment env;
	
//jpa mysql db persistence xml file	
	@Bean
	public LocalContainerEntityManagerFactoryBean emf(){
		LocalContainerEntityManagerFactoryBean emf=new LocalContainerEntityManagerFactoryBean();
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setDataSource(getDataSource());
		emf.setPackagesToScan("com.mayurtalole.weatherlogger"); 
		emf.setJpaProperties(getJPAProperties());
		return emf;
	}

	//Setting db connection
	@Bean
	public DataSource getDataSource(){
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/weather-db?useLegacyDatetimeCode=false");
		ds.setUsername("root");
		ds.setPassword("root");
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager transactionMgr(EntityManagerFactory emf){
		return new JpaTransactionManager(emf);
	}
	
	private Properties getJPAProperties(){
		Properties property=new Properties();
		property.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		property.setProperty("hibernate.hbm2ddl.auto", "create");
		property.setProperty("hibernate.show_sql", "true");
		property.setProperty("hibernate.format_sql", "true");
		return property;
	}
	
	
	
	
}
