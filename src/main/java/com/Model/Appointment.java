package com.Model;

import java.sql.Date;
import java.sql.Time;

public class Appointment {
	private int Appointment_ID;
	private int Consultant_ID;
	private String Consultant_first_name;
	private String Consultant_last_name;
	private int JobSeeker_ID;
	private String JobSeeker_name;
	private int available_id;
	private Date DATE;
	private Time Start_Time;
	private Time End_Time;
	private int country_id;
	private String country_name;
	private int job_id;
	private String job_name;
	private String Note;
	private String Appointment_Type;
	
	
	
	public Appointment(int appointment_ID, int consultant_ID, String consultant_first_name, String consultant_last_name,
			int jobSeeker_ID, String jobSeeker_name, int available_id, Date dATE, Time start_Time, Time end_Time,
			int country_id, String country_name, int job_id, String job_name, String note, String appointment_Type) {
		Appointment_ID = appointment_ID;
		Consultant_ID = consultant_ID;
		Consultant_first_name = consultant_first_name;
		Consultant_last_name = consultant_last_name;
		JobSeeker_ID = jobSeeker_ID;
		JobSeeker_name = jobSeeker_name;
		this.available_id = available_id;
		DATE = dATE;
		Start_Time = start_Time;
		End_Time = end_Time;
		this.country_id = country_id;
		this.country_name = country_name;
		this.job_id = job_id;
		this.job_name = job_name;
		Note = note;
		Appointment_Type = appointment_Type;
	}
	public Appointment(int appointment_ID, int consultant_ID, int jobSeeker_ID, int available_id, int country_id,
			int job_id, String note,String Appointment_Type) {
		Appointment_ID = appointment_ID;
		Consultant_ID = consultant_ID;
		JobSeeker_ID = jobSeeker_ID;
		this.available_id = available_id;
		this.country_id = country_id;
		this.job_id = job_id;
		Note = note;
		this.Appointment_Type = Appointment_Type;
	}
	public Appointment(int consultant_ID, int jobSeeker_ID, int available_id, int country_id, int job_id, String note,String Appointment_Type) {
		Consultant_ID = consultant_ID;
		JobSeeker_ID = jobSeeker_ID;
		this.available_id = available_id;
		this.country_id = country_id;
		this.job_id = job_id;
		Note = note;
		this.Appointment_Type = Appointment_Type;
	}
	public int getAppointment_ID() {
		return Appointment_ID;
	}
	public void setAppointment_ID(int appointment_ID) {
		Appointment_ID = appointment_ID;
	}
	public int getConsultant_ID() {
		return Consultant_ID;
	}
	public void setConsultant_ID(int consultant_ID) {
		Consultant_ID = consultant_ID;
	}
	public String getConsultant_first_name() {
		return Consultant_first_name;
	}
	public void setConsultant_first_name(String consultant_first_name) {
		Consultant_first_name = consultant_first_name;
	}
	public String getConsultant_last_name() {
		return Consultant_last_name;
	}
	public void setConsultant_last_name(String consultant_last_name) {
		Consultant_last_name = consultant_last_name;
	}
	public int getJobSeeker_ID() {
		return JobSeeker_ID;
	}
	public void setJobSeeker_ID(int jobSeeker_ID) {
		JobSeeker_ID = jobSeeker_ID;
	}
	public String getJobSeeker_name() {
		return JobSeeker_name;
	}
	public void setJobSeeker_name(String jobSeeker_name) {
		JobSeeker_name = jobSeeker_name;
	}
	public int getAvailable_id() {
		return available_id;
	}
	public void setAvailable_id(int available_id) {
		this.available_id = available_id;
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
	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}
	public String getJob_name() {
		return job_name;
	}
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
	public String getAppointment_Type() {
		return Appointment_Type;
	}
	public void setAppointment_Type(String appointment_Type) {
		Appointment_Type = appointment_Type;
	}
	
}
