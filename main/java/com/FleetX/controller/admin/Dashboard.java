package com.FleetX.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.FleetX.service.CheckoutService;
import com.FleetX.service.ContactService;
import com.FleetX.service.UserService;
import com.FleetX.service.VehicleService;

@WebServlet(asyncSupported = true, urlPatterns = { "/Dashboard" })
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private VehicleService vehicleService;
	private ContactService contactService;
	private CheckoutService checkoutService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Dashboard() {
		userService = new UserService();
		vehicleService = new VehicleService();
		contactService = new ContactService();
		checkoutService = new CheckoutService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
		    vehicleService.updateVehicleAvailabilityBasedOnRentals();
			// Always fetch the latest lists from the services
			request.setAttribute("VehicleList", vehicleService.getAllVehicles());
			request.setAttribute("totalVehicle", vehicleService.totalVehicleCount());
			
			request.setAttribute("UserList", userService.getAllUser());
			request.setAttribute("Only5User", userService.getOnly5User());
			request.setAttribute("totalUser", userService.totalUserCount());
			
			request.setAttribute("MessageList", contactService.getAllMessages());
			request.setAttribute("Only5Message", contactService.getOnly5Messages());
			request.setAttribute("totalMessage", contactService.totalMessageCount());
			
			request.setAttribute("BookingList", checkoutService.getAllRental());
			request.setAttribute("totalBooking", checkoutService.totalBookingCount());
			request.setAttribute("bookingTrend", checkoutService.getBookingTrend());
			
			request.getRequestDispatcher("/WEB-INF/Pages/Admin/Dashboard.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error: " + e.getMessage());
		}
	}

}
