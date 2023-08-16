package com.Model;

public class specialization {
	private int id;
	private int User_ID;
	private String User_F_Name;
	private String User_L_Name;
	private int Country_id;
	private String Country_Name;
	private int Job_Title_id;
	private String Job_Name;
	
	public specialization(int user_ID, String user_F_Name, String user_L_Name) {
		User_ID = user_ID;
		User_F_Name = user_F_Name;
		User_L_Name = user_L_Name;
	}

	public specialization(int country_id, String country_Name) {
		Country_id = country_id;
		Country_Name = country_Name;
	}
	
	public specialization(String job_Name, int job_Title_id) {
		Job_Title_id = job_Title_id;
		Job_Name = job_Name;
	}

	public specialization(int id, int user_ID, String user_F_Name, String user_L_Name, int country_id,
			String country_Name, int job_Title_id, String job_Name) {
		this.id = id;
		User_ID = user_ID;
		User_F_Name = user_F_Name;
		User_L_Name = user_L_Name;
		Country_id = country_id;
		Country_Name = country_Name;
		Job_Title_id = job_Title_id;
		Job_Name = job_Name;
	}

	public specialization(int id, int user_ID, int country_id, int job_Title_id) {
		this.id = id;
		User_ID = user_ID;
		Country_id = country_id;
		Job_Title_id = job_Title_id;
	}
	
	public specialization(int user_ID, int country_id, int job_Title_id) {
		User_ID = user_ID;
		Country_id = country_id;
		Job_Title_id = job_Title_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_ID() {
		return User_ID;
	}
	public void setUser_ID(int user_ID) {
		User_ID = user_ID;
	}
	public int getCountry_id() {
		return Country_id;
	}
	public void setCountry_id(int country_id) {
		Country_id = country_id;
	}
	public int getJob_Title_id() {
		return Job_Title_id;
	}
	public void setJob_Title_id(int job_Title_id) {
		Job_Title_id = job_Title_id;
	}

	public String getUser_F_Name() {
		return User_F_Name;
	}

	public void setUser_F_Name(String user_F_Name) {
		User_F_Name = user_F_Name;
	}

	public String getUser_L_Name() {
		return User_L_Name;
	}

	public void setUser_L_Name(String user_L_Name) {
		User_L_Name = user_L_Name;
	}

	public String getCountry_Name() {
		return Country_Name;
	}

	public void setCountry_Name(String country_Name) {
		Country_Name = country_Name;
	}

	public String getJob_Name() {
		return Job_Name;
	}

	public void setJob_Name(String job_Name) {
		Job_Name = job_Name;
	}
	
	

}
