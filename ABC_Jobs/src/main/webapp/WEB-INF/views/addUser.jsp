<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ABC | Set up Profile</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js">

</script>
<link href="<core:url value="/resources/css/login.css"/>"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div class="row">
     <%@ include file="/WEB-INF/views/adminNav.jsp"%>
    <div class="col-md-11">
	<div class="container mt-3">
		<h2 class="text-center pt-5 title">Add New User</h2>
		<form:form class="pt-5" id="proForm" action="/ABC_Jobs/addUser" method="post"
			onSubmit="return validation();" modelAttribute="addProfile" enctype="multipart/form-data">
			<div class="container">
				<!-- Email -->
				<div class="form-floating normal mb-3">
					<form:input type="text" class="form-control" id="email"
						placeholder="Enter Email address" path="email" name="email"/> <form:label
						for="email" path="email">Email</form:label>
				</div>
				<!-- Email -->
				<!-- Password -->
				<div class="form-floating normal mb-3">
					<form:input type="text" class="form-control" id="password"
						placeholder="Enter Password" path="password" name="password"/> <form:label
						for="password" path="password">Password</form:label>
				</div>
				<!-- Password -->
				<!-- First Name -->
				<div class="form-floating normal mb-3">
					<form:input type="text" class="form-control" id="firstName"
						placeholder="Enter Your first name" path="firstName" name="firstName" /> <form:label
						for="firstName" path="firstName">First Name</form:label>
				</div>
				<!-- First Name -->

				<!-- Last Name -->
				<div class="form-floating normal mb-3">
					<form:input type="text" class="form-control" id="lastName"
						placeholder="Enter Your last name" name="lastName" path="lastName"/> <form:label
						for="lastName" path="lastName">Last Name</form:label>
				</div>
				<!-- Last Name -->

				<!-- Profile -->
				<div class="mb-3 normal">

					<div class="container d-flex justify-content-center mb-3">
						<img id="frame"
							src="<core:url value="/resources/images/default_profile.png"/>"
							width="100px" height="100px" />
					</div>
					<label for="formFile" class="form-label normal mb-3"> <b>Choose Profile Picture</b></label> <input class="form-control uploader mb-5"
						accept=".jpg, .jpeg" type="file" id="file" name="file" onchange="preview();"/>

				</div>
				<!-- Profile -->

				<!-- Country -->
				<div class="form-floating mb-3 normal">
					<form:input type="text" class="form-control" id="country"
						placeholder="Country" name="country" path="country" /> <form:label for="country" path="country">
						Country </form:label>
				</div>
				<!-- Country -->

				<!-- City -->
				<div class="form-floating mb-5 normal">
					<form:input  type="text" class="form-control" id="city"
						placeholder="City" name="city" path="city" /> <form:label path="city" for="city"> City
					</form:label>
				</div>
				<!-- City -->

				<div class="normal mb-3">
					<p>Enter Education Qualification</p>
				</div>

				<!-- School -->
				<div class="form-floating mb-3 normal">
					<form:input type="text" class="form-control" id="school"
						placeholder="School" name="school" path="school"/> <form:label for="school" path="school">
						Graduated School </form:label>
				</div>
				<!-- School -->

				<!-- Qualification  -->
				<div class="form-floating mb-5 normal">
					<form:input type="text" class="form-control" id="qualification"
						placeholder="Qualification" name="qualification" path="qualification" /> <form:label
						for="qualification" path="qualification"> Related Qualification from School </form:label>
				</div>
				<!-- Qualification -->

				<div class="normal mb-3">
					<p>Please Add At least One Working Experience</p>
				</div>

				<!-- Company Name -->
				<div class="form-floating mb-3 normal">

					<form:input type="text" class="form-control" id="company"
						placeholder="Name of company" name="company" path="company" /> <form:label
						for="company" path="company"> Company Name </form:label>
				</div>
				<!-- Company Name -->

				<!-- Position -->
				<div class="form-floating mb-3 normal">
					<form:input type="text" class="form-control" id="position"
						placeholder="Name of company" name="position" path="position"/> <form:label
						for="position" path="position"> Position</form:label>
				</div>
				<!-- Position -->

				<!-- Location -->
				<div class="form-floating mb-3 normal">
					<form:input type="text" class="form-control" id="workedCountry"
						placeholder="Name of company" name="workedCountry" path="workedCountry"/> <form:label
						for="workedCountry" path="workedCountry"> Country been worked</form:label>
				</div>
				<!-- Location -->

				<!-- Start Date -->
				<div class="form mb-3 normal">
					<form:label for="start_date" path="start_data"> Start Date :</form:label> <form:input
						name="start_data" type="date" value="2017-06-01" path="start_data"/>
				</div>
				<!-- Start Date -->

				<!-- End Date -->
				<div class="form mb-3 normal">
					<form:label for="end_date" path="end_date"> End Date :</form:label> <form:input name="end_date"
						type="date" value="2017-06-01" path="end_date"/>
				</div>
				<!-- End Date -->


				<!-- Summary -->
				<form:label for="self_description" path="self_description" class="normal mb-3"><b>Self
                						Description</b></form:label>
				<div class="form mb-3 normal">
				<form:textarea class="form-control" path="self_description"
					rows="4"></form:textarea>
				</div>
				<!-- Summary -->

				<div class="d-grid btn-div">
					<button type="submit" class="btn btn-primary btn-lg">
						Add User</button>
				</div>
			</div>
		</form:form>
	</div>
	</div>
	</div>
	<script type="text/javascript"
		src="<core:url value="/resources/javaScript/profileForm.js"/>"></script>
</body>
</html>