<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
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
<script src="https://kit.fontawesome.com/dfffc10f23.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/formNav.jsp"%>
	<div class="container mt-5 pb-5 mb-5">
		<h2 class="text-center pt-5 title">
			Welcome Back <i class="fa-solid fa-heart" style="color: red;"></i>
		</h2>
		<form class="pt-5" id="loginForm" action="profileLogin" method="post"
			onSubmit="return validation()">
			<div class="container">
				<!-- Email -->
				<div class="form-floating normal mb-3">
					<input type="text" class="form-control" id="email" name="email"
						placeholder="email"/>
					<label path="email" for="email">Email Address</label>
				</div>
				<!-- Email -->

				<!-- Password -->
				<div class="form-floating normal mb-3">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="password"/> <label
						for="password">Password</label>
				</div>
				<!-- Password -->

				<div class="normal mb-3">
					<a href="forgetPassword?mode=login" class="h5"> Forget
						Password? </a>
				</div>

				<div class="d-grid btn-div">
					<button type="submit" class="btn btn-primary btn-lg">Log
						In</button>
				</div>

				<div class="row d-flex flex-row justify-content-center mt-2">
					<hr class="mt-3 col-6">
					<span class="col-1 text-center"> or </span>
					<hr class="mt-3 col-5">
				</div>


				<div class="d-grid btn-div">
				<a href="<core:url value="registration" />">
					<button type="button" class="btn btn-success btn-lg w-100">
							Create Account
					</button>
				</a>
				</div>
			</div>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
	<core:set var="errorMsg" scope="session" value="${error}"/>
        <core:if test="${error == 'true'}">
    	<div id="errorMsg" class="modal" tabindex="-1">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title"><i class="fa-solid fa-circle-exclamation text-danger"></i> Error Message</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <p>Incorrect email address or password, please renter credential or</p>
                <p>
                    <i class="fa-solid fa-hand-point-down fa-lg  text-primary"></i>
                </p>
                <a href="<core:url value="/registration"/>">Register</a>
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
		src="<core:url value="/resources/javaScript/loginValidate.js"/>"></script>
</body>
</html>