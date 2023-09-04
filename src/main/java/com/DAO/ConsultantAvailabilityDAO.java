package com.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Model.ConsultantAvailability;
import com.dbConnection.DBSingletonConnection;

public class ConsultantAvailabilityDAO {
private DBSingletonConnection dbConnection;
	
	private Connection getConnection() {
        return dbConnection.getConnection();
    }

    public ConsultantAvailabilityDAO() {
    	dbConnection = DBSingletonConnection.getInstance();
    }
    private static final String INSERT_CONSULTANT_AVAILABILITY_SQL = "INSERT INTO consultant_availability (User_ID, DATE, Start_Time, End_Time) VALUES (?, ?, ?, ?)";
    private static final String SELECT_CONSULTANT_AVAILABILITY_BY_ID = "SELECT * FROM consultant_availability WHERE ID = ?";
    private static final String SELECT_ALL_CONSULTANT_AVAILABILITIES = "SELECT * FROM consultant_availability ";
    private static final String DELETE_CONSULTANT_AVAILABILITY_SQL = "DELETE FROM consultant_availability WHERE ID = ?";
    private static final String UPDATE_CONSULTANT_AVAILABILITY_SQL = "UPDATE consultant_availability SET  DATE = ?, Start_Time = ?, End_Time = ? WHERE ID = ?";
    private static final String SELECT_ALL_CONSULTANT_AVAILABILITIES_WITH_NAME ="SELECT consultant_availability.ID , consultant_availability.User_ID , user.F_name , user.L_name , consultant_availability.Date , consultant_availability.Start_Time , consultant_availability.End_Time FROM db_appointment.consultant_availability INNER JOIN db_appointment.user ON (consultant_availability.User_ID = user.ID) WHERE User_ID =?;";
    private static final String SELECT_START_AND_END_TIME_BY_DATE ="SELECT * FROM consultant_availability WHERE DATE = ?";
    
    public void addConsultantAvailability(ConsultantAvailability consultantAvailability) throws SQLException {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONSULTANT_AVAILABILITY_SQL)) {
            preparedStatement.setInt(1, consultantAvailability.getUser_ID());
            preparedStatement.setDate(2, consultantAvailability.getDATE());
            preparedStatement.setTime(3, consultantAvailability.getStart_Time());
            preparedStatement.setTime(4, consultantAvailability.getEnd_Time());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConsultantAvailability getConsultantAvailabilityById(int availabilityId) {
        ConsultantAvailability consultantAvailability = null;
        try {
            Connection connection =getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONSULTANT_AVAILABILITY_BY_ID);
            preparedStatement.setInt(1, availabilityId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int ID = rs.getInt("ID");
                int user_ID = rs.getInt("User_ID");
                java.sql.Date DATE = rs.getDate("DATE");
                java.sql.Time start_Time = rs.getTime("Start_Time");
                java.sql.Time end_Time = rs.getTime("End_Time");
                consultantAvailability = new ConsultantAvailability(ID, user_ID, DATE, start_Time, end_Time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consultantAvailability;
    }

    public List<ConsultantAvailability> selectAllConsultantAvailabilities() {
        List<ConsultantAvailability> consultantAvailabilityList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CONSULTANT_AVAILABILITIES);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int ID = rs.getInt("ID");
                int user_ID = rs.getInt("User_ID");
                java.sql.Date DATE = rs.getDate("DATE");
                java.sql.Time start_Time = rs.getTime("Start_Time");
                java.sql.Time end_Time = rs.getTime("End_Time");
                consultantAvailabilityList.add(new ConsultantAvailability(ID, user_ID, DATE, start_Time, end_Time));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consultantAvailabilityList;
    }

    public boolean deleteConsultantAvailability(int availabilityId) throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_CONSULTANT_AVAILABILITY_SQL)) {
            statement.setInt(1, availabilityId);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean updateConsultantAvailability(ConsultantAvailability consultantAvailability) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_CONSULTANT_AVAILABILITY_SQL)) {
            statement.setDate(1, consultantAvailability.getDATE());
            statement.setTime(2, consultantAvailability.getStart_Time());
            statement.setTime(3, consultantAvailability.getEnd_Time());
            statement.setInt(4, consultantAvailability.getID());
            rowUpdated = statement.executeUpdate() > 0;
            System.out.println(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
    
    public List<ConsultantAvailability> selectAllConsultantAvailabilitiesWithName(int User_id) {
        List<ConsultantAvailability> consultantAvailabilityList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CONSULTANT_AVAILABILITIES_WITH_NAME);
            preparedStatement.setInt(1, User_id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int ID = rs.getInt("ID");
                int user_ID = rs.getInt("User_ID");
                String F_name = rs.getString("F_name");
                String L_name = rs.getString("L_name");
                java.sql.Date DATE = rs.getDate("DATE");
                java.sql.Time start_Time = rs.getTime("Start_Time");
                java.sql.Time end_Time = rs.getTime("End_Time");
                consultantAvailabilityList.add(new ConsultantAvailability(ID, user_ID, F_name, L_name, DATE, start_Time, end_Time));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consultantAvailabilityList;
    }
    
    public List<ConsultantAvailability> SELECT_START_AND_END_TIME_BY_DATE(Date date) {
        List<ConsultantAvailability> consultantAvailabilityList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_START_AND_END_TIME_BY_DATE);
            preparedStatement.setDate(1, date);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int ID = rs.getInt("ID");
                int user_ID = rs.getInt("User_ID");
                java.sql.Date DATE = rs.getDate("DATE");
                java.sql.Time start_Time = rs.getTime("Start_Time");
                java.sql.Time end_Time = rs.getTime("End_Time");
                consultantAvailabilityList.add(new ConsultantAvailability(ID, user_ID, DATE, start_Time, end_Time));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consultantAvailabilityList;
    }
}
