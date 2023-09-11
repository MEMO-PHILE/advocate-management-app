package com.amdocs;

import java.sql.SQLException;
import java.util.Scanner;
import com.amdocs.exception.InvalidMobileNumberException;
import com.amdocs.exception.InvalidNameException;
import com.amdocs.service.AdvocateService;
import com.amdocs.service.AppointmentService;
import com.amdocs.service.CustomerService;

/*
 * Project : Advocate Management System
 * 
 * Description : This applications simulates advocate management system.
 * It allows users to register,login,book appointment with advocate as well as update or cancel their bookings 
 * as per their need.
 */


public class App 
{
	private static Scanner scanner;
	private static CustomerService customerService;
	private static AdvocateService advocateService;
	private static AppointmentService appointmentService;
	
    public static void main( String[] args )
    {
    	
    	scanner = new Scanner(System.in);
    	customerService=new CustomerService();
    	advocateService=new AdvocateService();
    	appointmentService=new AppointmentService();
    	
    	System.out.println("ADVOCATE APPOINTMENT SYSTEM");
    	displayMainMenu();
    	closeConnection();
    	System.out.println("\nSystem terminated!");
    	
    }
    
    //display main menu of the application
	private static void displayMainMenu() 
    {	
    	int userChoice=-1;
    	
    	do {
    		System.out.println("\nMAIN MENU:\n");
    		System.out.println("Login.................Press 1");
    		System.out.println("Customer..............Press 2");
    		System.out.println("Advocate..............Press 3");
    		System.out.println("Appointment...........Press 4");
    		System.out.println("Services..............Press 5");
    		System.out.println("Exit..................Press 0");
    		System.out.print("\nSelect your option : ");
    		
    		try {
    			userChoice=Integer.parseInt(scanner.nextLine());
    		
	    		switch(userChoice) {
	    			case 1:	customerService.customerLogin();
	    					break;
	    			case 2: displayCustomerMenu();
	    					break;
	    			case 3:	advocateService.displayAllAdvocate();
	    					break;
	    			case 4:	displayAppointmentMenu();
	    					break;
	    			case 5: break;
	    			case 0:	
	    					break;
	    			default : System.out.println("\nWrong choice! Please choose between 0-5.");   					  
	    		}
    		}
    		catch(NumberFormatException e) {
    			System.err.println("\nERROR : enter correct input.");
    		}
    		catch(SQLException e) {
    			System.err.println("\nERROR : SQL query execution failed.");
    		}
    	}
    	
    	while(userChoice!=0);
    }
    
	//display customer menu
    private static void displayCustomerMenu() {
    	int customerChoice=-1;
    	
    	do {
    		System.out.println("\nCUSTOMER MENU:\n");
    		System.out.println("Register Customer................Press 1");
    		System.out.println("Update Customer Record...........Press 2");
    		System.out.println("Delete Customer Record...........Press 3");
    		System.out.println("View Single Record...............Press 4");
    		System.out.println("View All Records.................Press 5");
    		System.out.println("Exit.............................Press 0");
    		System.out.print("\nSelect your option : ");
    		
    		try {
    			customerChoice=Integer.parseInt(scanner.nextLine());
    			
	    		switch(customerChoice) {
	    			case 1:	customerService.registerCustomer();
							break;
	    			case 2:	customerService.updateCustomer();
	    					break;
	    			case 3:	customerService.deleteCustomer();
	    					break;
	    			case 4:	customerService.displaySingleCustomer();
	    					break;
	    			case 5: customerService.displayAllCustomer();
	    					break;
	    			case 0:	break;
	    			default : System.out.println("\nWrong choice! Please choose between 0-5.");   					  
	    		}
    		}
    		catch(NumberFormatException e) {
    			System.err.println("\nERROR : enter correct input.");
    		}
    		catch (InvalidNameException | InvalidMobileNumberException e) {
				System.err.println("\nERROR : "+ e.getMessage());
			}
    		catch(SQLException e) {
    			System.err.println("\nERROR : SQL query execution failed.");
    		}
	
    	}
    	
    	while(customerChoice!=0);
    }
    
    //display appointment menu
    private static void displayAppointmentMenu() {
    	int customerChoice=-1;
    	
    	do {
    		System.out.println("\nAPPOINTMENT MENU:\n");
    		System.out.println("Book Appointment.....................Press 1");
    		System.out.println("Modify Appointment...................Press 2");
    		System.out.println("Cancel Appointment...................Press 3");
    		System.out.println("View Appointment By ID...............Press 4");
    		System.out.println("View Appointment By Username.........Press 5");
    		System.out.println("View all Appointments................Press 6");
    		System.out.println("Exit.................................Press 0");
    		System.out.print("\nSelect your option : ");
    		
    		try {
    			customerChoice=Integer.parseInt(scanner.nextLine());
    						
	    		switch(customerChoice) {
	    			case 1:	appointmentService.bookAppointment();
	    					break;
	    			case 2:	appointmentService.updateAppointment();
	    					break;
	    			case 3:	appointmentService.cancelAppointment();
	    					break;
	    			case 4:	appointmentService.displayAppointmentById();
	    					break;
	    			case 5: appointmentService.displayAppointmentByUsername();
	    					break;
	    			case 6: appointmentService.displayAllAppointment();
	    			case 0:	break;
	    			default : System.out.println("\nWrong choice! Please choose between 0-6.");   					  
	    		}
    		}
    		catch(NumberFormatException e) {
    			System.err.println("\nERROR : enter correct input.");
    		}
    		catch(SQLException e) {
    			System.err.println("\nERROR : SQL query execution failed.");
    		}
    	}
    	
    	while(customerChoice!=0);
    }

    
    private static void closeConnection() {
    	scanner.close();
    	
    	try {
    	customerService.closeConnection();
    	advocateService.closeConnection();
    	appointmentService.closeConnection();
    	}
    	catch(SQLException e) {
    		System.err.println("\nERROR : Unable to close database connection.");
    	}
    }
    
    
    
    
}
