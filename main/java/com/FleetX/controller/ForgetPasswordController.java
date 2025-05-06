package com.FleetX.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.FleetX.service.UserService;
import com.FleetX.util.PasswordUtil;

/**
 * Servlet implementation class ForgetPasswordController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/forgetPassword" })
public class ForgetPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private String loginUrl = "WEB-INF/Pages/login.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgetPasswordController() {
		userService = new UserService();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		try {
			String username = userService.getUsernameByEmail(email);

			if (username == null) {
				request.setAttribute("error", "Email not registered.");
				request.getRequestDispatcher(loginUrl).forward(request, response);
				return;
			}

			if (!newPassword.equals(confirmPassword)) {
				request.setAttribute("error", "Passwords do not match.");
				request.getRequestDispatcher(loginUrl).forward(request, response);
				return;
			}

			String encryptPass = PasswordUtil.encrypt(username, newPassword);

			boolean success = userService.updateUserPasswordByEmail(email, encryptPass);
			if (success) {
				request.setAttribute("success", "Password reset successful. Please log in.");
			} else {
				request.setAttribute("error", "Failed to reset password. Try again.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		request.getRequestDispatcher(loginUrl).forward(request, response);
	}

}
