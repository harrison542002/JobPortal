<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ABC | User Profile</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<core:url value="/resources/css/profile.css"/>">
</head>
<body>
	<%@ include file="/WEB-INF/views/formNav.jsp"%>
	<!-- Profile -->
	<div class="container-fluid mt-5  profile pb-5">
	<a alt="back link" href="<core:url value="/search?query=${query}"/>">
		<h5>
		<i class="fa-solid fa-chevron-left ms-1 mt-5"></i> Back
		</h5>
	</a>
		<div class="row">
			<div class="col-sm-6">
				<div class="rounded shadow-sm border mt-1 bg-light">
					<div class="container mt-3 mb-3">
						<div class="container row d-lg-flex justify-content-around">
							<img class="img-fluid"
								src="data:image/jpg;base64,${imageString}"
								alt="profile image">
							<div class="pt-4 ms-lg-3">
								<h4 class="text-center">
								${profile.firstName} ${profile.lastName}</h4>
								<h5 class="text-center text-secondary">
									<i class="fa-solid fa-map-pin text-danger"></i> ${loca.country},
									${loca.city}
								</h5>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6 mt-2">
				<div class="container shadow-sm rounded h-100 bg-light">
					<div class="container p-2">
						<div class="container mt-3 mb-5">
							<h5>
								<i class="fa-solid fa-bookmark"></i>&nbsp; <b
									class="align-center">Bio Summary</b>
							</h5>
							<p>
							    ${profile.self_description}
							</p>
						</div>
						<div class="container mt-3 mb-5">
							<h5>
								<i class="fa-solid fa-graduation-cap" style="color: #4287f5"></i>
								&nbsp; <b>Education Background</b>
							</h5>
							<core:forEach items = "${edu}" var="education">
                            <p>
                            	${education.school} - ${education.qualification}
                            </p>
                            </core:forEach>
						</div>
						<div class="container mb-5">
							<h5>
								<i class="fa-solid fa-user-pen" style="color: #9849ba"></i>
								&nbsp; <b>Experiences</b>
							</h5>
                            <core:forEach items = "${exp}" var="experience">
                            <p>
								${experience.company} <span> ${experience.position} &nbsp;
								<i>From</i> : <b>
							   <fmt:formatDate type = "date"
         							value = "${experience.start_data}"/>
							   </b>
         						
         						<i>To</i> : <b>
         						<fmt:formatDate type = "date"
         							value = "${experience.end_date}"/>
         						</b></span>
							</p>
                            </core:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Profile -->
	<%@ include file="/WEB-INF/views/footer.jsp"%>

   <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>