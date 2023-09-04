package com.service;

import java.sql.SQLException;
import java.util.List;

import com.DAO.CountryDAO;
import com.DAO.JobDAO;
import com.DAO.SpecializationDAO;
import com.DAO.UserDAO;
import com.Model.Country;
import com.Model.Job;
import com.Model.User;
import com.Model.specialization;
import com.google.gson.Gson;

public class SpecializationService {
    private SpecializationDAO specializationDAO;
    private UserDAO userDAO;
    private CountryDAO countryDAO;
    private JobDAO jobDAO;

    public SpecializationService() {
        specializationDAO = new SpecializationDAO();
        userDAO = new UserDAO();
        countryDAO = new CountryDAO();
        jobDAO = new JobDAO();
    }

    public List<specialization> listSpecializations() {
        return specializationDAO.selectAllSpecializationswithname();
    }

    public List<User> getAllConsultants() {
        return userDAO.selectAllConsultant();
    }

    public List<Country> getAllCountries() {
        return countryDAO.selectAllCountries();
    }

    public List<Job> getAllJobs() {
        return jobDAO.selectAllJobs();
    }

    public specialization getSpecializationById(int specId) {
        return specializationDAO.getSpecializationById(specId);
    }

    public void addSpecialization(specialization spec) throws SQLException {
         specializationDAO.addSpecialization(spec);
    }

    public boolean updateSpecialization(specialization spec) throws SQLException {
        return specializationDAO.updateSpecialization(spec);
    }

    public boolean deleteSpecialization(int specId) throws SQLException {
        return specializationDAO.deleteSpecialization(specId);
    }

    public List<specialization> getSpecializationsByUser(int userId) {
        return specializationDAO.SELECT_SPECIALIZATION_COUNTRY_BY_USER_ID(userId);
    }

    public List<specialization> getSpecializationsByCountry(int countryId) {
        return specializationDAO.SELECT_SPECIALIZATION_COUNTRY_BY_COUNTRY_ID(countryId);
    }

    public List<specialization> getSpecializationsByUserAndCountry(int userId, int countryId) {
        return specializationDAO.SELECT_SPECIALIZATION_USER_USING_COUNTRY_AND_JOB(userId, countryId);
    }

    public String getSpecializationsAsJson(List<specialization> specializations) {
        return new Gson().toJson(specializations);
    }
}
