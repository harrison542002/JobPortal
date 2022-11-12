<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ABC | Appreciation</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js">
    </script>
<link rel="stylesheet"
	href="<core:url value="/resources/css/thankyou.css" />">
</head>
<body>
	<%@ include file="/WEB-INF/views/formNav.jsp"%>
	<div class="container mt-5 pb-5 mb-5">
		<div class="icon">
			<i class="fa-solid fa-circle-check text-center pt-5"></i>
		</div>
		<h2 class="text-center pt-1 title">Reset Password Successful</h2>
		<p class="text-center">Thank you for your patience, please
			continue to log in with new Password</p>
		<div class="d-grid btn-div">
			<a type="button" class="btn btn-primary btn-lg"
				href="<core:url value="login"/>"> Next </a>
		</div>
	</div>
	<!-- Footer -->
	<%@ include file="/WEB-INF/views/footer.jsp"%>
</body>