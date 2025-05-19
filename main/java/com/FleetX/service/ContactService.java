package com.FleetX.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.FleetX.config.DbConfig;
import com.FleetX.model.MessageModel;
import com.FleetX.model.UserModel;

public class ContactService {
	private Connection dConnection;

	public ContactService() {
		try {
			dConnection = DbConfig.getDbConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public UserModel getUserDetailByEmail(String email) throws SQLException {
		UserModel user = null;
		String sql = "SELECT * FROM users WHERE Email = ?";
		try (PreparedStatement pst = dConnection.prepareStatement(sql)) {
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				user = new UserModel();
				user.setId(rs.getInt("UserID"));
				user.setFname(rs.getString("FirstName"));
				user.setLname(rs.getString("LastName"));
				user.setuName(rs.getString("UserName"));
				user.setDob(rs.getString("DOB"));
				user.setPhone(rs.getString("Number"));
				user.setPassword(rs.getString("Password"));
				user.setEmail(rs.getString("Email"));
				user.setRole(rs.getString("role"));
			}
		}
		return user;
	}

	public boolean insertMessage(int id, String subject, String message) {
		String sql = "INSERT INTO message (user_id, subject, content) VALUES (?, ?, ?)";

		try (PreparedStatement stmt = dConnection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.setString(2, subject);
			stmt.setString(3, message);

			int rowsInserted = stmt.executeUpdate();
			return rowsInserted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// ✅ New method to fetch all messages with user email
	public List<MessageModel> getAllMessages() {
		List<MessageModel> messages = new ArrayList<>();

		String sql = "SELECT m.message_id, m.subject, m.content, m.sent_at, u.email " +
		             "FROM message m JOIN users u ON m.user_id = u.UserID " +
		             "ORDER BY m.sent_at DESC";

		try (PreparedStatement stmt = dConnection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				MessageModel message = new MessageModel();
				message.setMessageId(rs.getInt("message_id"));
				message.setSubject(rs.getString("subject"));
				message.setContent(rs.getString("content"));
				message.setSentAt(rs.getTimestamp("sent_at"));
				message.setEmail(rs.getString("email"));

				messages.add(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return messages;
	}
	
	public int totalMessageCount() {
		int count = 0;
		String sqlString = "SELECT COUNT(*) FROM message";
		try {
			PreparedStatement pStatement = dConnection.prepareStatement(sqlString);
			ResultSet rs = pStatement.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
