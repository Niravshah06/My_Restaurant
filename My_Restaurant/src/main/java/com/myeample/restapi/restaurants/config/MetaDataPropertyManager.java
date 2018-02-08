package com.myeample.restapi.restaurants.config;

import org.springframework.beans.factory.annotation.Value;
/**
 * class for getting data from properties file
 */
import org.springframework.stereotype.Component;

import lombok.Getter;
@Component
@Getter
public class MetaDataPropertyManager {

	
	@Value("${spring.redis.host}")
	private String redisHost;
	
	@Value("${spring.redis.port}")
	private Integer redisPort;
	
	
}
