package com.FleetX.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.FleetX.config.DbConfig;
import com.FleetX.model.RentalModel;
import com.FleetX.model.BookingTrendModel;
import com.FleetX.model.PaymentModel;

public class CheckoutService {
	private Connection dbConnection;

	public CheckoutService() {
		try {
			dbConnection = DbConfig.getDbConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public int insertRental(RentalModel rentalModel) throws SQLException {
		String sql = "INSERT INTO rental (user_id, vehicle_id, start_date, end_date, address, dropLocation, status) VALUES (?, ?, ?, ?, ?, ?, 'booked')";
		try (PreparedStatement pst = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pst.setInt(1, rentalModel.getUserId());
			pst.setInt(2, rentalModel.getVehicleId());
			pst.setDate(3, rentalModel.getStartDate());
			pst.setDate(4, rentalModel.getEndDate());
			pst.setString(5, rentalModel.getAddress());
			pst.setString(6, rentalModel.getDropAddress());

			int affectedRows = pst.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Creating rental failed, no rows affected.");
			}

			try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					int rentalId = generatedKeys.getInt(1);
					System.out.println("Rental inserted with ID: " + rentalId);
					return rentalId;
				} else {
					throw new SQLException("Creating rental failed, no ID obtained.");
				}
			}
		}
	}

	public boolean insertPayment(PaymentModel paymentModel) throws SQLException {
		String sql = "INSERT INTO payment (rental_id, amount, payment_date) VALUES (?, ?, ?)";
		try (PreparedStatement pst = dbConnection.prepareStatement(sql)) {
			pst.setInt(1, paymentModel.getRentalId());
			pst.setBigDecimal(2, paymentModel.getAmount());
			pst.setTimestamp(3, paymentModel.getPaymentDate());

			int affectedRows = pst.executeUpdate();
			if (affectedRows > 0) {
				System.out.println("Payment done");
			}
			return affectedRows > 0;
		}
	}

	public List<RentalModel> getAllRental() {
		List<RentalModel> rentalList = new ArrayList<>();
		
		String sqlString = """
			SELECT r.*, p.amount AS amount
			FROM rental r
			LEFT JOIN payment p ON r.rental_id = p.rental_id ORDER BY r.rental_id DESC
		""";

		try (PreparedStatement pst = dbConnection.prepareStatement(sqlString)) {
			ResultSet resultSet = pst.executeQuery();

			while (resultSet.next()) {
				rentalList.add(addRentalDetail(resultSet));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rentalList;
	}

	public RentalModel addRentalDetail(ResultSet rs) {
		try {
			return new RentalModel(
				rs.getInt("rental_id"),
				rs.getInt("user_id"),
				rs.getInt("vehicle_id"),
				rs.getDate("start_date"),
				rs.getDate("end_date"),
				rs.getString("address"),
				rs.getString("dropLocation"),
				rs.getString("status"),
				rs.getDouble("amount")
			);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean updateVehicleStatus(int vehicleId, String status) throws SQLException {
	    String sql = "UPDATE vehicle SET status = ? WHERE id = ?";
	    try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
	        stmt.setString(1, status);
	        stmt.setInt(2, vehicleId);
	        int rowsUpdated = stmt.executeUpdate();
	        return rowsUpdated > 0;
	    }
	}
	
	public int totalBookingCount() {
		int count = 0;
		String sqlString = "SELECT COUNT(*) FROM rental";
		try {
			PreparedStatement pStatement = dbConnection.prepareStatement(sqlString);
			ResultSet rs = pStatement.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public List<BookingTrendModel> getBookingTrend() {
		List<BookingTrendModel> trendList = new ArrayList<>();
		String sql = """
			SELECT start_date AS bookingDate, COUNT(*) AS totalBookings
			FROM rental
			GROUP BY start_date
			ORDER BY start_date DESC
			LIMIT 7
		""";

		try (PreparedStatement pst = dbConnection.prepareStatement(sql);
		     ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				BookingTrendModel trend = new BookingTrendModel(
					rs.getDate("bookingDate"),
					rs.getInt("totalBookings")
				);
				trendList.add(trend);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trendList;
	}


}
