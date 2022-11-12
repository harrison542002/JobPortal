<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Job Detail | ABC Jobs Portal</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<core:url value="/resources/css/jobDetail.css"/>">
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js">
    </script>
<script src="https://kit.fontawesome.com/dfffc10f23.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="container job-wrapper">
		<div class="container pt-3 pb-3">
			<a href="<core:url value="/findJob"/>">
				<h4>
					<i class="fa-solid fa-arrow-left-long fa-lg me-3"></i> Back to job
					lists
				</h4>
			</a>

		</div>

		<div class="row">
			<div class="col-md-8">
				<div class="container mb-4 bg-light border rounded shadow-sm">
					<div class="container mb-4 pt-4">
						<h2>${job.job_title}</h2>
						<h5>${job.company_name}</h5>
						<p>
							<i class="fa-solid fa-map-pin text-danger me-2"></i>
							${job.country}
						</p>
						<div class="mt-3">
						
						<core:if test="${not empty id}">
							<a class="btn btn-primary apply" data-uid="${id}" 
								data-jid="${job.job_id}"> Apply Now </a> <a
								class="btn btn-outline-primary saved" data-uid="${id}"
								data-jid="${job.job_id}"> Save </a>
						</core:if>
						
						<core:if test="${empty id}">
							<a class="btn btn-primary" href="<core:url value="/registration"/>"> 
								Register To Start Apply
							</a>
						</core:if>
						</div>
					</div>

					<div class="container">
						<div class="mt-5 mb-4">
							<h5>Job Requirements</h5>
							<p>${job.requirements}</p>
						</div>

						<div class="mb-4">
							<h5>Benefits</h5>
							<p>${job.benefits}</p>
						</div>

						<div class="mb-4">
							<h5>Company Information</h5>
							<p>${job.company_Information}</p>
						</div>

						<div class="mb-4">
							<h5>Contact Info</h5>
							<p>${job.contact_info}</p>
						</div>

						<div class="pb-4">
							<h5>Additional Information</h5>
							<p>${job.additional_information}</p>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="container-fluid mb-3">
					<h1>Related Job Posts</h1>
				</div>
				<core:if test="${not empty jobs}">
					<core:forEach items="${jobs}" var="jobb" varStatus="status">

						<div class="container bg-light border rounded shadow-sm mb-3">
							<div class="pt-3">
								<h4>
									<a href="<core:url value="/jobDetail?jid=${jobb.job_id}"/>">
										${jobb.job_title} </a>
								</h4>
							</div>
							<div class="pt-3 pb-3">
								<p>${jobb.company_name}</p>
								<p>${jobb.salary}</p>
							</div>
						</div>
					</core:forEach>
				</core:if>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
	<script src="https://code.jquery.com/jquery-3.6.1.min.js"
		integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
		crossorigin="anonymous">
	</script>
	<script type="text/javascript"
		src="<core:url value="/resources/javaScript/job.js"/>">
	</script>
	<script type="text/javascript"
		src="<core:url value="/resources/javaScript/apply.js"/>">
	</script>
</body>

</html>