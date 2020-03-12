package com.microservice.catalog.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.catalog.model.Rating;
import com.microservice.catalog.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
@Service
@EnableHystrix
public class UserRatingService {
	

	@Autowired //act as  consumer
	private RestTemplate restTemplate;
	
	@HystrixCommand(
			fallbackMethod = "getUserRatingFallback",
			threadPoolKey = "movieRatingPool",
			threadPoolProperties = {
					@HystrixProperty(name="coreSize",value ="20"),
					@HystrixProperty(name="maxQueueSize",value ="10"),
					
					
			})
	public UserRating getUserRating(String userId) {
		
		return restTemplate.getForObject("http://movie-rating-service/ratings/users/"+userId, UserRating.class);
	}
	
	
	public UserRating getUserRatingFallback(String userId) {
		
		UserRating userRating = new UserRating();
		userRating.setRatings(Arrays.asList(new Rating("500" ,0)));
		
		return userRating;
		
		
	}

}
