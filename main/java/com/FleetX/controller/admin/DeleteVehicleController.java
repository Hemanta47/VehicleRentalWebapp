package com.FleetX.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.FleetX.service.VehicleService;

/**
 * Servlet implementation class DeleteVehicleController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/deleteVehicle" })
public class DeleteVehicleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VehicleService vehicleService;
	
	public DeleteVehicleController() {
	vehicleService = new VehicleService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("vehicleID");
		
		if (id == null || id.isEmpty()) {
            System.out.println("No vehicle ID provided.");
            response.sendRedirect("Dashboard");
            return;
        }

        boolean isDeleted = vehicleService.deleteVehicle(Integer.parseInt(id));

        if (isDeleted) {
        	request.getSession().setAttribute("message", "Vehicle Deleted Successfully.");
        } else {
        	request.getSession().setAttribute("message", "Failed to delete vehicle.");
        }
		response.sendRedirect("Dashboard");

	}

}
