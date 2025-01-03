package com.transportsystem.backend;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.Properties;

@EnableSwagger2
@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
		})
@EntityScan("com.transportsystem.backend.model")
public class BackendApplication {
	private Environment environment;

	@Autowired
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	//@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(environment.getProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		sessionFactory.setPackagesToScan(new String[] { "" });
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	public HikariDataSource createHikariConfig() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
		hikariConfig.setJdbcUrl(environment.getProperty("spring.datasource.url"));
		hikariConfig.setUsername(environment.getProperty("spring.datasource.username"));
		hikariConfig.setPassword(environment.getProperty("spring.datasource.password"));
		return new HikariDataSource(hikariConfig);
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
		properties.put("hibernate.naming.physical-strategy", environment.getRequiredProperty("spring.jpa.hibernate.naming.physical-strategy"));
		properties.put("hibernate.naming.strategy", environment.getRequiredProperty("spring.jpa.hibernate.naming.physical-strategy"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("spring.jpa.show-sql"));
		properties.put("hibernate.ddl-auto", environment.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.id.new_generator_mappings", environment.getRequiredProperty("spring.jpa.hibernate.use-new-id-generator-mappings"));
		properties.put("hibernate.search.default.directory_provider", environment.getRequiredProperty("spring.jpa.properties.hibernate.search.default.directory_provider"));
		properties.put("current_session_context_class", environment.getRequiredProperty("spring.jpa.properties.hibernate.current_session_context_class"));
		properties.put("hibernate.search.default.indexBase", environment.getRequiredProperty("spring.jpa.properties.hibernate.search.default.indexBase"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("spring.jpa.properties.hibernate.format_sql"));
		properties.put("hibernate.use_sql_comments", environment.getRequiredProperty("spring.jpa.properties.hibernate.use_sql_comments"));
		properties.put("hibernate.max_fetch_depth", environment.getRequiredProperty("spring.jpa.properties.hibernate.max_fetch_depth"));
		//properties.put("hibernate.search.default.locking_strategy", environment.getRequiredProperty("hibernate.search.default.locking_strategy"));
		//properties.put("hibernate.search.lucene_version",environment.getRequiredProperty("hibernate.search.lucene_version"));
		return properties;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	@Bean
	public FilterRegistrationBean simpleCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Collections.singletonList("*"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
}
