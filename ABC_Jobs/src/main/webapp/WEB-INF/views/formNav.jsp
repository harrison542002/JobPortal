<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="<core:url value="/resources/css/nav.css"/>">
<link rel="icon" type="image/x-icon"
	href="<core:url value="/resources/images/logo.png"/>">
</head>
<body>
	<nav class="navbar fixed-top navbar-expand-lg bg-light shadow-sm">
		<div class="container-fluid">
			<a href="<core:url value="/"/>" class="navbar-brand ms-4"> <img
				src="<core:url value="/resources/images/logo.png"/>" width="40"
				alt="logo">
			</a>
			
			<core:if test="${not empty email && not empty password}">
				<form class="d-flex" role="search" action="search" method="post"
				id="searchBox" onsubmit="return checkQueryLength();">
				<div class="search-box d-flex border rounded">
					<button class="btn custom-btn btn-primary" type="submit">
						<i class="fa-solid fa-magnifying-glass fa-xl mt-2"></i>
					</button>
					<input class="form-control" name="search" type="search"
						placeholder="Search People.." aria-label="Search">
				</div>
				</form>
			</core:if>
			
			<core:set var="email" scope="request" value="${email}"/>
            <core:set var="password" scope="request" value="${password}"/>
            <core:if test="${not empty email && not empty password}">
            <div class="me-4">
            	<h3>
           			<a href="<core:url value="/messageBoards"/>">
           				<i class="fa-solid fa-comments fa-sm"></i>
           			</a>
            	</h3>
            </div>
			<div class="dropdown" style="margin-right : 6rem;">
					<button class="btn btn-outline-primary dropdown-toggle"
						type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown"
						aria-expanded="false">
						<i class="fa-solid fa-user"></i>
					</button>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                    	<li>
                    	    <a class="dropdown-item" href="<core:url value="/profile"/>">
                    	        Profile
                    	    </a>
                    	</li>
                    	<li><a class="dropdown-item" href="<core:url value="/logout"/>">Log Out </a></li>
                    </ul>
				</div>
				<!-- Profile nav -->
			</core:if>
		</div>
	</nav>
	<script src="<core:url value="/resources/javaScript/popper.js"/>"></script>
	<script src="<core:url value="/resources/javaScript/searchBox.js"/>"></script>
</body>
</html>