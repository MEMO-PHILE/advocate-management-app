package com.amdocs.dao;

import java.sql.SQLException;
import java.util.List;

import com.amdocs.entity.Appointment;

public interface AppointmentDao {
	boolean bookAppointment(Appointment appointment) throws SQLException;
	Appointment getAppointmentById(int appointmentId) throws SQLException;
	List<Appointment> getAllAppointment() throws SQLException;
	List<Appointment> getAppointmentByUsername(String username) throws SQLException;
	int cancelAppointment(int appointmentId) throws SQLException;
	int updateAppointment(int appointmentId,int advocateId) throws SQLException;
}
