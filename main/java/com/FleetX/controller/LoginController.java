package com.FleetX.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.FleetX.model.UserModel;
import com.FleetX.service.LoginService;
import com.FleetX.util.CookieUtil;
import com.FleetX.util.SessionUtil;

@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Pages/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserModel userModel = new UserModel(username, password);
        LoginService loginService = new LoginService();
        Boolean loginStatus = loginService.loginUser(userModel);

        if (loginStatus != null && loginStatus) {
            // Set session attributes
            SessionUtil.setAttribute(request, "username", username);

            // Handle redirect based on role
            String redirectUrl = "/"; // Default redirection URL
            String role = username.equals("admin") ? "admin" : "customer"; // Role logic

            SessionUtil.setAttribute(request, "role", role);
            CookieUtil.addCookie(response, "role", role, 5 * 30); 

            // Redirect to the appropriate page
            if ("admin".equals(role)) {
                redirectUrl = "/Dashboard"; 
            } else {
                redirectUrl = "/home";
            }

            response.sendRedirect(request.getContextPath() + redirectUrl); 
        } else {
            // Error handling for failed login
            request.setAttribute("error", "User credential mismatch or Server is under maintenance");
            request.getRequestDispatcher("/WEB-INF/Pages/login.jsp").forward(request, response);
        }
    }
}
