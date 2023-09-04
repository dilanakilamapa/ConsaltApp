package com.service;

import com.DAO.AppointmentDAO;
import com.DAO.ConsultantAvailabilityDAO;
import com.DAO.JobseekerDAO;
import com.DAO.UserDAO;
import com.Model.Appointment;
import com.Model.Jobseeker;
import com.Model.User;

import java.sql.SQLException;

import java.util.List;

public class AppointmentService {
    private AppointmentDAO appointmentDAO;
    private UserDAO userDAO;
    private JobseekerDAO jobseekerDAO;
    private ConsultantAvailabilityDAO consultantAvailabilityDAO;

    public AppointmentService() {
        appointmentDAO = new AppointmentDAO();
        userDAO = new UserDAO();
        jobseekerDAO = new JobseekerDAO();
        consultantAvailabilityDAO = new ConsultantAvailabilityDAO();
    }

    public List<Appointment> getAppointmentsByConsultant(int user_ID) {
        return appointmentDAO.SELECT_APPOINTMENT_JOIN_DATA_BY_CONSULT(user_ID);
    }

    public Appointment getAppointmentById(int appointmentId) {
        return appointmentDAO.getAppointmentById(appointmentId);
    }

    public void addAppointment(Appointment appointment) throws SQLException {
        appointmentDAO.addAppointment(appointment);
    }

    public Boolean updateAppointment(Appointment appointment) {
        try {
             appointmentDAO.updateAppointment(appointment);
             return true;
        } catch (SQLException e) {
            e.printStackTrace(); 
            return true;
        }
    }

    public void deleteAppointment(int appointmentId) throws SQLException {
        appointmentDAO.deleteAppointment(appointmentId);
    }

    public List<User> getAllConsultants() {
        return userDAO.selectAllConsultant();
    }

    public List<Jobseeker> getAllJobseekers() {
        return jobseekerDAO.selectAllJobseekers();
    }

	public void setAppointmentDAO(AppointmentDAO appointmentDAO2) {
		appointmentDAO = appointmentDAO2;
		
	}

	public void setUserDAO(UserDAO userDAO2) {
		userDAO = userDAO2; 
		
	}

	public void setJobseekerDAO(JobseekerDAO jobseekerDAO2) {
		jobseekerDAO = jobseekerDAO2;
		
	}

}
