package com.FleetX.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.FleetX.service.UserService;

@WebServlet(asyncSupported = true, urlPatterns = { "/deleteAccount" })
public class DeleteAccountController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    public DeleteAccountController() {
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        if (username == null || username.isEmpty()) {
            System.out.println("Username not provided.");
            response.sendRedirect("userprofile");
            return;
        }

        int userId = userService.getUserIdByUsername(username);

        if (userId <= 0) {
            System.out.println("Invalid user ID.");
            response.sendRedirect("userprofile");
            return;
        }

        boolean isDeleted = userService.deleteUser(userId);

        if (isDeleted) {
            System.out.println("User deleted successfully.");
            // Optionally invalidate session
            request.getSession().invalidate();
            response.sendRedirect("login");
        } else {
            System.out.println("Failed to delete user.");
            response.sendRedirect("userprofile");
        }
    }
}
