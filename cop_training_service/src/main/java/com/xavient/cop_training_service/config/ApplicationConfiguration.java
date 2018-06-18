package com.xavient.cop_training_service.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("com.xavient")
public class ApplicationConfiguration {

	@Bean
	@ConfigurationProperties("sql.db")
	public BasicDataSource dataSourceMySql(){
		
		return new BasicDataSource();
	}
	
	@Bean(name = "jdbcTemplate")
	public JdbcTemplate configureJdbcTemplate(BasicDataSource basicDataSource){
		
		return new JdbcTemplate(basicDataSource);
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
	
}
