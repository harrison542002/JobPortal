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
    <title>ABC | Post Job</title>
    <link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css">
    <script type="text/javascript"
        	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js">
    </script>
    <link rel="stylesheet"
    href="<core:url value="/resources/css/postJob.css"/>"/>
</head>

<body>
	<div class="row vh-100">
	<%@ include file="/WEB-INF/views/adminNav.jsp"%>
	<div class="col-md-11">
    <h2 class="text-center mt-3 title">
        Posting Job
    </h2>
    <form:form class="container postForm rounded" id="postForm"
    action="/ABC_Jobs/postJob" method="post" onSubmit="return validation()" modelAttribute="jobOffer">
    	<div class="form-floating mb-4">
            <form:input type="text" class="form-control" id="job_title" path="job_title" placeholder="Spring MVC.."/>
            <form:label for="job_title" path="job_title">Job Title</form:label>
        </div>
        <div class="form-group mb-4">
            <form:label for="requirements" path="requirements">Requirement of position</form:label>
            <form:textarea type="text" path="requirements" class="form-control" id="requirements" placeholder="Type in requirements ..."
            rows="5"/>
        </div>
        <div class="form-floating mb-4">
            <form:input type="text" path="salary" class="form-control" id="salary" placeholder="Negotiate"/>
            <form:label path="salary" for="salary">Salary</form:label>
        </div>
        <div class="form-group mb-4">
            <form:label path="benefits" for="benefits">Benefits from Company</form:label>
            <form:textarea path="benefits" type="text" class="form-control" id="benefits" placeholder="Benefits from Job"/>
        </div>
        <div class="form-group mb-4">
            <form:label path="company_Information" for="company_Information">Company Detail</form:label>
            <form:textarea path="company_Information" type="text" class="form-control" id="company_Information" placeholder="Enter Company validated Info"
            rows="5"/>
        </div>
        <div class="form-floating mb-4">
            <form:input path="company_name" type="text" class="form-control" id="company_name" placeholder="company_name"/>
            <form:label path="company_name" for="company_name">Name of Company</form:label>
        </div>
        <div class="form-floating mb-4">
            <form:input path="country" type="text" class="form-control" id="country" placeholder="country"/>
            <form:label path="country" for="country">Location Country</form:label>
        </div>
        <div class="form-floating mb-4">
            <form:input path="contact_info" type="text" class="form-control" id="contact_info" placeholder="company@google.com"/>
            <form:label path="contact_info" for="contact_info">Contact Information to Reach</form:label>
        </div>
        <div class="form-group mb-4">
            <form:label path="additional_information" for="additional_information">Additional Information</form:label>
            <form:textarea path="additional_information"  type="text" class="form-control" id="additional_information" placeholder="Additional Info to attract talent"
            rows="3"/>
        </div>
        <div class="form-group mb-4">
            <button class="btn btn-primary" type="submit">
                Submit
            </button>
        </div>
    </form:form>
    </div>
    </div>
    
    <core:set var="msgs" scope="session" value="${success}"/>
    <core:if test="${msgs == 'true'}">
	<div id="successMsg" class="modal" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title"><i class="fa-solid fa-circle-check" style="color : green;"></i> Success Message</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <p>The Job Offer has been posted</p>
          </div>
        </div>
      </div>
    </div>
    </core:if>
</body>
<script type="text/javascript" src="<core:url value="/resources/javaScript/postJob.js"/>"></script>
</html>