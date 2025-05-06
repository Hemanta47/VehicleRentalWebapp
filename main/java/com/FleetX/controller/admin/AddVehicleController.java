package com.FleetX.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.FleetX.model.VehicleModel;
import com.FleetX.service.VehicleService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.math.BigDecimal;

@SuppressWarnings("serial")
@WebServlet("/AddVehicle")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
    maxFileSize = 1024 * 1024 * 10,       // 10MB
    maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class AddVehicleController extends HttpServlet {

    private VehicleService vehicleService;

    @Override
    public void init() {
        vehicleService = new VehicleService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
            // Collect form parameters
            String category = request.getParameter("category").toLowerCase();
            String brand = request.getParameter("brand");
            String model = request.getParameter("model");
            int year = Integer.parseInt(request.getParameter("year"));
            String registrationNumber = request.getParameter("registrationNumber");
            
            // Change dailyRate to BigDecimal
            BigDecimal dailyRate = new BigDecimal(request.getParameter("dailyRate"));
            
            String fuelType = request.getParameter("fuelType");
            String transmission = request.getParameter("transmission");
            int capacity = Integer.parseInt(request.getParameter("capacity"));
            String status = request.getParameter("status");
            String location = request.getParameter("location");
            String description = request.getParameter("description");
            String features = request.getParameter("features");

            // File upload handling
            Part imagePart = request.getPart("imageUrl");
            String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();

            String appPath = request.getServletContext().getRealPath(""); // WebContent/
            String uploadPath = appPath + "assets" + File.separator + "vehicle" + File.separator + category;

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String filePath = uploadPath + File.separator + fileName;
            imagePart.write(filePath);

            String imageUrl = category + "/" + fileName; // Save relative path

            // Construct vehicle model
            VehicleModel vehicle = new VehicleModel(
                category, brand, model, year, registrationNumber,
                dailyRate, fuelType, transmission, capacity, status,
                imageUrl, location, description, features
            );

            // Insert vehicle
            boolean success = vehicleService.insertVehicle(vehicle);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/Dashboard");
                System.out.println("Vehicle added successfully!");
            } else {
            	response.sendRedirect(request.getContextPath() + "/Dashboard");
                request.setAttribute("error", "Failed to add vehicle.");
                System.out.println("Failed to add vehicle.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error adding vehicle: " + e.getMessage());
        }
    }
}

