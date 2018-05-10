package com.htc.spring4.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.htc.spring4.dao.EmployeeDAO;
import com.htc.spring4.dao.EmployeeDAOImpl;

@Configuration
public class JdbcConfig {
	
	@Bean(name="dataSource")
	public DataSource getDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@172.16.50.20:1521:orcl11g");
		dataSource.setUsername("trng11g");
		dataSource.setPassword("trng$11g");
		return dataSource;
	}
	
	@Bean(name="jdbcTemplate")
	public JdbcTemplate getJdbcTemplate(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate;
	}
	
 
	@Bean(name="dao")
	public EmployeeDAO getEmployeeDAO(){
		EmployeeDAOImpl dao = new EmployeeDAOImpl();
		dao.setJdbcTemplate(getJdbcTemplate());
		return dao;
	}
	
}
