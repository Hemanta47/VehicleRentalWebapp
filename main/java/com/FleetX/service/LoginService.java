package com.FleetX.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.FleetX.config.DbConfig;
import com.FleetX.model.UserModel;
import com.FleetX.util.PasswordUtil;

public class LoginService {
	private Connection dbConnection;
	private boolean isConnectionError = false;

	public LoginService() {
		try {
			dbConnection = DbConfig.getDbConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			isConnectionError = true;
		}
	}

	public Boolean loginUser(UserModel userModel) {
		if (isConnectionError) {
			System.out.println("Connection error!");
			return null;
		}

		String loginQuery = "SELECT UserName, Password FROM users WHERE UserName = ?";
		try (PreparedStatement pst = dbConnection.prepareStatement(loginQuery)) {
			pst.setString(1, userModel.getuName());
			ResultSet resultSet = pst.executeQuery();

			if (resultSet.next()) {
				return validatePassword(resultSet, userModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return false;
	}
	

	private boolean validatePassword(ResultSet resultSet, UserModel userModel) throws SQLException {
		String dbUsername = resultSet.getString("username");
		String dbPassword = resultSet.getString("password");

		// Decrypt stored password using username as key
		String decryptedPassword = PasswordUtil.decrypt(dbPassword, dbUsername);

		// Null check and comparison
		return dbUsername.equals(userModel.getuName())
				&& decryptedPassword != null
				&& decryptedPassword.equals(userModel.getPassword());
	}
}
