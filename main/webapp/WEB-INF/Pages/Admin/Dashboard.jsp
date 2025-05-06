<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Admin Dashboard</title>
<script src="https://kit.fontawesome.com/a63c128ded.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="${contextPath}/css/admin/global.css" />
<link rel="stylesheet" href="${contextPath}/css/admin/dashboard.css" />
<link rel="stylesheet" href="${contextPath}/css/admin/manageVehicle.css" />
<link rel="stylesheet" href="${contextPath}/css/admin/addVehicle.css" />
<link rel="stylesheet" href="${contextPath}/css/admin/booking.css" />
<link rel="stylesheet" href="${contextPath}/css/admin/user.css" />
<link rel="stylesheet" href="${contextPath}/css/admin/message.css" />
</head>

<body>
	<header class="header">
		<h1>Admin Dashboard</h1>
		<div class="profile">
			<div>
				<img src="${contextPath}/assets/bg-img/no-profile.jpg"
					alt="Profile Pic" /> <span>${sessionScope.username != null ? sessionScope.username : 'Admin'}</span>
			</div>
			<a href="${contextPath}/" target="_blank" class="public-link"><i
				class="fas fa-globe"></i>Public</a> 
				<form action="${contextPath}/logout" method="post">
							<input type="submit" class="nav-button" value="Logout" />
				</form>
				
		</div>
	</header>

	<div class="container">
		<aside class="sidebar">
			<nav>
				<ul>
					<li><a href="#" class="active" data-target="dashboard"><i
							class="fas fa-chart-line"></i><span>Dashboard</span></a></li>
					<li><a href="#" data-target="manageVehicle"><i
							class="fas fa-car"></i><span>Manage Vehicles</span></a></li>
					<li><a href="#" data-target="addVehicle"><i
							class="fas fa-plus-circle"></i><span>Add Vehicle</span></a></li>
					<li><a href="#" data-target="booking"><i
							class="fas fa-calendar-check"></i><span>Bookings</span></a></li>
					<li><a href="#" data-target="user"><i class="fas fa-users"></i><span>Users</span></a></li>
					<li><a href="#" data-target="messageContent"><i
							class="fas fa-envelope"></i><span>Messages</span></a></li>
				</ul>
			</nav>
			<div class="minimize">
				<i class="fa-solid fa-circle-chevron-left"></i>
			</div>

		</aside>

		<!-- Dashboard Section -->
		<section id="dashboard" class="main active-main">
			<jsp:include page="./dashboardContent.jsp" />
		</section>

		<!-- Manage Vehicles -->
		<section id="manageVehicle" class="main">
			<div class="content">
				<div class="content-header">
					<h2 class="section-title">Vehicle Fleet</h2>
				</div>

				<div class="table-container">
					<table class="data-table">
						<thead>
							<tr>
								<th>ID</th>
								<th>Vehicle</th>
								<th>Category</th>
								<th>Registration</th>
								<th>Daily Rate</th>
								<th>Fuel Type</th>
								<th>Transmission</th>
								<th>Capacity</th>
								<th>ImageUrl</th>
								<th>Status</th>
								<th>Location</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${VehicleList}" var="vehicle">
								<tr>
									<td>${vehicle.id}</td>
									<td>
										<div>
											<strong>${vehicle.brand}${vehicle.model}</strong>
											<div style="font-size: 0.8rem; color: var(--text-secondary);">2020</div>
										</div>
									</td>
									<td>${vehicle.category}</td>
									<td>${vehicle.registrationNumber}</td>
									<td>Rs.${vehicle.dailyRate}/day</td>
									<td>${vehicle.fuelType}</td>
									<td>${vehicle.transmission}</td>
									<td>${vehicle.capacity}</td>
									<td>${vehicle.imageUrl}</td>
									<td><span class="status status-available">${vehicle.status}</span></td>
									<td>${vehicle.location}</td>
									<td>
										<div class="btn-group">
											<button class="action-btn edit-btn" title="Edit">
												<i class="fas fa-edit"></i>
											</button>
											<button class="action-btn delete-btn" title="Delete">
												<i class="fas fa-trash"></i>
											</button>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</section>

		<!-- Add Vehicle -->
		<section id="addVehicle" class="main" >
			<div class="page-header">
				<h2>Add New Vehicle</h2>
			</div>

			<div class="vehicle-form-container">
				<div class="vehicle-form-wrapper">
					<div class="form-header">
						<h3>Vehicle Information</h3>
					</div>

					<div class="form-body">
						<form action="AddVehicle" method="POST" enctype="multipart/form-data">
							<div class="form-section">
								<h4 class="section-title">
									<i class="fas fa-info-circle"></i> Basic Information
								</h4>
								<div class="form-row">
									<div class="form-group">
										<label for="category"><i class="fas fa-tag"></i>
											Category</label> <input type="text" id="category" name="category"
											placeholder="e.g. car or bike" required> <span
											class="input-icon"><i class="fas fa-car-side"></i></span>
									</div>
									<div class="form-group">
										<label for="brand"><i class="fas fa-building"></i>
											Brand</label> <input type="text" id="brand" name="brand"
											placeholder="e.g. Toyota, Honda, BMW" required> <span
											class="input-icon"><i class="fas fa-trademark"></i></span>
									</div>
								</div>

								<div class="form-row">
									<div class="form-group">
										<label for="model"><i class="fas fa-car"></i> Model</label> <input
											type="text" id="model" name="model"
											placeholder="e.g. Camry, Civic, X5" required> <span
											class="input-icon"><i class="fas fa-car"></i></span>
									</div>
									<div class="form-group">
										<label for="year"><i class="fas fa-calendar-alt"></i>
											Year</label> <input type="number" id="year" name="year"
											placeholder="e.g. 2023" required> <span
											class="input-icon"></span>
									</div>
								</div>
							</div>

							<div class="form-section">
								<h4 class="section-title">
									<i class="fas fa-id-card"></i> Registration & Pricing
								</h4>
								<div class="form-row">
									<div class="form-group">
										<label for="registrationNumber"><i
											class="fas fa-id-badge"></i> Registration Number</label> <input
											type="text" id="registrationNumber" name="registrationNumber"
											placeholder="e.g. ABC-1234" required> <span
											class="input-icon"><i class="fas fa-fingerprint"></i></span>
									</div>
									<div class="form-group">
										<label for="dailyRate">Rs. Daily Rate (Rs.)</label> <input
											type="number" id="dailyRate" name="dailyRate"
											placeholder="e.g. 50" required> <span
											class="input-icon"><i class="fas fa-money-bill-wave"></i></span>
									</div>
								</div>
							</div>

							<div class="form-section">
								<h4 class="section-title">
									<i class="fas fa-cogs"></i> Technical Specifications
								</h4>
								<div class="form-row">
									<div class="form-group">
										<label for="fuelType"><i class="fas fa-gas-pump"></i>
											Fuel Type</label> <input type="text" id="fuelType" name="fuelType"
											placeholder="e.g. Petrol, Diesel, Electric" required>
										<span class="input-icon"><i
											class="fas fa-charging-station"></i></span>
									</div>
									<div class="form-group">
										<label for="transmission"><i class="fas fa-cog"></i>
											Transmission</label> <input type="text" id="transmission"
											name="transmission" placeholder="e.g. Automatic, Manual"
											required> <span class="input-icon"><i
											class="fas fa-cogs"></i></span>
									</div>
								</div>

								<div class="form-row">
									<div class="form-group">
										<label for="capacity"><i class="fas fa-users"></i>
											Capacity</label> <input type="number" id="capacity" name="capacity"
											placeholder="e.g. 5" required> <span
											class="input-icon"><i class="fas fa-user-friends"></i></span>
									</div>
									<div class="form-group">
										<label for="status"><i class="fas fa-info-circle"></i>
											Status</label>
										<!-- Replace input field with a select dropdown -->
										<select id="status" name="status" required>
											<option value="Available">Available</option>
											<option value="Maintenance">Maintenance</option>
											<option value="Booked">Booked</option>
										</select> <span class="input-icon"></span>
									</div>
								</div>
							</div>

							<div class="form-section">
								<h4 class="section-title">
									<i class="fas fa-map-marker-alt"></i> Location & Media
								</h4>
								<div class="form-row">
									<div class="form-group">
										<label for="imageUrl"><i class="fas fa-image"></i>
											Image File</label>
										<!-- Replace input for URL with file input -->
										<input type="file" id="imageUrl" name="imageUrl"
											accept="image/*" required> <span class="input-icon"><i
											class="fas fa-camera"></i></span>
									</div>
									<div class="form-group">
										<label for="location"><i class="fas fa-map-pin"></i>
											Location</label> <input type="text" id="location" name="location"
											placeholder="e.g. Downtown Branch" required> <span
											class="input-icon"><i class="fas fa-map-marked-alt"></i></span>
									</div>
								</div>
							</div>

							<div class="form-section">
								<h4 class="section-title">
									<i class="fas fa-file-alt"></i> Additional Details
								</h4>
								<div class="form-row">
									<div class="form-group full">
										<label for="description"><i class="fas fa-align-left"></i>
											Description</label>
										<textarea id="description" name="description"
											placeholder="Detailed description of the vehicle..."></textarea>
									</div>
								</div>

								<div class="form-row">
									<div class="form-group full">
										<label for="features"><i class="fas fa-list-ul"></i>
											Features</label> <input type="text" id="features" name="features"
											placeholder="e.g. GPS, Bluetooth, Leather Seats (comma separated)">
										<span class="input-icon"><i class="fas fa-star"></i></span>
									</div>
								</div>
							</div>

							<div class="form-footer">
								<button type="submit">
									<i class="fas fa-plus-circle"></i> Add Vehicle
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>

		</section>

		<!-- Bookings -->
		<section id="booking" class="main">
			<jsp:include page="./bookingContent.jsp" />
		</section>

		<!-- Users -->
		<section id="user" class="main">
			<h2 class="page-title">Manage Users</h2>
			<table class="data-table">
				<thead>
					<tr>
						<th>User ID</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Username</th>
						<th>Date of Birth</th>
						<th>Email</th>
						<th>Phone</th>
						<th>Password</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty UserList}">
						<tr>
							<td colspan="5">No users found or data not loaded correctly.</td>
						</tr>
					</c:if>
					<c:forEach items="${UserList}" var="user">
						<c:if test="${not empty user}">
							<tr>
								<td>${user.id}</td>
								<td>${user.fname}</td>
								<td>${user.lname}</td>
								<td>${user.uName}</td>
								<td>${user.dob}</td>
								<td>${user.email}</td>
								<td>${user.phone}</td>
								<td>********</td>
								<td>
									<form method="post" action="deleteUser">
										<input type="hidden" name="userId" value="${user.id}" />
										<button type="submit" class="action-btn">Delete</button>
									</form>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</section>


		<!-- Messages -->
		<section id="messageContent" class="main">
			<jsp:include page="./messageContent.jsp" />
		</section>
	</div>

	<script>
  window.addEventListener('DOMContentLoaded', () => {
	  const links = document.querySelectorAll('.sidebar nav ul li a');
	  links.forEach(link => {
	    link.addEventListener('click', e => {
	      e.preventDefault();
	      activateSection(link.dataset.target, link);
	    });
	  });

	  // Attach sidebar toggle
	  const minimizeBtn = document.querySelector('.minimize');
	  if (minimizeBtn) {
	    minimizeBtn.addEventListener('click', toggleSidebar);
	  }
	});

    function toggleSidebar() {
      const sidebar = document.querySelector('.sidebar');
      const icon = document.querySelector('.minimize i');
      sidebar.classList.toggle('collapsed');

      document.querySelectorAll('.main.active-main').forEach(main => {
        if (sidebar.classList.contains('collapsed')) {
          main.classList.add('expanded');
        } else {
          main.classList.remove('expanded');
        }
      });

      icon.classList.toggle('fa-circle-chevron-left');
      icon.classList.toggle('fa-circle-chevron-right');
    }

    function activateSection(id, link) {
      // Hide all
      document.querySelectorAll('.main').forEach(main => {
        main.classList.remove('active-main', 'expanded');
      });

      const target = document.getElementById(id);
      target.classList.add('active-main');

      if (document.querySelector('.sidebar.collapsed')) {
        target.classList.add('expanded');
      }

      document.querySelectorAll('.sidebar nav ul li a').forEach(a => a.classList.remove('active'));
      link.classList.add('active');
    }

    window.addEventListener('DOMContentLoaded', () => {
      const links = document.querySelectorAll('.sidebar nav ul li a');
      links.forEach(link => {
        link.addEventListener('click', e => {
          e.preventDefault();
          activateSection(link.dataset.target, link);
        });
      });
    });
  </script>
</body>
</html>
