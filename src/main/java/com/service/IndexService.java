package com.service;

import com.DAO.AppointmentDAO;
import com.DAO.JobseekerDAO;
import com.DAO.SpecializationDAO;
import com.Model.Appointment;
import com.Model.Jobseeker;
import com.Model.specialization;

import java.sql.SQLException;
import java.util.List;

public class IndexService {
    private JobseekerDAO jobseekerDAO;
    private SpecializationDAO specializationDAO;
    private AppointmentDAO appointmentDAO;

    public IndexService() {
        jobseekerDAO = new JobseekerDAO();
        specializationDAO = new SpecializationDAO();
        appointmentDAO = new AppointmentDAO();
    }

    public List<specialization> getAllSpecializations() {
        return specializationDAO.SELECT_SPECIALIZATION_ALL_COUNTRY();
    }

    public int addJobseeker(Jobseeker jobseeker) throws SQLException {
        return jobseekerDAO.addJobseekerReurnID(jobseeker);
    }

    public void addAppointment(Appointment appointment) throws SQLException {
        appointmentDAO.addAppointment(appointment);
    }
}
