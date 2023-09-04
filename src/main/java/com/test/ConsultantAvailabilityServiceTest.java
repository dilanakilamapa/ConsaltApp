package com.test;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.DAO.ConsultantAvailabilityDAO;
import com.DAO.UserDAO;
import com.Model.ConsultantAvailability;
import com.Model.User;
import com.service.ConsultantAvailabilityService;

public class ConsultantAvailabilityServiceTest {

    @Mock
    private ConsultantAvailabilityDAO consultantAvailabilityDAO;
    
    @Mock
    private UserDAO userDAO;

    private ConsultantAvailabilityService consultantAvailabilityService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        consultantAvailabilityService = new ConsultantAvailabilityService();
        consultantAvailabilityService.setConsultantAvailabilityDAO(consultantAvailabilityDAO);
        consultantAvailabilityService.setUserDAO(userDAO);
    }

    @Test
    public void testGetAllConsultantAvailabilities() {
        int userId = 123;
        List<ConsultantAvailability> expectedAvailabilities = new ArrayList<>();
        when(consultantAvailabilityDAO.selectAllConsultantAvailabilitiesWithName(userId)).thenReturn(expectedAvailabilities);

        List<ConsultantAvailability> actualAvailabilities = consultantAvailabilityService.getAllConsultantAvailabilities(userId);

        assertEquals(expectedAvailabilities, actualAvailabilities);
    }

    @Test
    public void testGetConsultantAvailabilityById() {
        int availabilityId = 456;
        ConsultantAvailability expectedAvailability = new ConsultantAvailability(456, 1, Date.valueOf("2023-09-01"), Time.valueOf("20:16:23"), Time.valueOf("23:16:32"));
        when(consultantAvailabilityDAO.getConsultantAvailabilityById(availabilityId)).thenReturn(expectedAvailability);

        ConsultantAvailability actualAvailability = consultantAvailabilityService.getConsultantAvailabilityById(availabilityId);

        assertEquals(expectedAvailability, actualAvailability);
    }

    @Test
    public void testAddConsultantAvailability() throws SQLException {
        ConsultantAvailability availability = new ConsultantAvailability(456, 1, Date.valueOf("2023-09-01"), Time.valueOf("20:16:23"), Time.valueOf("23:16:32"));

        consultantAvailabilityService.addConsultantAvailability(availability);

        verify(consultantAvailabilityDAO).addConsultantAvailability(availability);
    }

    @Test
    public void testUpdateConsultantAvailability() throws SQLException {
        ConsultantAvailability availability = new ConsultantAvailability(456, 1, Date.valueOf("2023-09-01"), Time.valueOf("20:16:23"), Time.valueOf("23:16:32"));

        boolean result = consultantAvailabilityService.updateConsultantAvailability(availability);

        assertTrue(result);
        verify(consultantAvailabilityDAO).updateConsultantAvailability(availability);
    }

    @Test
    public void testDeleteConsultantAvailability() throws SQLException {
        int availabilityId = 789;

        consultantAvailabilityService.deleteConsultantAvailability(availabilityId);

        verify(consultantAvailabilityDAO).deleteConsultantAvailability(availabilityId);
    }

    @Test
    public void testGetAllConsultants() {
        List<User> expectedConsultants = new ArrayList<>();
        when(userDAO.selectAllConsultant()).thenReturn(expectedConsultants);

        List<User> actualConsultants = consultantAvailabilityService.getAllConsultants();

        assertEquals(expectedConsultants, actualConsultants);
    }

    @Test
    public void testGetAvailabilityByDate() {
        Date availabilityDate = Date.valueOf("2023-09-01");
        List<ConsultantAvailability> expectedAvailabilities = new ArrayList<>();
        when(consultantAvailabilityDAO.SELECT_START_AND_END_TIME_BY_DATE(availabilityDate)).thenReturn(expectedAvailabilities);

        List<ConsultantAvailability> actualAvailabilities = consultantAvailabilityService.getAvailabilityByDate(availabilityDate);

        assertEquals(expectedAvailabilities, actualAvailabilities);
    }

    
}
