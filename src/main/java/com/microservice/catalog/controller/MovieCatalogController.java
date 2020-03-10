package com.microservice.catalog.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservice.catalog.model.CatalogItem;
import com.microservice.catalog.model.Movie;
import com.microservice.catalog.model.Rating;
import com.microservice.catalog.model.UserRating;
@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	@Autowired //act as  consumer
	private RestTemplate restTemplate;
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId){
		
		
		
		//get list of ratings
		
		/*List<Rating> ratings = Arrays.asList(
				new Rating("123",4),
				new Rating("124",5)
				);*/
		// why Api should not return list ,it is difficult to map to java object for resttempalate
		// replacing hard coded uRL with service name from eureka
//UserRating usrRating = restTemplate.getForObject("http://localhost:8083/ratings/users/"+userId, UserRating.class);
		UserRating usrRating = restTemplate.getForObject("http://movie-rating-service/ratings/users/"+userId, UserRating.class);
		
		//for each rating and get movie info
		
	return	usrRating.getRatings().stream().map(rating -> {
			
	//Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieID(), Movie.class);
			Movie movie = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/"+rating.getMovieID(), Movie.class);
			
		/*Movie movie = webClientBuilder.build()
		      .get()
		      .uri("http://localhost:8082/movies/"+rating.getMovieID())
		      .retrieve()
		      .bodyToMono(Movie.class)
		      .block(); */
		    return new CatalogItem(movie.getName(),"desc",rating.getRating());
			
		})
			
			.collect(Collectors.toList());
		
		
		
		//return Collections.singletonList(new CatalogItem(5, "radhe", "radhe-krishna"));
		
	}

}
