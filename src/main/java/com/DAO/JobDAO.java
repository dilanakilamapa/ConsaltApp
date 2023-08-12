package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Model.Job;
import com.dbConnection.dbConnection;

public class JobDAO {
    private dbConnection dbConnection;

    public JobDAO() {
        dbConnection = new dbConnection();
    }

    private static final String INSERT_JOB_SQL = "INSERT INTO job (Job_name) VALUES (?)";
    private static final String SELECT_JOB_BY_ID = "SELECT * FROM job WHERE job_id = ?";
    private static final String SELECT_ALL_JOBS = "SELECT * FROM job";
    private static final String DELETE_JOB_SQL = "DELETE FROM job WHERE job_id = ?";
    private static final String UPDATE_JOB_SQL = "UPDATE job SET Job_name = ? WHERE job_id = ?";

    public void addJob(Job job) throws SQLException {
        try (Connection connection = dbConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_JOB_SQL)) {
            preparedStatement.setString(1, job.getName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Job getJobById(int jobId) {
        Job job = null;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_JOB_BY_ID);
            preparedStatement.setInt(1, jobId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("job_id");
                String name = rs.getString("Job_name");
                job = new Job(id, name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return job;
    }

    public List<Job> selectAllJobs() {
        List<Job> jobList = new ArrayList<>();
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_JOBS);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("job_id");
                String name = rs.getString("Job_name");
                jobList.add(new Job(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobList;
    }

    public boolean deleteJob(int jobId) throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = dbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_JOB_SQL)) {
            statement.setInt(1, jobId);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean updateJob(Job job) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection = dbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_JOB_SQL)) {
            statement.setString(1, job.getName());
            statement.setInt(2, job.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
}
