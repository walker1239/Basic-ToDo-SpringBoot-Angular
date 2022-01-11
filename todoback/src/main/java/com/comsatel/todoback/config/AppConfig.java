package com.comsatel.todoback.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
 
@Configuration
@MapperScan("com.tutorialMyBatis.Repository")
public class AppConfig {
 
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource sdds = new SimpleDriverDataSource();
		sdds.setDriverClass(com.mysql.jdbc.Driver.class);
		sdds.setUrl("jdbc:mysql://127.0.0.1:3306/comsatel");
		sdds.setUsername("root");
		sdds.setPassword("");
		return sdds;
	}
 
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
 
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		return sessionFactory;
	}
}