package com.service;

import java.sql.SQLException;
import java.util.List;
import com.DAO.RoleDAO;
import com.DAO.UserDAO;
import com.Model.Role;
import com.Model.User;


public class UserService {
    private UserDAO userDAO;
    private RoleDAO roleDAO;

    public UserService() {
        userDAO = new UserDAO();
        roleDAO = new RoleDAO();
    }

    public List<User> listUsers() {
        return userDAO.selectAllUsers();
    }

    public List<Role> listRoles() {
        return roleDAO.selectAllRoles();
    }

    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    public void addUser(User user) throws SQLException {
         userDAO.addUser(user);
    }

    public boolean updateUser(User user) throws SQLException {
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(int userId) throws SQLException {
        return userDAO.deleteUser(userId);
    }
}
