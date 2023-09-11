package com.amdocs.service;

import java.sql.SQLException;
import com.amdocs.dao.Impl.AdvocateDaoImpl;

/*
 * business layer class to take required input from user  
 */

public class AdvocateService {
	private AdvocateDaoImpl advocateDao;
	
	public AdvocateService() {
		advocateDao=new AdvocateDaoImpl();
	}
	
	public void displayAllAdvocate() throws SQLException {
		try {
			advocateDao.displayAllAdvocate();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void closeConnection() throws SQLException {
		try {
			advocateDao.closeDBConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
}
