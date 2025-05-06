package com.FleetX.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.FleetX.config.DbConfig;
import com.FleetX.model.UserModel;

public class RegisterService {
	private Connection dbConnection;

	public RegisterService() {
		try {
			this.dbConnection = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database Connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public Boolean addUser(UserModel userModel) {
		if (dbConnection == null) {
			System.err.println("Database connection is not available");
			return false;
		}

		String sql = "INSERT INTO users (FirstName, LastName, UserName, DOB, Number, Password, Email, Role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement pst = dbConnection.prepareStatement(sql)) {
			pst.setString(1, userModel.getFname());
			pst.setString(2, userModel.getLname());
			pst.setString(3, userModel.getuName());
			pst.setString(4, userModel.getDob());
			pst.setString(5, userModel.getPhone());
			pst.setString(6, userModel.getPassword());
			pst.setString(7, userModel.getEmail());
			pst.setString(8, userModel.getRole());

			return pst.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
