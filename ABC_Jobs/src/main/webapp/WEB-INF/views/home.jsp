<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ABC | Home</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js">
                </script>
<link href="<core:url value="/resources/css/home.css"/>"
	rel="stylesheet" type="text/css">
</head>

<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<!-- Hero Banner -->
	<div class="row mt-5">
		<div class="col-md-6">
			<div class="first container ms-2 mb-3 mt-5">
				<h1 class="firsthead">START EARNING</h1>
				<p class="text-wrap para text-secondary mb-3">Welcome To ABC
					Jobs Limited. In Here, You can discover all range of software
					engineering jobs from this website. All jobs offer are validated
					and go through by ABC authorities.</p>

				<core:set var="email" scope="session" value="${email}"/>
                <core:set var="password" scope="session" value="${password}"/>
                <core:if test="${empty email && empty password}">
				<a href="<core:url value="registration" />">
					<button class="btn btn-lg btn-primary">Start Today</button>
				</a>
				</core:if>
			</div>
		</div>
		<div class="col-md-6">
			<img class="img-fluid"
				src="<core:url value="/resources/images/show.jpg" />"
				alt="show image">
		</div>
	</div>
	<!-- Hero Banner -->

	<!-- Partner -->
	<div class="trusted row">
		<h1 class="trusttitle mt-2 mb-2">Trusted By</h1>
		<div class="col-md-4 mt-4 mb-4">
			<div class="container d-flex justify-content-center">
				<img class="img-fluid"
					src="<core:url value="/resources/images/p1.png" />" alt="logo"
					width="64px">
			</div>
		</div>
		<div class="col-md-4 mt-4 mb-4">
			<div class="container d-flex justify-content-center">
				<img class="img-fluid"
					src="<core:url value="/resources/images/p2.png" />" alt="logo"
					width="64px">
			</div>
		</div>
		<div class="col-md-4 mt-4 mb-4">
			<div class="container d-flex justify-content-center">
				<img class="img-fluid"
					src="<core:url value="/resources/images/p3.png" />" alt="logo"
					width="60px">
			</div>
		</div>
	</div>
	<!-- Partner  -->

	<!-- Feedback  -->
	<div class="row pb-5">
		<h1 class="trusttitle text-secondary mb-2">Sounds From Partner</h1>
		<div class="col-md-4">
			<div class="container">
				<div class="card partner-card rounded shadow h-100">
					<div class="container mt-3 brand">
						<h2>
							<img class="img-fluid"
								src="<core:url value="/resources/images/p1.png" />" alt="logo"
								width="64px"> Jobicy
						</h2>
						<h4 class="quote mt-3">"ABC Jobs enables us to differentiate
							ourselves from our competitors and produce content at a higher
							caliber.</h4>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="container">
				<div class="card partner-card rounded shadow h-100">
					<div class="container mt-3 brand">
						<h2>
							<img class="img-fluid"
								src="<core:url value="/resources/images/p2.png" />" alt="logo"
								width="64px"> Google
						</h2>
						<h4 class="quote mt-3">"One of the advantages of utilizing
							freelancers is finding talent with different skills quickly as
							our needs change."</h4>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="container">
				<div class="card partner-card rounded shadow h-100">
					<div class="container mt-3 brand">
						<h2>
							<img class="img-fluid"
								src="<core:url value="/resources/images/p3.png" />" alt="logo"
								width="45px"> Figma
						</h2>
						<h4 class="quote mt-3">I don't think business would have
							grown as fast or as well if not for the freelancers' expertise
							and ability to adapt."</h4>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Feedback  -->

	<!-- Footer-->
	<%@ include file="/WEB-INF/views/footer.jsp"%>
	<script src="<core:url value="/resources/javaScript/popper.js"/>"></script>

</body>