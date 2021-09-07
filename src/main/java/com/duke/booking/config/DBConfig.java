package com.duke.booking.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
@PropertySource(value="classpath:application.properties")
public class DBConfig implements TransactionManagementConfigurer {
	private final Environment environment;
	
	@Autowired
	public DBConfig(Environment environment) {
		this.environment = environment;
	}
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName(setEnvironment("datasource.mysql.driver"));
	    dataSource.setUrl(setEnvironment("datasource.mysql.url"));
	    dataSource.setUsername(setEnvironment("datasource.mysql.user"));
	    dataSource.setPassword(setEnvironment("datasource.mysql.password"));
	    return dataSource;
	}
	
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	
	@Override
	public TransactionManager annotationDrivenTransactionManager() {
		return transactionManager();
	}
	
	private String setEnvironment(String before) {
	    String key = before + "." + environment.getProperty("environment");
	    return environment.getProperty(key);
	}

	
	
	
}
