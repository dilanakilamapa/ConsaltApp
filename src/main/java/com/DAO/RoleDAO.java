package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.Model.Role;
import com.dbConnection.*;

public class RoleDAO {
    private dbConnection dbConnection;

    public RoleDAO() {
        dbConnection = new dbConnection();
    }

    private static final String INSERT_ROLE_SQL = "INSERT INTO rols (Role_name) VALUES (?)";
    private static final String SELECT_ROLE_BY_ID = "SELECT * FROM rols WHERE id = ?";

    public void addRole(Role role) throws SQLException {
        try (Connection connection = dbConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROLE_SQL)) {
            preparedStatement.setString(1, role.getRole_name());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Role getRoleById(int roleId) {
        Role role = null;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE_BY_ID);
            preparedStatement.setInt(1, roleId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String role_name = rs.getString("Role_name");
                role = new Role(roleId, role_name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }
}
