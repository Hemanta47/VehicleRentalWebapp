package com.FleetX.controller;

import java.io.IOException;

import com.FleetX.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/deleteUser")
public class DeleteUserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        System.out.println("User ID to delete: " + userId);  // Debugging line

        UserService userService = new UserService();
        
        // If userId is null or empty, handle it as an error
        if (userId == null || userId.isEmpty()) {
            System.out.println("No user ID provided.");
            response.sendRedirect("Dashboard");
            return;
        }

        boolean isDeleted = userService.deleteUser(Integer.parseInt(userId));

        if (isDeleted) {
            System.out.println("User deleted successfully.");
            response.sendRedirect("Dashboard");
        } else {
            System.out.println("Failed to delete user.");
            response.sendRedirect("Dashboard");
        }
    }
}
