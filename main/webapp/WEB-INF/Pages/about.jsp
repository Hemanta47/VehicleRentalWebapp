<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About</title>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/global.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/about.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/footer.css" />
<script src="https://kit.fontawesome.com/a63c128ded.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="./component/header.jsp" />
<div class="container">
        <div class="hero">
            <h1>About Us</h1>
        </div>
        <div class="about-content">

            <div class="why-us">
                <h2>Why Choose Us</h2>
                <p>
                    We are a team of passionate developers committed to delivering high-quality web applications that
                    exceed expectations.
                    Our mission is to create efficient, scalable, and user-friendly software tailored to our clients'
                    needs.
                    With a strong belief in continuous learning and staying updated with the latest technologies, we’re
                    here to innovate and inspire.
                </p>
            </div>



            <div class="team">
                <h2 class="about-title">Meet the Team</h2>
                <ul class="team-list">
                    <li class="team-member">
                        <img src="${contextPath}/assets/bg-img/no-profile.jpg" alt="profile-pic">
                        <div class="personal-info">
                            <p class="name">Yograj Rijal</p>
                            <p class="role">Lead Developer</p>
                            <p class="bio">Hello, I am group leader</p>
                        </div>
                        <a href="">View More</a>
                    </li>

                    <li class="team-member">
                        <img src="${contextPath}/assets/bg-img/no-profile.jpg" alt="profile-pic">
                        <div class="personal-info">
                            <p class="name">Ananta Gurung</p>
                            <p class="role">UI/UX Designer</p>
                            <p class="bio">Hello, I am one of the member</p>
                        </div>
                        <a href="">View More</a>
                    </li>

                    <li class="team-member">
                        <img src="${contextPath}/assets/bg-img/no-profile.jpg" alt="profile-pic">
                        <div class="personal-info">
                            <p class="name">Sunil Phuyal</p>
                            <p class="role">Backend Engineer</p>
                            <p class="bio">Hello, I am one of the member</p>
                        </div>
                        <a href="">View More</a>
                    </li>

                    <li class="team-member">
                        <img src="${contextPath}/assets/bg-img/no-profile.jpg" alt="profile-pic">
                        <div class="personal-info">
                            <p class="name">Arun Nagarkoti</p>
                            <p class="role">QA Engineer</p>
                            <p class="bio">Hello, I am one of the member</p>
                        </div>
                        <a href="">View More</a>
                    </li>

                    <li class="team-member">
                        <img src="${contextPath}/assets/bg-img/no-profile.jpg" alt="profile-pic">
                        <div class="personal-info">
                            <p class="name">Pujan Pyoudel</p>
                            <p class="role">DevOps Engineer</p>
                            <p class="bio">Hello, I am one of the member</p>
                        </div>
                        <a href="">View More</a>
                    </li>
                </ul>
            </div>

        </div>
    </div>
    <jsp:include page="./component/footer.jsp" />
</body>
</html>