package com.amdocs.entity;

public class Appointment {
	private int appointmentId;
	private String customerUsername;
	private int advocateId;
	private String appointmentDate;
	
	public Appointment() {}
	
	public Appointment(int appointmentId, String customerUsername, int advocateId, String appointmentDate) {
		super();
		this.appointmentId = appointmentId;
		this.customerUsername = customerUsername;
		this.advocateId = advocateId;
		this.appointmentDate = appointmentDate;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getCustomerUsername() {
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}

	public int getAdvocateId() {
		return advocateId;
	}

	public void setAdvocateId(int advocateId) {
		this.advocateId = advocateId;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", customerUsername=" + customerUsername
				+ ", advocateId=" + advocateId + ", appointmentDate=" + appointmentDate + "]\n";
	}
	
	
	
}
