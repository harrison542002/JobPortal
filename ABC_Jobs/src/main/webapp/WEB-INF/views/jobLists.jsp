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
				<div class="cwrapper fixed border rounded shadow-sm">
					<div class="content">
						<core:if test="${not empty id}">
							<a class="myjobs" href="<core:url value="/myJobs"/>">
								<p>
									<i class="fa-solid fa-bookmark fa-lg icons"></i> My Jobs
								</p>
							</a>
						</core:if>
	
						<core:if test="${empty id}">
							<h3 class="text-center mb-3">Login In to start Apply Jobs !
							</h3>
							<div class="login container">

								<p class="text-center">
									<a class="shadow-sm" href="<core:url value="/login"/>">Log
										In </a>
								</p>
							</div>
						</core:if>


					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="container job-lists shadow-sm rounded">

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
												<i class="fa-regular fa-bookmark fa-xl saved"
													data-uid="${id}" data-jid="${job.job_id}"> </i>
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
	
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<core:set var="page" value="${page}" />
			<core:set var="total" value="${totalRows}" />
			<core:choose>
				<core:when test="${(page <= 0) or (total <= 0)}">
					<li class="page-item disabled">
				</core:when>
				<core:otherwise>
					<li class="page-item">
				</core:otherwise>
			</core:choose>
			<a class="page-link"
				href="<core:url value="/findJob?page=${page - 1}"/>">Previous</a>
			</li>
			<core:choose>
				<core:when test="${(page >= total) or (total <= 0)}">
					<li class="page-item disabled">
				</core:when>
				<core:otherwise>
					<li class="page-item">
				</core:otherwise>
			</core:choose>
			<a class="page-link"
				href="<core:url value="/findJob?page=${page + 1}"/>">Next</a>
			</li>
		</ul>
	</nav>
	
	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<script src="https://code.jquery.com/jquery-3.6.1.min.js"
		integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
		crossorigin="anonymous"></script>
	<script src="<core:url value="/resources/javaScript/job.js"/>"></script>
</body>
</html>