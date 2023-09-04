package com.test;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.DAO.AppointmentDAO;
import com.DAO.UserDAO;
import com.DAO.JobseekerDAO;
import com.Model.Appointment;
import com.Model.User;
import com.Model.Jobseeker;
import com.service.AppointmentService;

public class AppointmentServiceTest {

    @Mock
    private AppointmentDAO appointmentDAO;
    
    @Mock
    private UserDAO userDAO;
    
    @Mock
    private JobseekerDAO jobseekerDAO;

    private AppointmentService appointmentService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        appointmentService = new AppointmentService();
        appointmentService.setAppointmentDAO(appointmentDAO);
        appointmentService.setUserDAO(userDAO);
        appointmentService.setJobseekerDAO(jobseekerDAO);
    }

    @Test
    public void testGetAppointmentsByConsultant() {
        int userId = 123;
        List<Appointment> expectedAppointments = new ArrayList<>();
        when(appointmentDAO.SELECT_APPOINTMENT_JOIN_DATA_BY_CONSULT(userId)).thenReturn(expectedAppointments);

        List<Appointment> actualAppointments = appointmentService.getAppointmentsByConsultant(userId);

        assertEquals(expectedAppointments, actualAppointments);
    }

    @Test
    public void testGetAppointmentById() {
        int appointmentId = 456;
        Appointment expectedAppointment = new Appointment(456, 2, 1, 4, 2, 3, "ABC", "Online");
        when(appointmentDAO.getAppointmentById(appointmentId)).thenReturn(expectedAppointment);

        Appointment actualAppointment = appointmentService.getAppointmentById(appointmentId);

        assertEquals(expectedAppointment, actualAppointment);
    }

    @Test
    public void testAddAppointment() throws SQLException {
        Appointment appointment = new Appointment(456, 2, 1, 4, 2, 3, "ABC", "Online");

        appointmentService.addAppointment(appointment);

        verify(appointmentDAO).addAppointment(appointment);
    }

    @Test
    public void testUpdateAppointment() throws SQLException {
        Appointment appointment = new Appointment(456, 2, 1, 4, 2, 3, "ABC", "Online");

        boolean result = appointmentService.updateAppointment(appointment);

        assertTrue(result);
        verify(appointmentDAO).updateAppointment(appointment);
    }

    @Test
    public void testDeleteAppointment() throws SQLException {
        int appointmentId = 789;

        appointmentService.deleteAppointment(appointmentId);

        verify(appointmentDAO).deleteAppointment(appointmentId);
    }

    @Test
    public void testGetAllConsultants() {
        List<User> expectedConsultants = new ArrayList<>();
        when(userDAO.selectAllConsultant()).thenReturn(expectedConsultants);

        List<User> actualConsultants = appointmentService.getAllConsultants();

        assertEquals(expectedConsultants, actualConsultants);
    }

    @Test
    public void testGetAllJobseekers() {
        List<Jobseeker> expectedJobseekers = new ArrayList<>();
        when(jobseekerDAO.selectAllJobseekers()).thenReturn(expectedJobseekers);

        List<Jobseeker> actualJobseekers = appointmentService.getAllJobseekers();

        assertEquals(expectedJobseekers, actualJobseekers);
    }

}
