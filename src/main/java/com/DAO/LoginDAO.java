package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.Model.Login;
import com.dbConnection.*;

public class LoginDAO {
	private DBSingletonConnection dbConnection;
	
	private Connection getConnection() {
        return dbConnection.getConnection();
    }

    public LoginDAO() {
    	dbConnection = DBSingletonConnection.getInstance();
    }

    private static final String CHECK_LOGIN_SQL = "SELECT emp_id FROM user_accounts WHERE UserName = ? AND Password = ?";
    private static final String INSERT_LOGIN_SQL = "INSERT INTO user_accounts (emp_id, UserName, Password) VALUES (?, ?, ?)";
    private static final String SELECT_LOGIN_BY_EMP_ID = "SELECT * FROM user_accounts WHERE emp_id = ?";
    private static final String SELECT_ALL_LOGINS = "SELECT * FROM user_accounts";
    private static final String DELETE_LOGIN_SQL = "DELETE FROM user_accounts WHERE emp_id = ?";
    private static final String UPDATE_LOGIN_SQL = "UPDATE user_accounts SET UserName = ?, Password = ? WHERE emp_id = ?";
    
    public int checkLogin(String userName, String password) {
        int empId = -1;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LOGIN_SQL)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                empId = rs.getInt("emp_id");
                System.out.println("in dao " + empId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empId;
    }

    public void addLogin(Login login) throws SQLException {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOGIN_SQL)) {
            preparedStatement.setInt(1, login.getEmp_id());
            preparedStatement.setString(2, login.getUserName());
            preparedStatement.setString(3, login.getPassword());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Login getLoginByEmpId(int empId) {
        Login login = null;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOGIN_BY_EMP_ID);
            preparedStatement.setInt(1, empId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String userName = rs.getString("UserName");
                String password = rs.getString("Password");
                login = new Login(id, empId, userName, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return login;
    }

    public List<Login> selectAllLogins() {
        List<Login> loginsList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOGINS);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int empId = rs.getInt("emp_id");
                String userName = rs.getString("UserName");
                String password = rs.getString("Password");
                loginsList.add(new Login(id, empId, userName, password));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginsList;
    }

    public boolean deleteLogin(int empId) throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_LOGIN_SQL)) {
            statement.setInt(1, empId);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean updateLogin(Login login) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_LOGIN_SQL)) {
            statement.setString(1, login.getUserName());
            statement.setString(2, login.getPassword());
            statement.setInt(3, login.getEmp_id());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
}

