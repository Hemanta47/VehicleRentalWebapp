package com.FleetX.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
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

@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateVehicle" })
@MultipartConfig
public class UpdateVehicleController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VehicleService vehicleService;

    public UpdateVehicleController() {
        vehicleService = new VehicleService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
            // === Collect vehicle form data ===
            int id = Integer.parseInt(request.getParameter("id"));
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
            String existingImageUrl = request.getParameter("existingImageUrl");

            // === Image upload handling ===
            Part imagePart = request.getPart("imageUrl");
            String appPath = request.getServletContext().getRealPath("");
            String imageUrl;

            if (imagePart != null && imagePart.getSize() > 0) {
                // New image uploaded
                imageUrl = new ImageUtil().uploadImage(imagePart, appPath, category);
                if (imageUrl == null) {
                    throw new IOException("Image upload failed.");
                }
            } else {
                // Keep existing image
                imageUrl = existingImageUrl;
            }

            // === Create updated vehicle object ===
            VehicleModel vehicle = new VehicleModel(id,
                category, brand, model, year, registrationNumber,
                dailyRate, fuelType, transmission, capacity, status,
                imageUrl, location, description, features
            );

            // === Store updated vehicle in database ===
            boolean success = vehicleService.updateVehicle(vehicle);

            if (success) {
                request.getSession().setAttribute("message", "Vehicle updated successfully!");
                System.out.println("Vehicle updated successfully!");
            } else {
                request.getSession().setAttribute("error", "Failed to update vehicle.");
                System.err.println("Vehicle update failed.");
            }

            // Redirect to dashboard
            response.sendRedirect(request.getContextPath() + "/Dashboard");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Unexpected error: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/Dashboard");
        }
    }
}
