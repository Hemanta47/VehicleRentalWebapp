package com.FleetX.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.FleetX.model.UserModel;
import com.FleetX.service.UserService;

/**
 * Servlet implementation class UserProfileController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/userprofile" })
public class UserProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String username = (String) request.getSession().getAttribute("username");

	    if (username == null) {
	        // Redirect to login if no username is found in session
	        response.sendRedirect(request.getContextPath() + "/login");
	        return;
	    }

	    UserService userService = new UserService();
	    UserModel user = userService.getUserByUsername(username);

	    if (user == null) {
	        // If no user found, redirect to error page or show an appropriate message
	        response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
	        return;
	    }

	    request.setAttribute("user", user);
	    request.getRequestDispatcher("WEB-INF/Pages/userProfilePage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
