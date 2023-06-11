package com.database.implementation.config;


import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "teacherEntityManagerFactory",
		basePackages 	 = {"com.database.implementation.teacherRepository"},
		transactionManagerRef = "teacherTransactionManager"
		)

public class TeacherDatabaseConfig {
	
	@Autowired
	Environment environment;

	@Primary
	@Bean(name= "teacherDataSource")
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl(environment.getProperty("teacher.datasource.url"));
		ds.setUsername(environment.getProperty("teacher.datasource.username"));
		ds.setPassword(environment.getProperty("teacher.datasource.password"));
		ds.setDriverClassName(environment.getProperty("teacher.datasource.driver-class-name"));
		return ds;
	}
	
	@Primary
	@Bean(name= "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManager() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource());
		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		bean.setJpaVendorAdapter(adapter);
		HashMap<String,Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		bean.setJpaPropertyMap(properties);
		bean.setPackagesToScan("com.database.implementation.TeacherEntity");
		return bean;
	}
	
	@Primary
	@Bean("transactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory ) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
}

