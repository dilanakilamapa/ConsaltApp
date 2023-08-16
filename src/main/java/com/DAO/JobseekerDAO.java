package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Model.Jobseeker;
import com.dbConnection.dbConnection;

public class JobseekerDAO {
    private dbConnection dbConnection;

    public JobseekerDAO() {
        dbConnection = new dbConnection();
    }

    private static final String INSERT_JOBSEEKER_SQL = "INSERT INTO jobseekers (First_Name, Last_Name, Email, Phone_Number) VALUES (?, ?, ?, ?)";
    private static final String SELECT_JOBSEEKER_BY_ID = "SELECT * FROM jobseekers WHERE JobSeekers_ID = ?";
    private static final String SELECT_ALL_JOBSEEKERS = "SELECT * FROM jobseekers";
    private static final String DELETE_JOBSEEKER_SQL = "DELETE FROM jobseekers WHERE JobSeekers_ID = ?";
    private static final String UPDATE_JOBSEEKER_SQL = "UPDATE jobseekers SET First_Name = ?, Last_Name = ?, Email = ?, Phone_Number = ? WHERE JobSeekers_ID = ?";

    public void addJobseeker(Jobseeker jobseeker) throws SQLException {
        try (Connection connection = dbConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_JOBSEEKER_SQL)) {
            preparedStatement.setString(1, jobseeker.getFirst_Name());
            preparedStatement.setString(2, jobseeker.getLast_Name());
            preparedStatement.setString(3, jobseeker.getEmail());
            preparedStatement.setInt(4, jobseeker.getPhone_Number());
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int addJobseekerReurnID(Jobseeker jobseeker) throws SQLException {
        int generatedId = -1; 

        try (Connection connection = dbConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_JOBSEEKER_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, jobseeker.getFirst_Name());
            preparedStatement.setString(2, jobseeker.getLast_Name());
            preparedStatement.setString(3, jobseeker.getEmail());
            preparedStatement.setInt(4, jobseeker.getPhone_Number());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                // Retrieve the generated keys
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return generatedId; 
    }


    public Jobseeker getJobseekerById(int jobSeekersId) {
        Jobseeker jobseeker = null;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_JOBSEEKER_BY_ID);
            preparedStatement.setInt(1, jobSeekersId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int jobSeekers_ID = rs.getInt("JobSeekers_ID");
                String first_Name = rs.getString("First_Name");
                String last_Name = rs.getString("Last_Name");
                String email = rs.getString("Email");
                int phone_Number = rs.getInt("Phone_Number");
                jobseeker = new Jobseeker(jobSeekers_ID, first_Name, last_Name, email, phone_Number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobseeker;
    }

    public List<Jobseeker> selectAllJobseekers() {
        List<Jobseeker> jobseekerList = new ArrayList<>();
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_JOBSEEKERS);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int jobSeekers_ID = rs.getInt("JobSeekers_ID");
                String first_Name = rs.getString("First_Name");
                String last_Name = rs.getString("Last_Name");
                String email = rs.getString("Email");
                int phone_Number = rs.getInt("Phone_Number");
                jobseekerList.add(new Jobseeker(jobSeekers_ID, first_Name, last_Name, email, phone_Number));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobseekerList;
    }

    public boolean deleteJobseeker(int jobSeekersId) throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = dbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_JOBSEEKER_SQL)) {
            statement.setInt(1, jobSeekersId);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean updateJobseeker(Jobseeker jobseeker) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection = dbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_JOBSEEKER_SQL)) {
            statement.setString(1, jobseeker.getFirst_Name());
            statement.setString(2, jobseeker.getLast_Name());
            statement.setString(3, jobseeker.getEmail());
            statement.setInt(4, jobseeker.getPhone_Number());
            statement.setInt(5, jobseeker.getJobSeekers_ID());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
}
