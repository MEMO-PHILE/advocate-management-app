package com.amdocs.dao.Impl;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.amdocs.dao.AdvocateDao;
import com.amdocs.util.DatabaseUtil;

/*
 * Data access class to perform SQL queries on Advocate table
 */

public class AdvocateDaoImpl implements AdvocateDao {

	Connection connection;
	PreparedStatement preparedStatement;
	
	public AdvocateDaoImpl() {
		connection= DatabaseUtil.getConnection();
	}
	
	@Override
	public void displayAllAdvocate() throws SQLException {
		
		boolean isRecordExist=false;
		
		try {
			preparedStatement=connection.prepareStatement("SELECT * FROM advocate");
			
			ResultSet resultSet	= preparedStatement.executeQuery();
			
			System.out.printf("%n%-15s %-20s %-20s %-20s %s%n%n","Advocate ID","Full Name","Mobile Number","Specialization","Rating");
			while(resultSet.next()) {
				System.out.printf("%-15d %-20s %-20s %-20s %s%n",resultSet.getInt("advocate_id"),resultSet.getString("full_name"),resultSet.getString("mobile_number"),resultSet.getString("specialization"),resultSet.getString("rating"));
				isRecordExist=true;
			}
			
			resultSet.close();
			preparedStatement.close();
			
			
		} 
		catch (SQLException e) {
			throw e;
		}
		
    	if(!isRecordExist)
    		System.out.println("\nNo customer record found!\n");
    		
	}
	
	public void closeDBConnection() throws SQLException {
		try {
			connection.close();
		} catch (SQLException e) {
			throw e;
		}
	}

}
