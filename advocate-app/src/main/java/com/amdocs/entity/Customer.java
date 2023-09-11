package com.amdocs.entity;

public class Customer {

	private String fullName;
	private String mobileNumber;
	private String address;
	private String username;
	private String password;
	

	public Customer() {}
	
	public Customer(String fullName, String mobileNumber, String address,String username,String password) {
		super();
		this.fullName=fullName;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.username = username;
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Customer [fullName=" + fullName + ", mobileNumber=" + mobileNumber + ", address=" + address
				+ ", username=" + username + ", password=" + password + "]";
	}
	
	
	
	
}
