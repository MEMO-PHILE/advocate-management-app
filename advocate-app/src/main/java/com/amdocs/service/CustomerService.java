package com.amdocs.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.amdocs.dao.Impl.CustomerDaoImpl;
import com.amdocs.entity.Customer;
import com.amdocs.exception.InvalidMobileNumberException;
import com.amdocs.exception.InvalidNameException;
import com.amdocs.util.ValidateUtil;

/*
 * business layer class to take required input from user  
 */

public class CustomerService {
	private Scanner scanner;
	private CustomerDaoImpl customerDao;
	
	public CustomerService() {
		scanner = new Scanner(System.in);
		customerDao = new CustomerDaoImpl();
	}
	
	public void customerLogin() throws SQLException{
    	
    	System.out.print("\nEnter username : ");
	   	String username = scanner.nextLine();
	    System.out.print("Enter password : ");
	    String password = scanner.nextLine();
		 
	    boolean isLoginSuccessful;
	    try {
		isLoginSuccessful=customerDao.loginCustomer(username,password);
	    }
	    catch(SQLException e) {
	    	throw e;
	    }
		 if(isLoginSuccessful)
			 System.out.println("\nLogin successful.");
		 else
			 System.out.println("\nInvalid username or password.");
    }
	
	public void registerCustomer() throws InvalidNameException,InvalidMobileNumberException,SQLException{
    	
		String fullName,mobileNumber;
		
		try {
			System.out.print("\nEnter your full name : ");
			fullName = scanner.nextLine();
			
			if(!ValidateUtil.isValidName(fullName))
				throw new InvalidNameException("Invalid name format");
			
			System.out.print("Enter your mobile number : ");
			mobileNumber = scanner.nextLine();
			
			if(!ValidateUtil.isValidMobileNumber(mobileNumber))
				throw new InvalidMobileNumberException("Invalid mobile number format");
		}
		catch(InvalidNameException | InvalidMobileNumberException e) {
			throw e;
		}
		
    	System.out.print("Enter your address : ");
    	String address = scanner.nextLine();
    	System.out.print("Enter your username : ");
    	String username = scanner.nextLine();
    	System.out.print("Enter your password : ");
    	String password = scanner.nextLine();
    	
    	boolean isInsertionSuccessful;
    	try {
			if(checkIfCustomerExist(username)) {
	    		System.out.println("\nA customer with same username already exist.\n");
	    		return;
	    	}
	    	
	    	Customer customer=new Customer(fullName,mobileNumber,address,username,password);
	    	isInsertionSuccessful = customerDao.registerCustomer(customer);
    	}
    	catch(SQLException e) {
    		throw e;
    	}
    	
    	if(isInsertionSuccessful)
    		System.out.println("\nRegistration Successful.");
    	else
    		System.out.println("\nRegistration failed.");	
    	
    }
    
    private boolean checkIfCustomerExist(String username) throws SQLException {
    	Customer customer;
		try {
			customer = customerDao.getSingleCustomer(username);
		} catch (SQLException e) {
			throw e;
		}
    	return customer!=null;
    }
    
    public void displaySingleCustomer() throws SQLException {
    	
    	System.out.print("\nEnter username of customer : ");
    	String username = scanner.nextLine();
    	
    	Customer customer;
		try {
			customer = customerDao.getSingleCustomer(username);
		} catch (SQLException e) {
			throw e;
		}
    	
    	if(customer!=null) {
    		System.out.println("\nCustomer found!");
    		System.out.printf("\n%-20s %-20s %-20s %s%n%n","Username","Full Name","Mobile Number","Address");
    		System.out.printf("%-20s %-20s %-20s %s%n",customer.getUsername(),customer.getFullName(),customer.getMobileNumber(),customer.getAddress());        	
    	}
    	else
    		System.out.println("\nNo customer found with username "+username+".\n");
    		
    }
    
    public void displayAllCustomer() throws SQLException{
    	
    	List<Customer> customerList;
		try {
			customerList = customerDao.getAllCustomer();
		} catch (SQLException e) {
			throw e;
		}
    	
    	if(customerList.size()==0) {
    		System.out.println("\nNo customer record found!");
    		return;
    	}
    	
    	System.out.printf("\n%-20s %-20s %-20s %s%n%n","Username","Full Name","Mobile Number","Address");
    
    	for(Customer customer:customerList) {
    		System.out.printf("%-20s %-20s %-20s %s%n",customer.getUsername(),customer.getFullName(),customer.getMobileNumber(),customer.getAddress());
    	}
    }
    
    public void deleteCustomer() throws SQLException {
    	System.out.print("\nEnter username of customer to delete : ");
    	String username = scanner.nextLine();
    	
    	int isDeletionSuccessful;
		try {
			isDeletionSuccessful = customerDao.deleteCustomer(username);
		} catch (SQLException e) {
			throw e;
		}
    	
    	if(isDeletionSuccessful>0)
    		System.out.println("\nDeleted record of customer "+username+".");
    	else
    		System.out.println("\nNo customer found with username "+username+".");
    }
    
    public void updateCustomer() throws NumberFormatException,InvalidMobileNumberException,InvalidNameException,SQLException{
    	
    	String field=new String();
    	String fieldValue=new String();
    	boolean flag;
    	
    	System.out.print("\nEnter username of customer to update : ");
    	String username = scanner.nextLine();
    	
    	int isUpdationSuccessful;
    	
    	try {
	    	if(!checkIfCustomerExist(username)) {
	    		System.out.println("\nNo customer found with username "+username+".");
	    		return;
	    	}
	    	
	    	do {
	    		flag=false;
		    	switch(displayUpdateMenu()) {
		    		case 1 : 
	    					System.out.print("\nEnter new full name : ");
	    					fieldValue=scanner.nextLine();
	    					
	    					if(!ValidateUtil.isValidName(fieldValue))
	    						throw new InvalidNameException("Invalid name format");
	    					
		    				field="full_name";
		    				break;
		    		case 2 : System.out.print("\nEnter new mobile number : ");
							fieldValue=scanner.nextLine();
							
							if(!ValidateUtil.isValidMobileNumber(fieldValue))
								throw new InvalidMobileNumberException("Invalid mobile number format");
							
							field="mobile_number";
							break;
		    		case 3 : System.out.print("\nEnter new address : ");
							fieldValue=scanner.nextLine();
							field="address";
							break;
		    		case 4 : System.out.print("\nEnter new password : ");
							fieldValue=scanner.nextLine();
							field="login_password";
							break;
					default : System.out.println("\nWrong choice!\nPlease choose between 1-4\n");
								flag=true;
		    	}
	    	}
	    	while(flag);
	    	
	    	isUpdationSuccessful = customerDao.updateCustomer(username,field,fieldValue);
    	}
		catch(NumberFormatException | InvalidMobileNumberException | InvalidNameException | SQLException e) {
    		throw e;
    	}
    	
		if(isUpdationSuccessful>0)
    		System.out.println("\nUpdated record of customer "+username+".");
    	else
    		System.out.println("\nUpdation failed.");
		 
    }
    
    private int displayUpdateMenu() throws NumberFormatException{
    	
    	int userChoice=-1;
    	
    	System.out.println("\nPress [1] to update full name.");
    	System.out.println("Press [2] to update mobile number.");
    	System.out.println("Press [3] to update address.");
    	System.out.println("Press [4] to update password.");
    	
    	try {
    		userChoice = Integer.parseInt(scanner.nextLine());
    	}
    	catch(NumberFormatException e) {
    		throw e;
    	}
    	return userChoice;
    	
    }
    
    public void closeConnection() throws SQLException {
    	scanner.close();
    	try {
			customerDao.closeDBConnection();
		} catch (SQLException e) {
			throw e;
		}
    }
}
