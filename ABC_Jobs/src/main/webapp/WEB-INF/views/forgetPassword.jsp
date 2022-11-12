<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ABC | Forget Password</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="<core:url value="/resources/css/login.css" />">
<script src="https://kit.fontawesome.com/dfffc10f23.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/formNav.jsp"%>
	<div class="container mt-5 pb-5 mb-5">
		<h2 class="text-center pt-5 title">Enter Your Account Email
			Address</h2>
		<p class="text-center">Please enter the email address which you
			used for registration</p>
		<form class="pt-5" action="verification?mode=login" method="post"
			onSubmit="return validation()" id="forgetForm">
			<div class="container">
				<div class="form-floating normal mb-3">
					<input type="text" class="form-control" id="email" name="email"
						placeholder="email"> <label for="email">Email
						Address</label>
				</div>
				<div class="d-grid btn-div">
					<button type="submit" class="btn btn-primary btn-lg">Next
					</button>
				</div>
			</div>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
	<script type="text/javascript"
		src="<core:url value="/resources/javaScript/forgetVd.js"/>"></script>
</body>
</html>