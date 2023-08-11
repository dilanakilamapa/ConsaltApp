package com.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
	private String jdbcURL = "jdbc:mysql://localhost:3306/db_appointment?useSSL=false";
	private String JdbcUsername = "root";
	private String jdbcPassword = "hy6Per@lex";

	public Connection getConnection(){
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL,JdbcUsername,jdbcPassword);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
