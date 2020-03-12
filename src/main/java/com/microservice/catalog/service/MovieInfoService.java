package com.microservice.catalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.catalog.model.Movie;
import com.microservice.catalog.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
@Service
@EnableHystrix
public class MovieInfoService {
	
	

	@Autowired //act as  consumer
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getMovieInfoFallback",
					
					threadPoolKey = "movieInfoPool",
					threadPoolProperties = {
							@HystrixProperty(name="coreSize",value ="20"),
							@HystrixProperty(name="maxQueueSize",value ="10"),
													
					})
	public Movie getMovieInfo(Rating rating) {
	
	return restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/"+rating.getMovieID(), Movie.class);

}
	
	
	public Movie getMovieInfoFallback(Rating rating) {
		
		return new Movie("0", "no movie", "no desc");
		
	}
	
}
