<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>JobOffers | ABC Jobs</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<core:url value="/resources/css/job.css" />">
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js">
    </script>
<script src="https://kit.fontawesome.com/dfffc10f23.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="container-fluid pt-5 pb-3">
		<div class="row mt-5">
			<div class="col-md-3">
				<div class="cwrapper fixed border rounded shadow-sm bg-light">
					<div class="content">
						<core:if test="${not empty id}">
							<a class="myjobs" href="<core:url value="/findJob" />">
								<p>
								<i class="fa-sharp fa-solid fa-arrow-left-long fa-lg icons"></i> Back To Job Lists
								</p>
							</a>
						</core:if>

						<core:if test="${empty id}">
							<h3 class="text-center mb-3">
								Login In to start Apply Jobs !
							</h3>
							<div class="login container">

								<p class="text-center">
									<a class="shadow-sm" href="<core:url value="/login"/>">Log In </a>
								</p>
							</div>
						</core:if>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="container job-lists shadow-sm rounded bg-light">
					<core:if test="${empty jobs}">
						<h3 class="text-center mt-5">
							Sorry You don't Have Any Saved Job, Save Now!
						</h3>
						<div class="findbtn">
							<a class="btn btn-primary btn-lg" href="<core:url value="/findJob"/>">
							Find Job And Save
							</a>
						</div>	
						
						<div class="container imageCon">
								<img class="w-50" src="<core:url value="/resources/images/notJobs.png"/>">
						</div>
					</core:if>
					<core:if test="${not empty jobs}">
						<core:forEach items="${jobs}" var="job" varStatus="status">
							<div class="job-offer">
								<div class="info">
									<div class="row">
										<div class="col-10">
											<a class="job-link" href="<core:url value="/jobDetail?jid=${job.job_id}"/>">
												<h4 class="mr-auto">${job.job_title}</h4>
											</a>
										</div>
										<div class="col-2">

											<core:if test="${not empty id}">
												<i class="fa-regular fa-bookmark fa-xl saved" data-toggle="tooltip" data-placement="right" 
                            title="Remove Jobs from Saved Jobs" data-uid="${id}" data-jid="${job.job_id}"> </i>
											</core:if>
										</div>
									</div>
									<p class="cn">
										<i class="fa-solid fa-building"></i> ${job.company_name}
									</p>
									<p class="location">
										<i class="fa-sharp fa-solid fa-location-dot"></i>
										${job.country}
									</p>
									<p class="salary text-success">
										<i class="fa-solid fa-hand-holding-dollar"></i> ${job.salary}
									</p>
								</div>
							</div>
						</core:forEach>
					</core:if>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<script src="https://code.jquery.com/jquery-3.6.1.min.js"
		integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
		crossorigin="anonymous"></script>
	<script src="<core:url value="/resources/javaScript/savejob.js"/>"></script>
</body>
</html>