package com.FleetX.model;

import java.math.BigDecimal;

public class VehicleModel {

    private int id;
    private String category;
    private String brand;
    private String model;
    private int year;
    private String registrationNumber;
    private BigDecimal dailyRate; // Changed to BigDecimal
    private String fuelType;
    private String transmission;
    private int capacity;
    private String status;
    private String imageUrl;
    private String location;
    private String description;
    private String features;

    public VehicleModel() {
        // Default constructor
    }

    public VehicleModel(int id, String category, String brand, String model, int year, String registrationNumber,
            BigDecimal dailyRate, String fuelType, String transmission, int capacity, String status, String imageUrl,
            String location, String description, String features) {
        this.id = id;
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.dailyRate = dailyRate;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.capacity = capacity;
        this.status = status;
        this.imageUrl = imageUrl;
        this.location = location;
        this.description = description;
        this.features = features;
    }

    public VehicleModel(String category, String brand, String model, int year, String registrationNumber, BigDecimal dailyRate,
            String fuelType, String transmission, int capacity, String status, String imageUrl, String location,
            String description, String features) {
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.dailyRate = dailyRate;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.capacity = capacity;
        this.status = status;
        this.imageUrl = imageUrl;
        this.location = location;
        this.description = description;
        this.features = features;
    }

    public VehicleModel(int id, String brand, String model, BigDecimal dailyRate, String transmission, int capacity,
            String imageUrl) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.dailyRate = dailyRate;
        this.transmission = transmission;
        this.capacity = capacity;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
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

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }
    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }

    public String getFuelType() {
        return fuelType;
    }
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmission() {
        return transmission;
    }
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeatures() {
        return features;
    }
    public void setFeatures(String features) {
        this.features = features;
    }
}
