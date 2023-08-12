package com.Model;

import java.sql.Date;
import java.sql.Time;

public class ConsultantAvailability {
	private int ID;
	private int User_ID;
	private String F_name;
	private String L_name;
	private Date DATE;
	private Time Start_Time;
	private Time End_Time;
	
	public ConsultantAvailability(int iD, int user_ID, Date dATE, Time start_Time, Time end_Time) {
		ID = iD;
		User_ID = user_ID;
		DATE = dATE;
		Start_Time = start_Time;
		End_Time = end_Time;
	}
	public ConsultantAvailability(int user_ID, Date dATE, Time start_Time, Time end_Time) {
		User_ID = user_ID;
		DATE = dATE;
		Start_Time = start_Time;
		End_Time = end_Time;
	}
	public ConsultantAvailability(int iD, int user_ID, String f_name, String l_name, Date dATE, Time start_Time,
			Time end_Time) {
		ID = iD;
		User_ID = user_ID;
		F_name = f_name;
		L_name = l_name;
		DATE = dATE;
		Start_Time = start_Time;
		End_Time = end_Time;
	}
	
	public ConsultantAvailability(Date dATE, Time start_Time, Time end_Time,int iD) {
		ID = iD;
		DATE = dATE;
		Start_Time = start_Time;
		End_Time = end_Time;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getUser_ID() {
		return User_ID;
	}
	public void setUser_ID(int user_ID) {
		User_ID = user_ID;
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
	public Date getDATE() {
		return DATE;
	}
	public void setDATE(Date dATE) {
		DATE = dATE;
	}
	public Time getStart_Time() {
		return Start_Time;
	}
	public void setStart_Time(Time start_Time) {
		Start_Time = start_Time;
	}
	public Time getEnd_Time() {
		return End_Time;
	}
	public void setEnd_Time(Time end_Time) {
		End_Time = end_Time;
	}
}
