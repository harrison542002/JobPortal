<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ABC Jobs Portal | NOT FOUND</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js">
</script>
<link href="<core:url value="/resources/css/notFound.css"/>"
	rel="stylesheet" type="text/css">
</head>
<body>	
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	
	<div class="container error-info">
		<h1> <i class="fa-solid fa-face-sad-tear text-primary"></i> 
			The resources you are trying to reach is not avaliable.
		</h1>
		<h4>
			<a href="<core:url value="/"/>">
				<i class="fa-solid fa-arrow-left me-2"></i>Return Home 
			</a>
			
		</h4>
		
	</div>
	
	
	<!-- Footer-->
	<%@ include file="/WEB-INF/views/footer.jsp"%>
</body>	
</html>