package com.amdocs.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amdocs.dao.CustomerDao;
import com.amdocs.entity.Customer;
import com.amdocs.util.DatabaseUtil;

/*
 * Data access class to perform SQL queries on customer table
 */

public class CustomerDaoImpl implements CustomerDao{

	Connection connection;
	PreparedStatement preparedStatement;
	
	public CustomerDaoImpl() {
		connection= DatabaseUtil.getConnection();
	}
	
	@Override
	public boolean loginCustomer(String username, String password) throws SQLException{
		
		boolean isCustomerExist=false;
		
		try {
			preparedStatement=connection.prepareStatement("SELECT * FROM customer WHERE username=? AND login_password=?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			ResultSet resultSet	= preparedStatement.executeQuery();
			
			if(resultSet.next())
				isCustomerExist=true;
			
			resultSet.close();
			preparedStatement.close();
			
			
		} catch (SQLException e) {
			throw e;
		}
		
		return isCustomerExist;
	}

	@Override
	public boolean registerCustomer(Customer customer) throws SQLException{
		
		int isInsertionSuccessful=-1;
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?)");
			preparedStatement.setString(1, customer.getFullName());
			preparedStatement.setString(2, customer.getMobileNumber());
			preparedStatement.setString(3, customer.getAddress());
			preparedStatement.setString(4, customer.getUsername());
			preparedStatement.setString(5, customer.getPassword());
			
			isInsertionSuccessful = preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
		} catch (SQLException e) {
			throw e;
		}
	
		return isInsertionSuccessful > 0;
		
	}
	

	@Override
	public Customer getSingleCustomer(String username) throws SQLException{
		Customer customer = new Customer();
		
		try {
			preparedStatement=connection.prepareStatement("SELECT * FROM customer WHERE username=?");
			preparedStatement.setString(1, username);
			
			ResultSet resultSet	= preparedStatement.executeQuery();
			
			if(resultSet.next()){
				customer.setFullName(resultSet.getString("full_name"));
				customer.setMobileNumber(resultSet.getString("mobile_number"));
				customer.setAddress(resultSet.getString("address"));
				customer.setUsername(resultSet.getString("username"));
				customer.setPassword(resultSet.getString("login_password"));
			}
			else
				customer=null;
			
			resultSet.close();
			preparedStatement.close();
			
			
		} catch (SQLException e) {
			throw e;
		}
		
		return customer;
	}

	@Override
	public List<Customer> getAllCustomer() throws SQLException{
		
		List<Customer> customerList = new ArrayList<>();
		Customer customer;

		try {
			preparedStatement=connection.prepareStatement("SELECT * FROM customer");
			
			ResultSet resultSet	= preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				customer=new Customer();
				customer.setFullName(resultSet.getString("full_name"));
				customer.setMobileNumber(resultSet.getString("mobile_number"));
				customer.setAddress(resultSet.getString("address"));
				customer.setUsername(resultSet.getString("username"));
				customer.setPassword(resultSet.getString("login_password"));
				
				customerList.add(customer);
			}
			
			resultSet.close();
			preparedStatement.close();
			
			
		} catch (SQLException e) {
			throw e;
		}
		
		return customerList;
	}

	@Override
	public int deleteCustomer(String username) throws SQLException{
		
		int isDeletionSuccessful=-1;
		try {
			preparedStatement=connection.prepareStatement("DELETE FROM customer WHERE username=?");
			preparedStatement.setString(1, username);
			
			isDeletionSuccessful = preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
			
		} catch (SQLException e) {
			throw e;
		}
		
		return isDeletionSuccessful;
	}

	@Override
	public int updateCustomer(String username,String field, String value) throws SQLException{
		int isUpdationSuccessful=-1;
		try {
			preparedStatement=connection.prepareStatement("UPDATE customer SET "+field+"=? WHERE username=?");
			preparedStatement.setString(1,value);
			preparedStatement.setString(2,username);
			isUpdationSuccessful = preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
			
		} catch (SQLException e) {
			throw e;
		}
		
		return isUpdationSuccessful;
	}
	
	public void closeDBConnection() throws SQLException {
		try {
			connection.close();
		} catch (SQLException e) {
			throw e;
		}
	}

}
