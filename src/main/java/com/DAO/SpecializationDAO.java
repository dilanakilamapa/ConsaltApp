package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Model.specialization;
import com.dbConnection.dbConnection;

public class SpecializationDAO {
	 private dbConnection dbConnection;

	    public SpecializationDAO() {
	        dbConnection = new dbConnection();
	    }

	    private static final String INSERT_SPECIALIZATION_SQL = "INSERT INTO specialization (User_ID, Country_id, Job_Title_id) VALUES (?, ?, ?)";
	    private static final String SELECT_SPECIALIZATION_BY_ID = "SELECT * FROM specialization WHERE id = ?";
	    private static final String SELECT_ALL_SPECIALIZATIONS = "SELECT * FROM specialization";
	    private static final String DELETE_SPECIALIZATION_SQL = "DELETE FROM specialization WHERE id = ?";
	    private static final String UPDATE_SPECIALIZATION_SQL = "UPDATE specialization SET User_ID = ?, Country_id = ?, Job_Title_id = ? WHERE id = ?";
	    private static final String SELECT_SPECIALIZATION_WITH_NAME = "SELECT specialization.ID , specialization.User_ID , user.F_name , user.L_name , specialization.Country_id , country.country_name , specialization.Job_Title_id , job.Job_name FROM db_appointment.specialization INNER JOIN db_appointment.country ON (specialization.Country_id = country.id) INNER JOIN db_appointment.job ON (specialization.Job_Title_id = job.job_id) INNER JOIN db_appointment.user ON (specialization.User_ID = user.ID);";
	    private static final String SELECT_SPECIALIZATION_COUNTRY_BY_USER_ID = "SELECT specialization.Country_id , country.country_name FROM db_appointment.specialization INNER JOIN db_appointment.country ON (specialization.Country_id = country.id) WHERE specialization.User_ID = ? GROUP BY  country.country_name";
	    private static final String SELECT_SPECIALIZATION_COUNTRY_BY_COUNTRY_ID = "SELECT specialization.Job_Title_id , job.Job_name FROM db_appointment.specialization INNER JOIN db_appointment.job ON (specialization.Job_Title_id = job.job_id) WHERE Country_id= ? GROUP BY Job_Title_id;";
	    private static final String SELECT_SPECIALIZATION_ALL_COUNTRY = "SELECT specialization.Country_id , country.country_name FROM db_appointment.specialization INNER JOIN db_appointment.country ON (specialization.Country_id = country.id) GROUP BY specialization.Country_id;";
	    private static final String SELECT_SPECIALIZATION_USER_USING_COUNTRY_AND_JOB = "SELECT specialization.User_ID , user.F_name , user.L_name FROM db_appointment.specialization INNER JOIN db_appointment.user ON (specialization.User_ID = user.ID) WHERE specialization.Country_id =? AND specialization.Job_Title_id = ?;";
	    
	    public void addSpecialization(specialization spec) throws SQLException {
	        try (Connection connection = dbConnection.getConnection();
	                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SPECIALIZATION_SQL)) {
	            preparedStatement.setInt(1, spec.getUser_ID());
	            preparedStatement.setInt(2, spec.getCountry_id());
	            preparedStatement.setInt(3, spec.getJob_Title_id());
	            preparedStatement.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public specialization getSpecializationById(int specId) {
	        specialization spec = null;
	        try {
	            Connection connection = dbConnection.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SPECIALIZATION_BY_ID);
	            preparedStatement.setInt(1, specId);
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                int user_ID = rs.getInt("User_ID");
	                int country_id = rs.getInt("Country_id");
	                int job_Title_id = rs.getInt("Job_Title_id");
	                spec = new specialization(id, user_ID, country_id, job_Title_id);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return spec;
	    }

	    public List<specialization> selectAllSpecializations() {
	        List<specialization> specList = new ArrayList<>();
	        try {
	            Connection connection = dbConnection.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SPECIALIZATIONS);
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                int user_ID = rs.getInt("User_ID");
	                int country_id = rs.getInt("Country_id");
	                int job_Title_id = rs.getInt("Job_Title_id");
	                specList.add(new specialization(id, user_ID, country_id, job_Title_id));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return specList;
	    }

	    public boolean deleteSpecialization(int specId) throws SQLException {
	        boolean rowDeleted = false;
	        try (Connection connection = dbConnection.getConnection();
	                PreparedStatement statement = connection.prepareStatement(DELETE_SPECIALIZATION_SQL)) {
	            statement.setInt(1, specId);
	            rowDeleted = statement.executeUpdate() > 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return rowDeleted;
	    }

	    public boolean updateSpecialization(specialization spec) throws SQLException {
	        boolean rowUpdated = false;
	        try (Connection connection = dbConnection.getConnection();
	                PreparedStatement statement = connection.prepareStatement(UPDATE_SPECIALIZATION_SQL)) {
	            statement.setInt(1, spec.getUser_ID());
	            statement.setInt(2, spec.getCountry_id());
	            statement.setInt(3, spec.getJob_Title_id());
	            statement.setInt(4, spec.getId());
	            rowUpdated = statement.executeUpdate() > 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return rowUpdated;
	    }
	    
	    public List<specialization> selectAllSpecializationswithname() {
	        List<specialization> specList = new ArrayList<>();
	        try {
	            Connection connection = dbConnection.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SPECIALIZATION_WITH_NAME);
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                int user_ID = rs.getInt("User_ID");
	                String User_F_Name =rs.getString("F_name");
	                String User_L_Name =rs.getString("L_name");
	                int country_id = rs.getInt("Country_id");
	                String Country_Name =rs.getString("country_name");
	                int job_Title_id = rs.getInt("Job_Title_id");
	                String Job_Name =rs.getString("Job_name");
	                specList.add(new specialization(id, user_ID, User_F_Name, User_L_Name, country_id, Country_Name, job_Title_id, Job_Name));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return specList;
	    }
	    
	    public List<specialization> SELECT_SPECIALIZATION_COUNTRY_BY_USER_ID(int User_ID) {
	    	//System.out.println(User_ID);
	    	List<specialization> specList = new ArrayList<>();
	        try {
	            Connection connection = dbConnection.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SPECIALIZATION_COUNTRY_BY_USER_ID);
	            preparedStatement.setInt(1, User_ID);
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                int country_id = rs.getInt("Country_id");
	                String Country_name = rs.getString("country_name");
	                specList.add( new specialization(country_id, Country_name));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return specList;
	    }
	    
	    public List<specialization> SELECT_SPECIALIZATION_COUNTRY_BY_COUNTRY_ID(int User_ID) {
	    	System.out.println(User_ID);
	    	List<specialization> specList = new ArrayList<>();
	        try {
	            Connection connection = dbConnection.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SPECIALIZATION_COUNTRY_BY_COUNTRY_ID);
	            preparedStatement.setInt(1, User_ID);
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                int job_id = rs.getInt("Job_Title_id");
	                String job_name = rs.getString("Job_name");
	                specList.add( new specialization(job_name, job_id));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return specList;
	    }
	    
	    //SELECT_SPECIALIZATION_ALL_COUNTRY
	    
	    public List<specialization> SELECT_SPECIALIZATION_ALL_COUNTRY() {
	    	List<specialization> specList = new ArrayList<>();
	        try {
	            Connection connection = dbConnection.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SPECIALIZATION_ALL_COUNTRY);
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	            	int country_id = rs.getInt("Country_id");
	                String Country_name = rs.getString("country_name");
	                specList.add( new specialization(country_id, Country_name));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return specList;
	    }
	    
	    //SELECT_SPECIALIZATION_USER_USING_COUNTRY_AND_JOB
	    
	    public List<specialization> SELECT_SPECIALIZATION_USER_USING_COUNTRY_AND_JOB(int country_id, int Job_id) {
	    	List<specialization> specList = new ArrayList<>();
	        try {
	            Connection connection = dbConnection.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SPECIALIZATION_USER_USING_COUNTRY_AND_JOB);
	            preparedStatement.setInt(1, country_id);
	            preparedStatement.setInt(2, Job_id);
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	            	int User_id = rs.getInt("User_ID");
	                String User_first_name = rs.getString("F_name");
	                String User_last_name = rs.getString("L_name");
	                specList.add( new specialization(User_id, User_first_name, User_last_name));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return specList;
	    }
	    

}
