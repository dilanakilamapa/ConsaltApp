package com.Model;

import javax.validation.constraints.NotEmpty;

public class Country {
	private int id;
	
	@NotEmpty(message = "Country Name should not be empty")
	private String name;
	public Country(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public Country(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
