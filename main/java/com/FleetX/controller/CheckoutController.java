package com.FleetX.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.FleetX.model.CartModel;
import com.FleetX.service.CartService;

@WebServlet(asyncSupported = true, urlPatterns = { "/Checkout" })
public class CheckoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CheckoutController() {
        super();
    }

    // Load checkout.jsp on GET
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CartService cartService = new CartService(request.getSession());

        List<CartModel> cartItems = cartService.getCartItems();
        BigDecimal totalValue = cartService.calculateTotalPrice(); // Corrected

        request.setAttribute("cartItems", cartItems);
        request.setAttribute("totalValue", totalValue);

        request.getRequestDispatcher("/WEB-INF/Pages/checkout.jsp").forward(request, response);
    }

    // Handle order confirmation on POST
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CartService cartService = new CartService(request.getSession());

        List<CartModel> cartItems = cartService.getCartItems();
        BigDecimal totalValue = cartService.calculateTotalPrice(); // Corrected

        // Set checkout attributes
        request.setAttribute("cartItems", cartItems);
        request.setAttribute("totalValue", totalValue);

        // Clear the cart
        cartService.clearCart();

        // Forward to confirmation page
        request.getRequestDispatcher("/WEB-INF/Pages/confirmation.jsp").forward(request, response);
    }
}
