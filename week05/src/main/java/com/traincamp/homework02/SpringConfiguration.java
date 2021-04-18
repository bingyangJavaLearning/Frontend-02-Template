package com.traincamp.homework02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {
	
	@Bean
	public ConfigurationBean configurationBean() {
		return new ConfigurationBean();
	}

}
