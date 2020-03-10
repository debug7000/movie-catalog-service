package com.microservice.catalog.model;

import java.util.List;

public class UserRating {
	
	private List<Rating> ratings;

	public UserRating() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
	

}
