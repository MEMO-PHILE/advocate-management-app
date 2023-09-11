package com.amdocs.dao;

import java.sql.SQLException;
import java.util.List;

import com.amdocs.entity.Customer;

public interface CustomerDao {
	boolean loginCustomer(String username,String password) throws SQLException;
	boolean registerCustomer(Customer customer)throws SQLException;
	Customer getSingleCustomer(String username) throws SQLException;
	int deleteCustomer(String username) throws SQLException;
	List<Customer> getAllCustomer() throws SQLException;
	int updateCustomer(String username,String field,String value) throws SQLException;
}
