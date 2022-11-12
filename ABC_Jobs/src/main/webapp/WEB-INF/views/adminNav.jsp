<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <link rel="icon" type="image/x-icon"
    	href="<core:url value="/resources/images/logo.png"/>">
    <script src="https://kit.fontawesome.com/dfffc10f23.js"
        	crossorigin="anonymous"></script>
    <style>
    	.fa-solid {
    		color : #b7d7f7;
    	}
    	.fa-solid:hover{
    		color : #ada5a5;
    	}
    </style>
</head>
<div class="col-md-1">
            <div class="navbar d-flex flex-column flex-shrink-0 bg-dark bg-gradient shadow" style="width: 4.5rem; height: 100%;">
                <ul class="nav flex-sm-column">
                    <li class="nav-item d-flex justify-content-center">
                       <img class="mb-5" src="<core:url value="/resources/images/logo.png"/>" width="40" alt="logo">
                    </li>
                    <li class="nav-item d-flex justify-content-center mb-5">
                        <a class="nav-link" href="<core:url value="/adminTable"/>">
                            <i class="fa-solid fa-house fa-2xl" data-toggle="tooltip" data-placement="right" 
                            title="User Information"></i>
                        </a>
                    </li>
                    <li class="nav-item d-flex justify-content-center mb-5">
                        <a class="nav-link" aria-current="page" href="<core:url value="/addUser"/>">
                            <i class="fa-solid fa-square-plus fa-2xl" data-toggle="tooltip" data-placement="right" 
                            title="Add new User"></i>
                        </a>
                    </li>
                    <li class="nav-item justify-content-center mb-5">
                        <a class="nav-link" aria-current="page" href="<core:url value="/emailConstruction"/>">
                            <i class="fa-solid fa-paper-plane fa-2xl"
                            data-toggle="tooltip" data-placement="right" 
                            title="Send Bulk Email"></i>
                        </a>
                    </li>
                    <li class="nav-item justify-content-center mb-5">
                        <a class="nav-link" aria-current="page" href="<core:url value="/jobForm"/>">
                            <i class="fa-solid fa-briefcase fa-2xl" 
                            data-toggle="tooltip" data-placement="right" 
                            title="Post New Job Offer"></i>
                        </a>
                    </li>
                    
            <core:set var="username" scope="session" value="${username}"/>
            <core:set var="password" scope="session" value="${password}"/>
            <core:if test="${not empty username && not empty password}">
            <li>
            		<div class="dropdown ms-2" id="nav-icon">
            			<button class="btn btn-outline-primary dropdown-toggle"
            					type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown"
            					aria-expanded="false">
            				<i class="fa-solid fa-user"></i>
            				</button>
            				<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                <li><a class="dropdown-item" href="<core:url value="/adminLogout"/>">
                                Log Out </a></li>
                             </ul>
            		</div>
            		<!-- Profile nav -->
            </li>
            </core:if>
            </ul>
            </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"
                	integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
                	crossorigin="anonymous">
</script>
<script>
	$(document).ready(function(){
			$(".fa-square-plus").hover(function(){
				$(".fa-square-plus").tooltip("toggle")
			})
			
			$(".fa-house").hover(function(){
				$(".fa-house").tooltip("toggle")
			})
			
			$(".fa-paper-plane").hover(function(){
				$(".fa-paper-plane").tooltip("toggle")
			})
			
			$(".fa-briefcase").hover(function(){
				$(".fa-briefcase").tooltip("toggle")
			})
	})
</script>