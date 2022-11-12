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
	href="<core:url value="/resources/css/login.css"/>">
<script src="https://kit.fontawesome.com/dfffc10f23.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/formNav.jsp"%>
	<div class="container mt-5 pb-5 mb-5">
		<h2 class="text-center pt-5 title">
			Administration System <i class="fa-solid fa-screwdriver-wrench"></i>
		</h2>
		<form class="pt-5" id="adminForm" action="adminCheck" method="post">
			<div class="container">
				<!-- username -->
				<div class="form-floating normal mb-3">
					<input type="text" class="form-control" id="username" name="username"
						placeholder="username"/>
					<label for="username">Username</label>
				</div>
				<!-- username -->

				<!-- Password -->
				<div class="form-floating normal mb-3">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="password"/> <label
						for="password">Password</label>
				</div>
				<!-- Password -->

				<div class="d-grid btn-div">
					<button type="submit" class="btn btn-primary btn-lg">Log
						In</button>
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
                <p>Incorrect username or password, please renter credential</p>
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
		src="<core:url value="/resources/javaScript/admin.js"/>"></script>
</body>
</html>