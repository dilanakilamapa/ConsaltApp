package com.Model;

import javax.validation.constraints.NotBlank;

public class Role {
	
	private int id;
	
	@NotBlank(message = "Role name should not be blank")
	private String Role_name;
	
	public Role(int id, String role_name) {
		this.id = id;
		Role_name = role_name;
	}
	
	public Role(String role_name) {
		Role_name = role_name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole_name() {
		return Role_name;
	}
	public void setRole_name(String role_name) {
		Role_name = role_name;
	}
	
	

}
