<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.FleetX.model.CartModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout - FleetX</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 900px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        h1, h2 {
            color: #333;
            text-align: center;
            margin-bottom: 30px;
        }
        .checkout-summary ul {
            list-style: none;
            padding: 0;
        }
        .checkout-summary li {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
            border-bottom: 1px solid #eee;
            padding-bottom: 10px;
        }
        .checkout-summary img {
            width: 90px;
            height: auto;
            border-radius: 4px;
            margin-right: 15px;
        }
        .checkout-summary .item-info {
            flex-grow: 1;
        }
        .checkout-summary .item-info div:first-child {
            font-weight: bold;
            font-size: 18px;
        }
        .checkout-summary .item-info div:last-child {
            color: #666;
        }
        .cart-total {
            display: flex;
            justify-content: space-between;
            font-size: 24px;
            font-weight: bold;
            padding: 15px 0;
            border-top: 1px solid #eee;
            border-bottom: 1px solid #eee;
            margin-bottom: 20px;
        }
        .submit-btn {
            display: block;
            width: 100%;
            padding: 15px;
            background: #2ecc71;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
        }
        .submit-btn:hover {
            background: #27ae60;
        }
        .back-btn {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #3498db;
            text-decoration: none;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Checkout</h1>

    <div class="checkout-summary">
        <h2>Order Summary</h2>
        <c:if test="${not empty cartItems}">
            <ul>
                <c:forEach var="item" items="${cartItems}">
                    <li>
                        <img src="${contextPath}/assets/vehicle/${item.imageUrl}" alt="${item.model}" />
                        <div class="item-info">
                            <div>${item.model}</div>
                            <div>Rs. ${item.dailyRate}/day</div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </c:if>

        <div class="cart-total">
            <span>Total Amount:</span>
            <span>Rs. <%= request.getAttribute("totalValue") %></span>
        </div>
    </div>

    <form action="ProcessOrder" method="post">
        <button type="submit" class="submit-btn">Place Order</button>
    </form>

    <a href="cart" class="back-btn">Back to Cart</a>
</div>
</body>
</html>
