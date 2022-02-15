package net.koreate.test_java.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class RootConfig {
	
	@Bean("ds")
	public DataSource dataSource() {
		DriverManagerDataSource ds 
				= new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/smartWeb");
		ds.setUsername("smartWeb");
		ds.setPassword("12345");
		return ds;
	}
	
}








