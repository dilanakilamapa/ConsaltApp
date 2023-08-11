package com.Model;

public class Login {
	private int id;
	private int emp_id;
	private String UserName;
	private String Password;
	
	public Login(int id, int emp_id, String userName, String password) {
		this.id = id;
		this.emp_id = emp_id;
		UserName = userName;
		Password = password;
	}

	public Login(int emp_id, String userName, String password) {
		this.emp_id = emp_id;
		UserName = userName;
		Password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
}
