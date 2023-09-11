package com.amdocs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * This class establishes connection with database
 */

public class DatabaseUtil {
	private static final String URL="jdbc:mysql://localhost:3306/advocateapp";
	private static final String USER_NAME="root";
	private static final String PASSWORD="root";

	private static Connection connection;	
	
	public static Connection getConnection() {
		try {
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		} catch (SQLException e) {
			System.err.println("Error : Database connection not established.\n" + e );
		}
		return connection;
	}
}
