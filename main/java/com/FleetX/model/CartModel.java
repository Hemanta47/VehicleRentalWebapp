package com.FleetX.model;

import java.math.BigDecimal;
import java.sql.Date;

public class CartModel {
    private int vehicleId;
    private String brand;
    private String model;
    private String imageUrl;
    private BigDecimal dailyRate;
    private Date startDate;
    private Date endDate;
    private String pickupLocation;
    private String dropupLocation;

    public CartModel() {
    }

    public CartModel(int vehicleId, String brand, String model, String imageUrl, BigDecimal dailyRate, Date startDate, Date endDate, String pickupLocation, String dropupLocation) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.imageUrl = imageUrl;
        this.dailyRate = dailyRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pickupLocation = pickupLocation;
        this.dropupLocation = dropupLocation;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
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

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public int getRentalDays() {
        long diffMillis = endDate.getTime() - startDate.getTime();
        return Math.max(1, (int) (diffMillis / (1000 * 60 * 60 * 24)));
    }

    public BigDecimal getTotalPrice() {
        BigDecimal rentalDays = new BigDecimal(getRentalDays());
        return dailyRate.multiply(rentalDays);  // Return total price as BigDecimal
    }

	public String getDropupLocation() {
		return dropupLocation;
	}

	public void setDropupLocationString(String dropupLocation) {
		this.dropupLocation = dropupLocation;
	}
}
