<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ABC | Registration</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<core:url value="/resources/css/registration.css" />">
</head>
<body>
	<%@ include file="/WEB-INF/views/formNav.jsp"%>
	<!-- Registration Form -->
	<div class="container mt-5 mb-5">
		<h2 class="text-center pt-5 title">Sign Up Now To Land Your Dream
			Career</h2>
		<form:form class="pt-5" id="regForm" onsubmit="return validation();"
			action="verification" method="post" modelAttribute="user">
			<div class="container">
				<div class="form-floating normal mb-3">
					<form:input type="text" class="form-control" id="email"
						placeholder="name@example.com" path="email" name="email" />
					<form:label path="password" for="email">Email address</form:label>
				</div>

				<div class="form-floating normal mb-3">
					<form:input type="password" class="form-control" id="password"
						placeholder="Password" path="password" name="password"/>
					<form:label path="password" for="password">Enter Password</form:label>
				</div>

				<div class="d-grid btn-div">
					<button type="submit" class="btn btn-primary btn-lg">Sign
						Up</button>
				</div>

				<div class="row d-flex flex-row justify-content-center mt-2">
					<hr class="mt-3 col-6">
					<span class="col-1 text-center"> or </span>
					<hr class="mt-3 col-5">
				</div>

				<div class="d-grid btn-div">
					<a type="button" class="btn btn-success btn-lg" href="<core:url value="/login"/>">Log
						In</a>
				</div>
			</div>
		</form:form>
	</div>

	<!-- Footer -->
	<%@ include file="/WEB-INF/views/footer.jsp"%>

    <core:set var="hasaccount" scope="session" value="${hasAccount}"/>
    <core:if test="${hasaccount == 'true'}">
	<div id="errorMsg" class="modal" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title"><i class="fa-solid fa-circle-exclamation text-danger"></i> Error Message</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <p>This email had been already created an account.</p>
            <a href="<core:url value="/login"/>"> Log In</a>
          </div>
        </div>
      </div>
    </div>
    </core:if>
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"
        	integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
        	crossorigin="anonymous"></script>
    <script type="text/javascript"
    	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript"
		src="<core:url value="/resources/javaScript/form.js"/>"></script>
</body>
</html>