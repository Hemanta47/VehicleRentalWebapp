<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="true" %>

<header class="header">
	<nav class="navBar">
		<ul class="navList">
			<li><a href="${contextPath}/">Home</a></li>
			<li><a href="${contextPath}/about">About</a></li>
			<li><a href="${contextPath}/vehicle">Vehicle</a></li>
			<li><a href="${contextPath}/contact">Contact</a></li>
		</ul>
	</nav>
	
	<div class="logo">
		<span>FleetX</span>
	</div>
	
	<div class="buttons">
		<c:choose>
			<c:when test="${not empty sessionScope.username}">
			    <div class="userProfile">
					<a href="${contextPath}/userprofile"> <i class="fa-solid fa-user"></i></a>
					 <span>${sessionScope.username}</span>
			    </div>
			    <a class="cart" href="${contextPath}/cart">
			    <i class="fa-solid fa-cart-shopping"></i>
			    (<c:out value="${cartSize}"/>)
			    </a>
			    <form action="${contextPath}/logout" method="post">
							<input type="submit" class="nav-button" value="Logout" />
				</form>
			</c:when>
			<c:otherwise>
				<a href="${contextPath}/login">Login</a>
				<a href="${contextPath}/register">Register</a>
			</c:otherwise>
		</c:choose>
	</div>
</header>
