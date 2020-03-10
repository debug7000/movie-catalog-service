package com.microservice.catalog.model;

public class CatalogItem {
	
	private int rating;
	private String name;
	private String desc;
	
	
	public CatalogItem( String name, String desc,int rating) {
		super();
		this.rating = rating;
		this.name = name;
		this.desc = desc;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	

	

}
