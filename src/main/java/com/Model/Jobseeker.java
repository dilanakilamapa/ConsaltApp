package com.Model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;

public class Jobseeker {
	
	private int JobSeekers_ID;
	
	@NotBlank(message = "First name should not be blank")
	private String First_Name;
	
	 @NotBlank(message = "Last name should not be blank")
	private String Last_Name;
	 
	 @NotBlank(message = "Email should not be blank")
	 @Email(message = "Invalid email format")
	private String Email;
	 
	 @NotNull(message = "Phone number should not be null")
	private int Phone_Number;
	
	public Jobseeker(int jobSeekers_ID, String first_Name, String last_Name, String email, int phone_Number) {
		JobSeekers_ID = jobSeekers_ID;
		First_Name = first_Name;
		Last_Name = last_Name;
		Email = email;
		Phone_Number = phone_Number;
	}
	public Jobseeker(String first_Name, String last_Name, String email, int phone_Number) {
		First_Name = first_Name;
		Last_Name = last_Name;
		Email = email;
		Phone_Number = phone_Number;
	}
	public int getJobSeekers_ID() {
		return JobSeekers_ID;
	}
	public void setJobSeekers_ID(int jobSeekers_ID) {
		JobSeekers_ID = jobSeekers_ID;
	}
	public String getFirst_Name() {
		return First_Name;
	}
	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}
	public String getLast_Name() {
		return Last_Name;
	}
	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getPhone_Number() {
		return Phone_Number;
	}
	public void setPhone_Number(int phone_Number) {
		Phone_Number = phone_Number;
	}
}
