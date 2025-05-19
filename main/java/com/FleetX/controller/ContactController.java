package com.FleetX.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.FleetX.model.UserModel;
import com.FleetX.service.ContactService;

/**
 * Servlet implementation class ContactController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/contact" })
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactService contactService;
	
	public ContactController() {
	contactService = new ContactService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/Pages/contact.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String email = request.getParameter("email");
	    String subject = request.getParameter("subject");
	    String message = request.getParameter("message");

	    try {
	        UserModel user = contactService.getUserDetailByEmail(email);

	        if (user != null) {
	            boolean success = contactService.insertMessage(user.getId(), subject, message);

	            if (success) {
	                // success message for frontend
	                request.setAttribute("messageStatus", "Message sent successfully!");
	            } else {
	                request.setAttribute("messageStatus", "Failed to send message. Please try again.");
	            }
	        } else {
	            request.setAttribute("messageStatus", "User not found with provided email.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        request.setAttribute("messageStatus", "Server error occurred.");
	    }

	    request.getRequestDispatcher("/WEB-INF/Pages/contact.jsp").forward(request, response);
	}


}
