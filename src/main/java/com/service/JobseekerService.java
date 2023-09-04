package com.service;

import com.DAO.JobseekerDAO;
import com.Model.Jobseeker;

import java.sql.SQLException;
import java.util.List;

public class JobseekerService {
    private JobseekerDAO jobseekerDAO;

    public JobseekerService() {
        jobseekerDAO = new JobseekerDAO();
    }

    public List<Jobseeker> getAllJobseekers() {
        return jobseekerDAO.selectAllJobseekers();
    }

    public Jobseeker getJobseekerById(int jobseekerId) {
        return jobseekerDAO.getJobseekerById(jobseekerId);
    }

    public void addJobseeker(Jobseeker jobseeker) throws SQLException {
        jobseekerDAO.addJobseeker(jobseeker);
    }

    public boolean updateJobseeker(Jobseeker jobseeker) throws SQLException {
        return jobseekerDAO.updateJobseeker(jobseeker);
    }

    public void deleteJobseeker(int jobseekerId) throws SQLException {
        jobseekerDAO.deleteJobseeker(jobseekerId);
    }

	public void setJobseekerDAO(JobseekerDAO mockJobseekerDAO) {
		jobseekerDAO = mockJobseekerDAO;
		
	}
}
