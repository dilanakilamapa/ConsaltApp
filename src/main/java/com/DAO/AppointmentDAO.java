package com.DAO;

import com.Model.Appointment;
import com.dbConnection.DBSingletonConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
	private DBSingletonConnection dbConnection;
	
	private Connection getConnection() {
        return dbConnection.getConnection();
    }

    public AppointmentDAO() {
    	dbConnection = DBSingletonConnection.getInstance();
    }
    
    private static final String INSERT_APPOINTMENT_SQL = "INSERT INTO appointments (Consultant_ID, JobSeeker_ID, Available_ID, Country_ID, Job_ID, Note, Appointment_Type) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_APPOINTMENT_BY_ID = "SELECT * FROM appointments WHERE Appointment_ID = ?";
    private static final String SELECT_ALL_APPOINTMENTS = "SELECT * FROM appointments";
    private static final String DELETE_APPOINTMENT_SQL = "DELETE FROM appointments WHERE Appointment_ID = ?";
    private static final String UPDATE_APPOINTMENT_SQL = "UPDATE appointments SET Consultant_ID = ?, JobSeeker_ID = ?, Available_ID = ?, Country_ID = ?, Job_ID = ?, Note = ? , Appointment_Type = ? WHERE Appointment_ID = ?";
    private static final String SELECT_APPOINTMENT_JOIN_DATA_ALL ="SELECT appointments.Appointment_ID , appointments.Consultant_ID , user.F_name , user.L_name , appointments.JobSeeker_ID , jobseekers.First_Name , appointments.available_id , consultant_availability.Date , consultant_availability.Start_Time , consultant_availability.End_Time , appointments.country_id , country.country_name , appointments.job_id , job.Job_name , appointments.Note , appointments.Appointment_Type FROM db_appointment.appointments INNER JOIN db_appointment.consultant_availability ON (appointments.available_id = consultant_availability.ID) INNER JOIN db_appointment.user ON (appointments.Consultant_ID = user.ID) INNER JOIN db_appointment.country ON (appointments.country_id = country.id) AND (consultant_availability.User_ID = user.ID) INNER JOIN db_appointment.job ON (job.job_id = appointments.job_id) INNER JOIN db_appointment.jobseekers ON (appointments.JobSeeker_ID = jobseekers.JobSeekers_ID)";
    private static final String SELECT_APPOINTMENT_JOIN_DATA_BY_CONSULT ="SELECT appointments.Appointment_ID , appointments.Consultant_ID , user.F_name , user.L_name , appointments.JobSeeker_ID , jobseekers.First_Name , appointments.available_id , consultant_availability.Date , consultant_availability.Start_Time , consultant_availability.End_Time , appointments.country_id , country.country_name , appointments.job_id , job.Job_name , appointments.Note , appointments.Appointment_Type FROM db_appointment.appointments INNER JOIN db_appointment.consultant_availability ON (appointments.available_id = consultant_availability.ID) INNER JOIN db_appointment.user ON (appointments.Consultant_ID = user.ID) INNER JOIN db_appointment.country ON (appointments.country_id = country.id) AND (consultant_availability.User_ID = user.ID) INNER JOIN db_appointment.job ON (job.job_id = appointments.job_id) INNER JOIN db_appointment.jobseekers ON (appointments.JobSeeker_ID = jobseekers.JobSeekers_ID) WHERE appointments.Consultant_ID = ?";
    
    public void addAppointment(Appointment appointment) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_APPOINTMENT_SQL)) {
            preparedStatement.setInt(1, appointment.getConsultant_ID());
            preparedStatement.setInt(2, appointment.getJobSeeker_ID());
            preparedStatement.setInt(3, appointment.getAvailable_id());
            preparedStatement.setInt(4, appointment.getCountry_id());
            preparedStatement.setInt(5, appointment.getJob_id());
            preparedStatement.setString(6, appointment.getNote());
            preparedStatement.setString(7, appointment.getAppointment_Type());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Appointment getAppointmentById(int appointmentId) {
        Appointment appointment = null;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_APPOINTMENT_BY_ID);
            preparedStatement.setInt(1, appointmentId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int Appointment_ID = rs.getInt("Appointment_ID");
                int Consultant_ID = rs.getInt("Consultant_ID");
                int JobSeeker_ID = rs.getInt("JobSeeker_ID");
                int Available_ID = rs.getInt("Available_ID");
                int Country_ID = rs.getInt("Country_ID");
                int Job_ID = rs.getInt("Job_ID");
                String Note = rs.getString("Note");
                String Appointment_Type = rs.getString("Appointment_Type");
                appointment = new Appointment(Appointment_ID, Consultant_ID, JobSeeker_ID, Available_ID, Country_ID, Job_ID, Note, Appointment_Type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointment;
    }

    public List<Appointment> selectAllAppointments() {
        List<Appointment> appointmentList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_APPOINTMENTS);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int Appointment_ID = rs.getInt("Appointment_ID");
                int Consultant_ID = rs.getInt("Consultant_ID");
                int JobSeeker_ID = rs.getInt("JobSeeker_ID");
                int Available_ID = rs.getInt("Available_ID");
                int Country_ID = rs.getInt("Country_ID");
                int Job_ID = rs.getInt("Job_ID");
                String Note = rs.getString("Note");
                String Appointment_Type = rs.getString("Appointment_Type");
                appointmentList.add(new Appointment(Appointment_ID, Consultant_ID, JobSeeker_ID, Available_ID, Country_ID, Job_ID, Note, Appointment_Type));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointmentList;
    }

    public boolean deleteAppointment(int appointmentId) throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_APPOINTMENT_SQL)) {
            statement.setInt(1, appointmentId);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean updateAppointment(Appointment appointment) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_APPOINTMENT_SQL)) {
            statement.setInt(1, appointment.getConsultant_ID());
            statement.setInt(2, appointment.getJobSeeker_ID());
            statement.setInt(3, appointment.getAvailable_id());
            statement.setInt(4, appointment.getCountry_id());
            statement.setInt(5, appointment.getJob_id());
            statement.setString(6, appointment.getNote());
            statement.setString(7, appointment.getAppointment_Type());
            statement.setInt(8, appointment.getAppointment_ID());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
    
    
    public List<Appointment> SELECT_APPOINTMENT_JOIN_DATA_ALL() {
    	
        List<Appointment> appointmentList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_APPOINTMENT_JOIN_DATA_ALL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int Appointment_ID = rs.getInt("Appointment_ID");
                int Consultant_ID = rs.getInt("Consultant_ID");
                String Consultant_first_name = rs.getString("F_name");
                String Consultant_last_name = rs.getString("L_name");
                int JobSeeker_ID = rs.getInt("JobSeeker_ID");
                String JobSeeker_name = rs.getString("First_Name");
                int Available_ID = rs.getInt("available_id");
                Date DATE = rs.getDate("Date");
                Time start_Time = rs.getTime("Start_Time");
                Time end_Time = rs.getTime("End_Time");
                int Country_ID = rs.getInt("country_id");
                String country_name = rs.getString("country_name");
                int Job_ID = rs.getInt("job_id");
                String job_name = rs.getString("Job_name");
                String Note = rs.getString("Note");
                String Appointment_Type = rs.getString("Appointment_Type");
                appointmentList.add(new Appointment(Appointment_ID, Consultant_ID, Consultant_first_name, Consultant_last_name, JobSeeker_ID, JobSeeker_name, Available_ID, DATE, start_Time, end_Time, Country_ID, country_name, Job_ID, job_name, Note, Appointment_Type));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointmentList;
    }
    
    public List<Appointment> SELECT_APPOINTMENT_JOIN_DATA_BY_CONSULT(int id) {
    	
        List<Appointment> appointmentList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_APPOINTMENT_JOIN_DATA_BY_CONSULT);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int Appointment_ID = rs.getInt("Appointment_ID");
                int Consultant_ID = rs.getInt("Consultant_ID");
                String Consultant_first_name = rs.getString("F_name");
                String Consultant_last_name = rs.getString("L_name");
                int JobSeeker_ID = rs.getInt("JobSeeker_ID");
                String JobSeeker_name = rs.getString("First_Name");
                int Available_ID = rs.getInt("available_id");
                java.sql.Date DATE = rs.getDate("Date");
                java.sql.Time start_Time = rs.getTime("Start_Time");
                java.sql.Time end_Time = rs.getTime("End_Time");
                int Country_ID = rs.getInt("country_id");
                String country_name = rs.getString("country_name");
                int Job_ID = rs.getInt("job_id");
                String job_name = rs.getString("Job_name");
                String Note = rs.getString("Note");
                String Appointment_Type = rs.getString("Appointment_Type");
                appointmentList.add(new Appointment(Appointment_ID, Consultant_ID, Consultant_first_name, Consultant_last_name, JobSeeker_ID, JobSeeker_name, Available_ID, DATE, start_Time, end_Time, Country_ID, country_name, Job_ID, job_name, Note, Appointment_Type));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointmentList;
    }
    
    
}
