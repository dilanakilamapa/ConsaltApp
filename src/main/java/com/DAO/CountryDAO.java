package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Model.Country;
import com.dbConnection.DBSingletonConnection;

public class CountryDAO {
private DBSingletonConnection dbConnection;
	
	private Connection getConnection() {
        return dbConnection.getConnection();
    }

    public CountryDAO() {
    	dbConnection = DBSingletonConnection.getInstance();
    }

    private static final String INSERT_COUNTRY_SQL = "INSERT INTO country (country_name) VALUES (?)";
    private static final String SELECT_COUNTRY_BY_ID = "SELECT * FROM country WHERE id = ?";
    private static final String SELECT_ALL_COUNTRIES = "SELECT * FROM country";
    private static final String DELETE_COUNTRY_SQL = "DELETE FROM country WHERE id = ?";
    private static final String UPDATE_COUNTRY_SQL = "UPDATE country SET country_name = ? WHERE id = ?";

    public void addCountry(Country country) throws SQLException {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COUNTRY_SQL)) {
            preparedStatement.setString(1, country.getName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Country getCountryById(int countryId) {
        Country country = null;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNTRY_BY_ID);
            preparedStatement.setInt(1, countryId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("country_name");
                country = new Country(id, name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return country;
    }

    public List<Country> selectAllCountries() {
        List<Country> countryList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COUNTRIES);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("country_name");
                countryList.add(new Country(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countryList;
    }

    public boolean deleteCountry(int countryId) throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_COUNTRY_SQL)) {
            statement.setInt(1, countryId);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean updateCountry(Country country) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_COUNTRY_SQL)) {
            statement.setString(1, country.getName());
            statement.setInt(2, country.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
}
