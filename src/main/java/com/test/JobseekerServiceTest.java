package com.test;

import com.Model.Jobseeker;
import com.service.JobseekerService;
import com.DAO.JobseekerDAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobseekerServiceTest {
    private JobseekerService jobseekerService;
    private JobseekerDAO mockJobseekerDAO;

    @BeforeEach
    public void setUp() {
        mockJobseekerDAO = mock(JobseekerDAO.class);
        jobseekerService = new JobseekerService();
        jobseekerService.setJobseekerDAO(mockJobseekerDAO);
    }

    @Test
    public void testGetAllJobseekers() {
        List<Jobseeker> mockJobseekers = new ArrayList<>();
        when(mockJobseekerDAO.selectAllJobseekers()).thenReturn(mockJobseekers);

        List<Jobseeker> jobseekers = jobseekerService.getAllJobseekers();
        assertNotNull(jobseekers);
        assertEquals(mockJobseekers, jobseekers);
    }

    @Test
    public void testGetJobseekerById() {
        int jobseekerId = 1;
        Jobseeker mockJobseeker = new Jobseeker(1, "Dilan", "Mapa", "dilan@gmail.com", 1234567890);
        when(mockJobseekerDAO.getJobseekerById(jobseekerId)).thenReturn(mockJobseeker);

        Jobseeker jobseeker = jobseekerService.getJobseekerById(jobseekerId);
        assertNotNull(jobseeker);
        assertEquals(mockJobseeker, jobseeker);
    }
    
    @Test
    public void testUpdateJobseeker() throws SQLException {
        int jobseekerId = 1;
        Jobseeker mockJobseeker = new Jobseeker(1, "Dilan", "Mapa", "dilan@gmail.com", 1234567890);
        
        when(mockJobseekerDAO.updateJobseeker(mockJobseeker)).thenReturn(true);
        
        boolean result = jobseekerService.updateJobseeker(mockJobseeker);
        assertTrue(result);
        
        verify(mockJobseekerDAO).updateJobseeker(mockJobseeker);
    }

}
