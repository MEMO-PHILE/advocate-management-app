package com.amdocs.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.amdocs.dao.AdvocateDao;
import com.amdocs.dao.Impl.AdvocateDaoImpl;
import com.amdocs.dao.Impl.AppointmentDaoImpl;
import com.amdocs.entity.Appointment;

/*
 * business layer class to take required input from user  
 */

public class AppointmentService {
	
	private Scanner scanner;
	private AppointmentDaoImpl appointmentDao;
	private AdvocateDao advocateDao;
	
	public AppointmentService() {
		scanner = new Scanner(System.in);
		appointmentDao = new AppointmentDaoImpl();
		advocateDao = new AdvocateDaoImpl();
	}
	
	public void bookAppointment() throws NumberFormatException, SQLException{
		
		System.out.print("\nEnter your username : ");
    	String customerUsername = scanner.nextLine();
    	
    	System.out.println("\nAvailable advocates :");
    	advocateDao.displayAllAdvocate();
    	System.out.print("\nEnter advocate id : ");
    	int advocateId;
    	
    	try {
    		advocateId = Integer.parseInt(scanner.nextLine());
    	}
    	catch(NumberFormatException e) {
    		throw e;
    	}
    	
    	Appointment appointment = new Appointment();
    	
    	appointment.setCustomerUsername(customerUsername);
    	appointment.setAdvocateId(advocateId);
    	
    	boolean isInsertionSuccessful;
		try {
			isInsertionSuccessful = appointmentDao.bookAppointment(appointment);
		} catch (SQLException e) {
			throw e;
		}
    	
    	if(isInsertionSuccessful)
    		System.out.println("\nAppointment booked!");
    	else
    		System.out.println("\nBooking failed.");	
    	
	}
	
	public void displayAppointmentById() throws NumberFormatException,SQLException {
		System.out.print("\nEnter appointment id : ");
		
		int appointmentId;
		Appointment appointment;
		
    	try{
    		appointmentId = Integer.parseInt(scanner.nextLine());
    		appointment = appointmentDao.getAppointmentById(appointmentId);
		}
		catch(NumberFormatException | SQLException e) {
			throw e;
		}
    	
    	if(appointment!=null) {
    		System.out.println("\nAppointment booked with id "+appointmentId+" :");
    		System.out.printf("%n%-15s %-25s %-20s %s%n%n","Appointment ID","Customer Username","Advocate ID","Appointment Time");
        	System.out.printf("%-15s %-25s %-20s %s%n",appointment.getAppointmentId(),appointment.getCustomerUsername(),appointment.getAdvocateId(),appointment.getAppointmentDate());
        	
    	}
    	else
    		System.out.println("\nNo appointment booked with id "+appointmentId+".");
	}
	
	public void displayAllAppointment() throws SQLException {
		List<Appointment> appointmentList;
		try {
			appointmentList = appointmentDao.getAllAppointment();
		} catch (SQLException e) {
			throw e;
		}
    	
    	if(appointmentList.size()==0) {
    		System.out.println("\nNo appointments booked!");
    		return;
    	}
    	
    	System.out.printf("%n%-15s %-25s %-20s %s%n%n","Appointment ID","Customer Username","Advocate ID","Appointment Time");
    	for(Appointment appointment:appointmentList) {
    		System.out.printf("%-15s %-25s %-20s %s%n",appointment.getAppointmentId(),appointment.getCustomerUsername(),appointment.getAdvocateId(),appointment.getAppointmentDate());
    	}
	}
	
	public void displayAppointmentByUsername() throws SQLException {
		
		System.out.print("\nEnter username : ");
		String username = scanner.nextLine();
		
		List<Appointment> appointmentList;
		try {
			appointmentList = appointmentDao.getAppointmentByUsername(username);
		} catch (SQLException e) {
			throw e;
		}
		
    	if(appointmentList.size()==0) {
    		System.out.println("\nNo appointments booked by username "+username+".");
    		return;
    	}
    	
    	
    	System.out.println("\nAppointment history of user " +username+":");
    	System.out.printf("%n%-15s %-25s %-20s %s%n%n","Appointment ID","Customer Username","Advocate ID","Appointment Time");
    	for(Appointment appointment:appointmentList) {
    		System.out.printf("%-15s %-25s %-20s %s%n",appointment.getAppointmentId(),appointment.getCustomerUsername(),appointment.getAdvocateId(),appointment.getAppointmentDate());
    	}
		
	}
	
	public void cancelAppointment()  throws NumberFormatException,SQLException{
		
    	int appointmentId,isDeletionSuccessful;
    	
    	System.out.print("\nEnter appointment id : ");
    	
    	try {
    		appointmentId = Integer.parseInt(scanner.nextLine());
    		isDeletionSuccessful = appointmentDao.cancelAppointment(appointmentId);
    	}
    	catch(NumberFormatException | SQLException e) {
			throw e;
		}
    	
    	if(isDeletionSuccessful>0)
    		System.out.println("\nAppointment cancelled.");
    	else
    		System.out.println("\nNo Appointment exist with id "+appointmentId+".");
	}
	
	public void updateAppointment() throws NumberFormatException,SQLException{
		
		int appointmentId,advocateId,isUpdationSuccessful;
		
		try {
			System.out.print("\nEnter appointment id : ");
	    	appointmentId = Integer.parseInt(scanner.nextLine());
	    	
	    	System.out.println("\nAvailable advocates : ");
	    	advocateDao.displayAllAdvocate();
	    
	    	System.out.print("\nEnter new advocate id : ");
	    	advocateId = Integer.parseInt(scanner.nextLine());
	    	
	    	isUpdationSuccessful=appointmentDao.updateAppointment(appointmentId,advocateId);
		}
		catch(NumberFormatException | SQLException e) {
			throw e;
		}
		
		if(isUpdationSuccessful>0)
    		System.out.println("\nAppointment updated!");
    	else
    		System.out.println("\nNo appointment found with id "+appointmentId+".");
	}
	
	public void closeConnection() throws SQLException {
	    scanner.close();
	   	try {
			appointmentDao.closeDBConnection();
		} catch (SQLException e) {
			throw e;
		}
	}
}
