package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Model.User;
import com.dbConnection.dbConnection;

public class UserDAO {
	 private dbConnection dbConnection;

    public UserDAO() {
    	 dbConnection = new dbConnection();
    }

    private static final String INSERT_USER_SQL = "INSERT INTO user (F_name, L_name, Address, Contact_01, Contact_02, DOB, role_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE Id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM user";
    private static final String DELETE_USER_SQL = "DELETE FROM user WHERE Id = ?";
    private static final String UPDATE_USER_SQL = "UPDATE user SET F_name = ?, L_name = ?, Address = ?, Contact_01 = ?, Contact_02 = ?, DOB = ?, role_id = ? WHERE Id = ?";

    public void addUser(User user) throws SQLException {
        try (Connection connection = dbConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getF_name());
            preparedStatement.setString(2, user.getL_name());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setInt(4, user.getContact_01());
            preparedStatement.setInt(5, user.getContact_02());
            preparedStatement.setDate(6, user.getDOB());
            preparedStatement.setInt(7, user.getRole_id());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int userId) {
        User user = null;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String f_name = rs.getString("F_name");
                String l_name = rs.getString("L_name");
                String address = rs.getString("Address");
                int contact_01 = rs.getInt("Contact_01");
                int contact_02 = rs.getInt("Contact_02");
                java.sql.Date dob = rs.getDate("DOB");
                int role_id = rs.getInt("role_id");
                user = new User(userId, f_name, l_name, address, contact_01, contact_02, dob, role_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> selectAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Id");
                String f_name = rs.getString("F_name");
                String l_name = rs.getString("L_name");
                String address = rs.getString("Address");
                int contact_01 = rs.getInt("Contact_01");
                int contact_02 = rs.getInt("Contact_02");
                java.sql.Date dob = rs.getDate("DOB");
                int role_id = rs.getInt("role_id");
                userList.add(new User(id, f_name, l_name, address, contact_01, contact_02, dob, role_id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public boolean deleteUser(int userId) throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = dbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL)) {
            statement.setInt(1, userId);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection = dbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL)) {
            statement.setString(1, user.getF_name());
            statement.setString(2, user.getL_name());
            statement.setString(3, user.getAddress());
            statement.setInt(4, user.getContact_01());
            statement.setInt(5, user.getContact_02());
            statement.setDate(6, user.getDOB());
            statement.setInt(7, user.getRole_id());
            statement.setInt(8, user.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
}
