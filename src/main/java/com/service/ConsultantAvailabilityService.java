package com.service;

import com.DAO.ConsultantAvailabilityDAO;
import com.DAO.UserDAO;
import com.Model.ConsultantAvailability;
import com.Model.User;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ConsultantAvailabilityService {
    private ConsultantAvailabilityDAO consultantAvailabilityDAO;
    private UserDAO userDAO;

    public ConsultantAvailabilityService() {
        consultantAvailabilityDAO = new ConsultantAvailabilityDAO();
        userDAO = new UserDAO();
    }

    public List<ConsultantAvailability> getAllConsultantAvailabilities(int user_ID) {
        return consultantAvailabilityDAO.selectAllConsultantAvailabilitiesWithName(user_ID);
    }

    public ConsultantAvailability getConsultantAvailabilityById(int availabilityId) {
        return consultantAvailabilityDAO.getConsultantAvailabilityById(availabilityId);
    }

    public void addConsultantAvailability(ConsultantAvailability availability) throws SQLException {
        consultantAvailabilityDAO.addConsultantAvailability(availability);
    }

    public Boolean updateConsultantAvailability(ConsultantAvailability availability) throws SQLException {
    	consultantAvailabilityDAO.updateConsultantAvailability(availability);
    	return true;
        		
    }

    public void deleteConsultantAvailability(int availabilityId) throws SQLException {
        consultantAvailabilityDAO.deleteConsultantAvailability(availabilityId);
    }

    public List<User> getAllConsultants() {
        return userDAO.selectAllConsultant();
    }

    public List<ConsultantAvailability> getAvailabilityByDate(Date date) {
        return consultantAvailabilityDAO.SELECT_START_AND_END_TIME_BY_DATE(date);
    }

	public void setConsultantAvailabilityDAO(ConsultantAvailabilityDAO consultantAvailabilityDAO2) {
		consultantAvailabilityDAO = consultantAvailabilityDAO2;
		
	}

	public void setUserDAO(UserDAO userDAO2) {
		userDAO = userDAO2;
		
	}
}
