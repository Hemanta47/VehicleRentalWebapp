<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>${vehicle.brand}${vehicle.model}|FleetX</title>
<link rel="stylesheet" href="${contextPath}/css/global.css" />
<link rel="stylesheet" href="${contextPath}/css/header.css" />
<link rel="stylesheet" href="${contextPath}/css/vehicle-detail.css" />
<script src="https://kit.fontawesome.com/a63c128ded.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="./component/header.jsp" />

	<div class="vehicle-detail-page">
		<h1 class="vehicle-title">${vehicle.brand}${vehicle.model}</h1>

		<div class="image-info-section">
			<div class="vehicle-image">
				<img src="${contextPath}/assets/vehicle/${vehicle.imageUrl}"
					alt="${vehicle.brand} ${vehicle.model}">
			</div>
			<div class="vehicle-info">
				<ul>
					<li><strong>Category:</strong> ${vehicle.category}</li>
					<li><strong>Brand:</strong> ${vehicle.brand}</li>
					<li><strong>Model:</strong> ${vehicle.model}</li>
					<li><strong>Year:</strong> ${vehicle.year}</li>
					<li><strong>Registration No:</strong>
						${vehicle.registrationNumber}</li>
					<li><strong>Daily Rate:</strong> ₹${vehicle.dailyRate}/day</li>
					<li><strong>Fuel Type:</strong> ${vehicle.fuelType}</li>
					<li><strong>Transmission:</strong> ${vehicle.transmission}</li>
					<li><strong>Capacity:</strong> ${vehicle.capacity} Seater</li>
					<li><strong>Status:</strong> ${vehicle.status}</li>
					<li><strong>Location:</strong> ${vehicle.location}</li>
				</ul>
			</div>
		</div>

		<div class="vehicle-description">
			<h2>Description</h2>
			<p>${vehicle.description}</p>
		</div>

		<div class="vehicle-features">
			<h2>Features</h2>
			<ul>
				<c:forEach var="feature" items="${fn:split(vehicle.features, ',')}">
					<li>${feature}</li>
				</c:forEach>
			</ul>
		</div>

		<c:choose>
			<c:when test="${not empty sessionScope.username}">

				<div class="rental-form-section">
					<h2>Rent This Vehicle</h2>
					<form action="${contextPath}/AddToCart" method="post">
						<input type="hidden" name="vehicleId" value="${vehicle.id}">

						<label for="rental-start">Start Date:</label> <input type="date"
							id="rental-start" name="startDate" required> <label
							for="rental-end">End Date:</label> <input type="date"
							id="rental-end" name="endDate" required> <label
							for="pickup-location">Pickup Location:</label> <select
							id="pickup-location" name="pickupLocation" required>
							<option value="">Select Location</option>
							<option value="Kathmandu">Kathmandu</option>
							<option value="Lalitpur">Lalitpur</option>
							<option value="Bhaktapur">Bhaktapur</option>
						</select>

						<div class="action-buttons">
							<button type="submit" name="action" value="add_to_cart">Add
								to Cart</button>
						</div>
					</form>
				</div>
			</c:when>
			<c:otherwise>
				<div
					style="padding: 0.5rem 0; font-size: 1.5rem; margin: 1rem 0 0; background: var(--black); color: var(--white); text-align: center;">
					You need to Login <a href="${contextPath}/login"
						style="font-weight: 400; text-decoration: underline;  color: #fff;">Sign
						In</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<script>
    const today = new Date().toISOString().split('T')[0];
    const startDateInput = document.getElementById("rental-start");
    const endDateInput = document.getElementById("rental-end");

    startDateInput.setAttribute("min", today);

    startDateInput.addEventListener("change", () => {
        const selectedStart = new Date(startDateInput.value);
        const nextDay = new Date(selectedStart);
        nextDay.setDate(selectedStart.getDate() + 1);
        const minEnd = nextDay.toISOString().split('T')[0];
        endDateInput.value = "";
        endDateInput.setAttribute("min", minEnd);
    });
</script>
</body>
</html>
