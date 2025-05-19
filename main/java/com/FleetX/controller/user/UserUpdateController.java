package com.FleetX.controller.user;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.FleetX.model.UserModel;
import com.FleetX.service.UserService;
import com.FleetX.util.PasswordUtil;

/**
 * Servlet implementation class UserUpdateController
 */
@WebServlet("/userUpdate")
public class UserUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uName = request.getParameter("username");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		UserService userService = new UserService();
		UserModel userModel = new UserModel(fname, lname, uName, email, phone);

		boolean success = userService.updateUserProfile(userModel);

		UserModel updatedUser = userService.getUserByUsername(uName);
		request.getSession().setAttribute("user", updatedUser);

		if (success) {
			request.setAttribute("status", "updated");

		} else {
			request.setAttribute("status", "failed");
		}
		request.getRequestDispatcher("WEB-INF/Pages/userProfilePage.jsp").forward(request, response);

	}

}
