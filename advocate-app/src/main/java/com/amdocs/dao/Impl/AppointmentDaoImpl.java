package com.amdocs.dao.Impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.amdocs.dao.AppointmentDao;
import com.amdocs.entity.Appointment;
import com.amdocs.util.DatabaseUtil;

/*
 * Data access class to perform SQL queries on Appointment table
 */

public class AppointmentDaoImpl implements AppointmentDao {

	Connection connection;
	PreparedStatement preparedStatement;
	
	public AppointmentDaoImpl() {
		connection= DatabaseUtil.getConnection();
	}
	
	@Override
	public boolean bookAppointment(Appointment appointment) throws SQLException {
		int isInsertionSuccessful=-1;
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO appointment(customer_username,advocate_id,appointment_date) VALUES(?,?,NOW())");
			preparedStatement.setString(1, appointment.getCustomerUsername());
			preparedStatement.setInt(2, appointment.getAdvocateId());
			
			isInsertionSuccessful = preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
		} catch (SQLException e) {
			throw e;
		}
	
		return isInsertionSuccessful > 0;
		
	}
	
	@Override
	public Appointment getAppointmentById(int appointmentId) throws SQLException {
		Appointment appointment = new Appointment();
		
		try {
			preparedStatement=connection.prepareStatement("SELECT * FROM appointment WHERE appointment_id=?");
			preparedStatement.setInt(1, appointmentId);
			
			ResultSet resultSet	= preparedStatement.executeQuery();
			
			if(resultSet.next()){
				appointment.setAppointmentId(resultSet.getInt("appointment_id"));
				appointment.setCustomerUsername(resultSet.getString("customer_username"));
				appointment.setAdvocateId(resultSet.getInt("advocate_id"));
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				appointment.setAppointmentDate(dateFormat.format(resultSet.getTimestamp("appointment_date")));
				
			}
			else
				appointment=null;
			
			resultSet.close();
			preparedStatement.close();
			
			
		} catch (SQLException e) {
			throw e;
		}
		
		return appointment;
	}
    
	@Override
	public List<Appointment> getAllAppointment() throws SQLException {
		List<Appointment> appointmentList = new ArrayList<>();
		Appointment appointment;

		try {
			preparedStatement=connection.prepareStatement("SELECT * FROM appointment");
			
			ResultSet resultSet	= preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				appointment = new Appointment();
				
				appointment.setAppointmentId(resultSet.getInt("appointment_id"));
				appointment.setCustomerUsername(resultSet.getString("customer_username"));
				appointment.setAdvocateId(resultSet.getInt("advocate_id"));
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				appointment.setAppointmentDate(dateFormat.format(resultSet.getTimestamp("appointment_date")));
				
				appointmentList.add(appointment);
			}
			
			resultSet.close();
			preparedStatement.close();
			
			
		} catch (SQLException e) {
			throw e;
		}
		
		return appointmentList;
	}
	
	@Override
	public List<Appointment> getAppointmentByUsername(String username) throws SQLException {
		
		List<Appointment> appointmentList = new ArrayList<>();
		Appointment appointment;

		try {
			preparedStatement=connection.prepareStatement("SELECT * FROM appointment WHERE customer_username=? ORDER BY appointment_date DESC");
			preparedStatement.setString(1, username);
			ResultSet resultSet	= preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				appointment = new Appointment();
				
				appointment.setAppointmentId(resultSet.getInt("appointment_id"));
				appointment.setCustomerUsername(resultSet.getString("customer_username"));
				appointment.setAdvocateId(resultSet.getInt("advocate_id"));
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				appointment.setAppointmentDate(dateFormat.format(resultSet.getTimestamp("appointment_date")));
				
				appointmentList.add(appointment);
			}
			
			resultSet.close();
			preparedStatement.close();
			
			
		} catch (SQLException e) {
			throw e;
		}
		
		return appointmentList;
	}
	
	@Override
	public int updateAppointment(int appointmentId,int advocateId) throws SQLException {
		int isUpdationSuccessful=-1;
		try {
			preparedStatement=connection.prepareStatement("UPDATE appointment SET advocate_id=?,appointment_date=NOW() WHERE appointment_id=?");
			preparedStatement.setInt(1,advocateId);
			preparedStatement.setInt(2,appointmentId);
			isUpdationSuccessful = preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
			
		} catch (SQLException e) {
			throw e;
		}
		
		return isUpdationSuccessful;
	}

	@Override
	public int cancelAppointment(int appointmentId) throws SQLException {
		int isDeletionSuccessful=-1;
		try {
			preparedStatement=connection.prepareStatement("DELETE FROM appointment WHERE appointment_id=?");
			preparedStatement.setInt(1, appointmentId);
			
			isDeletionSuccessful = preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
			
		} catch (SQLException e) {
			throw e;
		}
		
		return isDeletionSuccessful;
	}

	public void closeDBConnection() throws SQLException {
		try {
			connection.close();
		} catch (SQLException e) {
			throw e;
		}
	}


}
