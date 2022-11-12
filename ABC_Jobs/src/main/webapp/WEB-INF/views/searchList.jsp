<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ABC | Search Lists</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js">
    </script>
<link rel="stylesheet"
	href="<core:url value="/resources/css/searchList.css"/>">
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="container searchList">
	<core:if test="${notFound == 'true'}">
	    <h2 class="mt-3">No such a user, please renter search query</h2>
	    <h3><a href="<core:url value="/"/>">
	    Return Home
	    </a>
	    </h3>
	</core:if>
	<core:if test="${not empty userprofiles}">
		<h2 class="mb-3">Search Results</h2>
		<core:forEach items="${userprofiles}" var="profiles" varStatus="status">
		<div class="container people bg-light d-flex mb-3">
			<img class="img-fluid img"
				src="data:image/jpg;base64,${imageSources.get(status.index)}"
				alt="profileImage">
			<div class="container ms-5 p-3">
				<h4> ${profiles.firstName}  ${profiles.lastName}</h4>
				<p class="text-secondary">
					<i class="fa-solid fa-map-pin text-danger"></i>
					${locations.get(status.index).city} ${locations.get(status.index).country}
				</p>
				<a class="btn btn-outline-primary"
					href="<core:url value="/otherProfile/${profiles.user_profile_id}?query=${query}"/>"> View Profile </a>
			</div>
		</div>
		</core:forEach>
		<nav aria-label="Page navigation example">
          <ul class="pagination justify-content-center">
              <core:set var = "page" value = "${page}"/>
              <core:set var = "total" value = "${totalRows}"/>
                           <core:choose>
                           <core:when test="${(page <= 0) or (total <= 0)}">
                                  <li class="page-item disabled">
                            </core:when>
                           <core:otherwise>
                                  <li class="page-item">
                            </core:otherwise>
                          </core:choose>
              <a class="page-link" href="<core:url value="/searchList?page=${page - 1}&query=${query}"/>">Previous</a>
            </li>
                                       <core:choose>
                                       <core:when test="${(page >= total) or (total <= 0)}">
                                              <li class="page-item disabled">
                                        </core:when>
                                       <core:otherwise>
                                              <li class="page-item">
                                        </core:otherwise>
                                      </core:choose>
              <a class="page-link" href="<core:url value="/searchList?page=${page + 1}&query=${query}"/>">Next</a>
            </li>
          </ul>
        </nav>
		</core:if>
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>