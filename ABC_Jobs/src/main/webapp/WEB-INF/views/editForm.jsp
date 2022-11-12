<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ABC | Login</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<core:url value="/resources/css/login.css" />">
</head>
<body>
	<div class="row">
	<%@ include file="/WEB-INF/views/adminNav.jsp"%>
    <div class="col-md-11">
	<div class="container mt-5 pb-5 mb-5">
		<h2 class="text-center pt-5 title">
			Edit User Info <i class="fa-solid fa-pen-to-square"></i>
		</h2>
		<form class="pt-5" id="editForm" action="/ABC_Jobs/editedData?p_id=${userprofile.user_profile_id}&u_id=${user.user_id}&l_id=${location.l_id}" method="post"
			onSubmit="return validation()">
			<div class="container">
				<!-- Email -->
				<div class="form-floating normal mb-3">
					<input type="text" class="form-control" id="email" name="email"
					value="${user.email}"
						placeholder="email"/>
					<label path="email" for="email">Email Address</label>
				</div>
				<!-- Email -->

				<!-- Password -->
				<div class="form-floating normal mb-3">
					<input type="password" class="form-control" id="password" value="${user.password}"
						name="password" placeholder="password"/> <label
						for="password">Password</label>
				</div>
				<!-- Password -->
				<!-- FirstName -->
				<div class="form-floating normal mb-3">
					<input type="text" class="form-control" id="firstName" value="${userprofile.firstName}"
						name="firstName" placeholder="firstName"/> <label
						for="firstName">First Name</label>
				</div>
				<!-- FirstName -->
				<!-- LastName -->
				<div class="form-floating normal mb-3">
					<input type="text" class="form-control" id="lastName" value="${userprofile.lastName}"
						name="lastName" placeholder="lastName"/> <label
						for="lastName">Last Name</label>
				</div>
				<!-- LastName -->
				<!-- Country -->
				<div class="form-floating normal mb-3">
					<input type="text" class="form-control" id="country" value="${location.country}"
						name="country" placeholder="country"/> <label
						for="country">Country</label>
				</div>
				<!-- Country -->
				<!-- City -->
				<div class="form-floating normal mb-3">
					<input type="text" class="form-control" id="city" value="${location.city}"
						name="city" placeholder="city"/> <label
						for="city">City</label>
				</div>
				<!-- City -->
				<div class="d-grid btn-div">
					<button type="submit" class="btn btn-primary btn-lg">Submit</button>
				</div>
			</div>
		</form>
	</div>
	</div>
        <script src="https://code.jquery.com/jquery-3.6.1.min.js"
                	integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
                	crossorigin="anonymous"></script>
        <script type="text/javascript"
        	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript"
		src="<core:url value="/resources/javaScript/loginValidate.js"/>"></script>
</body>
</html>