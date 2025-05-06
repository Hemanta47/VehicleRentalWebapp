package com.FleetX.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

import com.FleetX.model.UserModel;
import com.FleetX.service.UserService;

/**
 * Servlet implementation class UpdatePassword
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/updatePassword" })
public class UpdatePasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uName = request.getParameter("username");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        if (uName == null || oldPassword == null || newPassword == null ||
            uName.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty()) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("/WEB-INF/Pages/userProfilePage.jsp").forward(request, response);
            return;
        }

        boolean success = userService.updateUserPassword(uName, oldPassword, newPassword);
        HttpSession session = request.getSession();

        if (success) {
            request.setAttribute("passwordStatus", "success"); // used to show popup
            session.setAttribute("passwordUpdatedAt", new Date()); // timestamp
        } else {
            request.setAttribute("error", "Failed to update password. Check old password.");
        }

        // Always update user model in session
        UserModel updatedUser = userService.getUserByUsername(uName);
        session.setAttribute("user", updatedUser);

        request.getRequestDispatcher("/WEB-INF/Pages/userProfilePage.jsp").forward(request, response);
    }
}
