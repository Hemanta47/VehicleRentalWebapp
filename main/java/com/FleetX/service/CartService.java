package com.FleetX.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.FleetX.model.CartModel;
import jakarta.servlet.http.HttpSession;

public class CartService {
    private static final String CART_SESSION_KEY = "cartItems";
    private HttpSession session;

    public CartService(HttpSession session) {
        this.session = session;
    }

    @SuppressWarnings("unchecked")
    public boolean addItem(CartModel item) {
        List<CartModel> cart = (List<CartModel>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
        }

        // Check for duplicate vehicle by vehicle ID
        boolean alreadyExists = cart.stream()
                .anyMatch(existingItem -> existingItem.getVehicleId() == item.getVehicleId());

        if (!alreadyExists) {
            cart.add(item);
            session.setAttribute(CART_SESSION_KEY, cart);
            return true;
        }

        return false; // Not added due to duplication
    }

    @SuppressWarnings("unchecked")
    public void removeItem(int index) {
        List<CartModel> cart = (List<CartModel>) session.getAttribute(CART_SESSION_KEY);
        if (cart != null && index >= 0 && index < cart.size()) {
            cart.remove(index);
            session.setAttribute(CART_SESSION_KEY, cart);
        }
    }

    @SuppressWarnings("unchecked")
    public List<CartModel> getCartItems() {
        List<CartModel> cart = (List<CartModel>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }

    public void clearCart() {
        System.out.println("Clearing cart...");
        session.removeAttribute(CART_SESSION_KEY);
        System.out.println("Cart after clearing: " + session.getAttribute(CART_SESSION_KEY));
    }


    public BigDecimal calculateTotalPrice() {
        List<CartModel> cart = getCartItems();
        // Sum the total prices of all cart items, using BigDecimal for precision
        return cart.stream()
                .map(CartModel::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @SuppressWarnings("unchecked")
    public int getCartSize() {
        List<CartModel> cart = (List<CartModel>) session.getAttribute(CART_SESSION_KEY);
        return cart != null ? cart.size() : 0;
    }
}
