package com.FleetX.model;

import java.sql.Date;

public class RentalModel {
	private int rentalId;
	private int userId;
	private int vehicleId;
	private Date startDate;
	private Date endDate;
	private String address;
	private String dropAddress;
	private String status;
	private double amount;
	private String vehicleName;

	public RentalModel() {

	}
	
	
	
	public RentalModel(int rentalId, String status, double amount, String vehicleName) {
		this.rentalId = rentalId;
		this.status = status;
		this.amount = amount;
		this.vehicleName = vehicleName;
	}



	public RentalModel(int rentalId, int userId, int vehicleId, Date startDate, Date endDate, String address,
			String dropAddress, String status) {

		this.rentalId = rentalId;
		this.userId = userId;
		this.vehicleId = vehicleId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.address = address;
		this.dropAddress = dropAddress;
		this.status = status;
	}
	
	public RentalModel(int rentalId, int userId, int vehicleId, Date startDate, Date endDate, String address, String dropAddress,
			String status, double amount) {

		this.rentalId = rentalId;
		this.userId = userId;
		this.vehicleId = vehicleId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.address = address;
		this.dropAddress = dropAddress;
		this.status = status;
		this.amount = amount;
	}

	public int getRentalId() {
		return rentalId;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDropAddress() {
		return dropAddress;
	}

	public void setDropAddress(String dropAddress) {
		this.dropAddress = dropAddress;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public int getRentalDays() {
		if (startDate == null || endDate == null) {
			return 0;
		}
		long diffMillis = endDate.getTime() - startDate.getTime();
		return Math.max(1, (int) (diffMillis / (1000 * 60 * 60 * 24)));
	}
}
