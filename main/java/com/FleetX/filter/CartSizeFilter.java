package com.FleetX.filter;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.FleetX.service.CartService;

@WebFilter("/*") // Applies to all requests
public class CartSizeFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        if (session != null) {
            CartService cartService = new CartService(session);
            int cartSize = cartService.getCartSize();
            httpRequest.setAttribute("cartSize", cartSize);
        } else {
            httpRequest.setAttribute("cartSize", 0);
        }

        chain.doFilter(request, response);
    }
}
