package com.FleetX.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.FleetX.config.DbConfig;
import com.FleetX.model.VehicleModel;

public class VehicleService {
	private Connection dbConnection;

	public VehicleService() {
		try {
			this.dbConnection = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database Connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	
	public boolean insertVehicle(VehicleModel vehicle) {
	    String sql = "INSERT INTO vehicle (" +
	            "category, brand, model, year, registration_number, " +
	            "daily_rate, fuel_type, transmission, capacity, status, " +
	            "image_url, location, description, features" +
	            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {

	        stmt.setString(1, vehicle.getCategory());
	        stmt.setString(2, vehicle.getBrand());
	        stmt.setString(3, vehicle.getModel());
	        stmt.setInt(4, vehicle.getYear());
	        stmt.setString(5, vehicle.getRegistrationNumber());
	        stmt.setBigDecimal(6, vehicle.getDailyRate());
	        stmt.setString(7, vehicle.getFuelType());
	        stmt.setString(8, vehicle.getTransmission());
	        stmt.setInt(9, vehicle.getCapacity());
	        stmt.setString(10, vehicle.getStatus());
	        stmt.setString(11, vehicle.getImageUrl());
	        stmt.setString(12, vehicle.getLocation());
	        stmt.setString(13, vehicle.getDescription());
	        stmt.setString(14, vehicle.getFeatures());

	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	
	
	// Get all vehicles
	public List<VehicleModel> getAllVehicles() {
		
		List<VehicleModel> vehicles = new ArrayList<>();
		String sql = "SELECT * FROM vehicle";

		try (PreparedStatement stmt = dbConnection.prepareStatement(sql);
		     ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				vehicles.add(setVehicleResult(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicles;
	} 
	
	//Filtering Vehicle
	public List<VehicleModel> getFilteredVehicles(String fuel, String gear, String type, String location) {
	    List<VehicleModel> vehicles = new ArrayList<>();
	    StringBuilder sql = new StringBuilder("SELECT * FROM vehicle WHERE 1=1");
	    List<Object> params = new ArrayList<>();

	    if (fuel != null && !fuel.isEmpty()) {
	        sql.append(" AND fuel_type = ?");
	        params.add(fuel);
	    }
	    if (gear != null && !gear.isEmpty()) {
	        sql.append(" AND transmission = ?");
	        params.add(gear);
	    }
	    if (type != null && !type.isEmpty()) {
	        sql.append(" AND category = ?");
	        params.add(type);
	    }
	    if (location != null && !location.isEmpty()) {
	        sql.append(" AND location = ?");
	        params.add(location);
	    }

	    try (PreparedStatement stmt = dbConnection.prepareStatement(sql.toString())) {
	        for (int i = 0; i < params.size(); i++) {
	            stmt.setObject(i + 1, params.get(i));
	        }

	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                vehicles.add(setVehicleResult(rs));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return vehicles;
	}

	
	public VehicleModel getVehicleById(int id) {
	    VehicleModel vehicle = null;
	    String sql = "SELECT * FROM vehicle WHERE id = ?";
	    try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
	        stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            vehicle = setVehicleResult(rs); // use your helper
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return vehicle;
	}

	public VehicleModel setVehicleResult(ResultSet rs) throws SQLException {
		VehicleModel v = new VehicleModel();
		v.setId(rs.getInt("id"));
		v.setCategory(rs.getString("category"));
		v.setBrand(rs.getString("brand"));
		v.setModel(rs.getString("model"));
		v.setYear(rs.getInt("year"));
		v.setRegistrationNumber(rs.getString("registration_number"));
		v.setDailyRate(rs.getBigDecimal("daily_rate"));
		v.setFuelType(rs.getString("fuel_type"));
		v.setTransmission(rs.getString("transmission"));
		v.setCapacity(rs.getInt("capacity"));
		v.setStatus(rs.getString("status"));
		v.setImageUrl(rs.getString("image_url"));
		v.setLocation(rs.getString("location"));
		v.setDescription(rs.getString("description"));
		v.setFeatures(rs.getString("features"));
		return v;
	}
	
		public List<VehicleModel> getNewAddedVehicle(){
		List<VehicleModel> vehicles = new ArrayList<>();
		String sql = "SELECT * FROM vehicle ORDER BY id DESC LIMIT 4";

		try (PreparedStatement stmt = dbConnection.prepareStatement(sql);
		     ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				vehicles.add(new VehicleModel(
						rs.getInt("id"),
						rs.getString("brand"),
						rs.getString("model"),
						rs.getBigDecimal("daily_rate"),
						rs.getString("transmission"),
						rs.getInt("capacity"),
						rs.getString("image_url")
						));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicles;

	}
}
