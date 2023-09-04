package com.service;

import java.util.List;

import com.DAO.ConsultantAvailabilityDAO;
import com.DAO.LoginDAO;
import com.DAO.RoleDAO;
import com.DAO.UserDAO;
import com.Model.ConsultantAvailability;
import com.Model.Role;
import com.Model.User;

public class LoginService {
    private LoginDAO loginDAO;
    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private ConsultantAvailabilityDAO consultantAvailabilityDAO;

    public LoginService() {
        loginDAO = new LoginDAO();
        userDAO = new UserDAO();
        roleDAO = new RoleDAO();
        consultantAvailabilityDAO = new ConsultantAvailabilityDAO();
    }
    
    public List<ConsultantAvailability> getAllConsultantAvailabilities(int user_ID) {
        return consultantAvailabilityDAO.selectAllConsultantAvailabilitiesWithName(user_ID);
    }

    public int checkLogin(String userName, String password) {
    	int empId = loginDAO.checkLogin(userName, password);
        return empId;
    }
    
    public User getUserbyID(int empId) {
    	if (empId != -1) {
            return userDAO.getUserById(empId);
        } else {
            return null;
        }
    }

    public Role getRoleById(int roleId) {
        return roleDAO.getRoleById(roleId);
    }
}
