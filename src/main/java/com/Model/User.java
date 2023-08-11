package com.Model;

import java.sql.Date;

public class User {
	private int Id;
	private String F_name;
	private String L_name;
	private String Address;
	private int Contact_01;
	private int Contact_02;
	private Date DOB;
	private int role_id;
	
	public User(int id, String f_name, String l_name, String address, int contact_01, int contact_02, Date dOB,
			int role_id) {
		Id = id;
		F_name = f_name;
		L_name = l_name;
		Address = address;
		Contact_01 = contact_01;
		Contact_02 = contact_02;
		DOB = dOB;
		this.role_id = role_id;
	}
	public User(String f_name, String l_name, String address, int contact_01, int contact_02, Date dOB, int role_id) {
		F_name = f_name;
		L_name = l_name;
		Address = address;
		Contact_01 = contact_01;
		Contact_02 = contact_02;
		DOB = dOB;
		this.role_id = role_id;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getF_name() {
		return F_name;
	}
	public void setF_name(String f_name) {
		F_name = f_name;
	}
	public String getL_name() {
		return L_name;
	}
	public void setL_name(String l_name) {
		L_name = l_name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public int getContact_01() {
		return Contact_01;
	}
	public void setContact_01(int contact_01) {
		Contact_01 = contact_01;
	}
	public int getContact_02() {
		return Contact_02;
	}
	public void setContact_02(int contact_02) {
		Contact_02 = contact_02;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
}
