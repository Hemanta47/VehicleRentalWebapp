package com.FleetX.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.FleetX.service.UserService;
import com.FleetX.service.VehicleService;

@WebServlet(asyncSupported = true, urlPatterns = { "/Dashboard" })
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private VehicleService vehicleService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Dashboard() {
		userService = new UserService();
		vehicleService = new VehicleService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Always fetch the latest lists from the services
			request.setAttribute("VehicleList", vehicleService.getAllVehicles());
			request.setAttribute("UserList", userService.getAllUser());
			request.getRequestDispatcher("/WEB-INF/Pages/Admin/Dashboard.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error: " + e.getMessage());
		}
	}

}
