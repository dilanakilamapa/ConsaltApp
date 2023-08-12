package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.Model.Role;
import com.dbConnection.*;

public class RoleDAO {
    private dbConnection dbConnection;

    public RoleDAO() {
        dbConnection = new dbConnection();
    }

    private static final String INSERT_ROLE_SQL = "INSERT INTO rols (Role_name) VALUES (?)";
    private static final String SELECT_ROLE_BY_ID = "SELECT * FROM rols WHERE id = ?";
    private static final String SELECT_ALL_ROLE = "SELECT * FROM rols";

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
    
    public List<Role> selectAllRoles() {
        List<Role> rolesList = new ArrayList<>();
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROLE);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String role_name = rs.getString("Role_name");
                rolesList.add(new Role(id, role_name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rolesList;
    }
}
