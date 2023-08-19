package com.Model;

import javax.validation.constraints.NotEmpty;

public class Job {
	private int id;
	
	@NotEmpty(message = "Job Name should not be empty")
	private String name;
	public Job(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public Job(String name) {
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
