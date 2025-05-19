package com.FleetX.controller.user;

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
 * Servlet implementation class SendMessageController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/SendMessage" })
public class SendMessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
private ContactService contactService;
	
	public SendMessageController() {
	contactService = new ContactService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
	    String subject = request.getParameter("subject");
	    String message = request.getParameter("message");

	    try {
	        UserModel user = contactService.getUserDetailByEmail(email);

	        if (user != null) {
	            boolean success = contactService.insertMessage(user.getId(), subject, message);

	            if (success) {
	                request.getSession().setAttribute("messageStatus", "Message sent successfully!");
	            } else {
	                request.getSession().setAttribute("messageStatus", "Failed to send message. Please try again.");
	            }
	        } else {
	            request.getSession().setAttribute("messageStatus", "User not found with provided email.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        request.setAttribute("messageStatus", "Server error occurred.");
	    }
	    request.getRequestDispatcher("/WEB-INF/Pages/contact.jsp").forward(request, response);

	}

}
