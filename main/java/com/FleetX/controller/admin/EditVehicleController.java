package com.FleetX.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.math.BigDecimal;

import com.FleetX.model.VehicleModel;
import com.FleetX.service.VehicleService;
import com.FleetX.util.ImageUtil;

/**
 * Servlet implementation class EditPageController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/EditVehicle" })
public class EditVehicleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VehicleService vehicleService;

	public EditVehicleController() {
		vehicleService = new VehicleService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String idString = request.getParameter("vehicleId");
			if (idString == null) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing vehicleId");
				return;
			}

			int vehicleId = Integer.parseInt(idString);
			VehicleModel vehicle = vehicleService.getVehicleById(vehicleId);

			if (vehicle == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Vehicle not found");
				return;
			}

			request.setAttribute("vehicle", vehicle);
			request.getRequestDispatcher("/WEB-INF/Pages/Admin/EditVehicle.jsp").forward(request, response);

		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid vehicleId");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		try {
			int id = Integer.parseInt(request.getParameter("id")); // Hidden input from form
			String category = request.getParameter("category").toLowerCase();
			String brand = request.getParameter("brand");
			String model = request.getParameter("model");
			int year = Integer.parseInt(request.getParameter("year"));
			String registrationNumber = request.getParameter("registrationNumber");
			BigDecimal dailyRate = new BigDecimal(request.getParameter("dailyRate"));
			String fuelType = request.getParameter("fuelType");
			String transmission = request.getParameter("transmission");
			int capacity = Integer.parseInt(request.getParameter("capacity"));
			String status = request.getParameter("status");
			String location = request.getParameter("location");
			String description = request.getParameter("description");
			String features = request.getParameter("features");

			Part imagePart = request.getPart("imageUrl");
			String appPath = request.getServletContext().getRealPath("");
			String imageUrl;

			if (imagePart != null && imagePart.getSize() > 0) {
				imageUrl = new ImageUtil().uploadImage(imagePart, appPath, category);
			} else {
				VehicleModel existingVehicle = vehicleService.getVehicleById(id);
				imageUrl = existingVehicle.getImageUrl();
			}

			VehicleModel updatedVehicle = new VehicleModel(id, category, brand, model, year, registrationNumber,
					dailyRate, fuelType, transmission, capacity, status, imageUrl, location, description, features);

			// Update in DB
			boolean success = vehicleService.updateVehicle(updatedVehicle);

			if (success) {
				request.getSession().setAttribute("message", "Vehicle updated successfully.");
			} else {
				request.getSession().setAttribute("message", "Failed to update vehicle.");
			}
			response.sendRedirect("Dashboard");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Unexpected error: " + e.getMessage());
			response.sendRedirect(request.getContextPath() + "/Dashboard");
		}
	}

}
