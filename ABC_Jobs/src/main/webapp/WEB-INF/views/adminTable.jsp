<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administration</title>
    <link rel="stylesheet"
        	href="<core:url value="/resources/css/admin.css"/>">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/dfffc10f23.js" crossorigin="anonymous"></script>
</head>
<body>
    <div class="row vh-100">
    <%@ include file="/WEB-INF/views/adminNav.jsp"%>
        <div class="col-md-11">
            <h2 class="text-center mt-3 title">
                    Administration System
                </h2>
                <table class="table">
                     <thead class="table-info">
                      <tr>
                        <th scope="col"></th>
                        <th scope="col">Email address</th>
                        <th scope="col">Password</th>
                        <th scope="col">First Name</th>
                        <th scope="col">Last Name</th>
                        <th scope="col">Country</th>
                        <th scope="col">City</th>
                        <th scope="col"></th>
                      </tr>
                    </thead>
                    <tbody>
                    <core:forEach items="${userprofiles}" var="profile" varStatus="status">
                      <tr>
                        <td>${status.index + 1}</td>
                        <td>${users.get(status.index).email}</td>
                        <td>${users.get(status.index).password}</td>
                        <td>${profile.firstName}</td>
                        <td>${profile.lastName}</td>
                        <td>${locations.get(status.index).country}</td>
                        <td>${locations.get(status.index).city}</td>
                        <td>
                            <a class="btn btn-info" href="<core:url value="/edit/${profile.user_profile_id}"/>">Edit</a>
                            <a class="btn btn-danger" onclick="deleteTrigger(${profile.user_profile_id},${locations.get(status.index).l_id},${users.get(status.index).user_id});">Delete</a>
                        </td>
                      </tr>
                     </core:forEach>
                    </tbody>
                  </table>
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
                                    <a class="page-link" href="<core:url value="/adminTable?page=${page - 1}"/>">Previous</a>
                                  </li>
                                                    <core:set var = "page" value = "${page}"/>
                                                    <core:set var = "total" value = "${totalRows}"/>
                                                             <core:choose>
                                                             <core:when test="${(page >= total) or (total <= 0)}">
                                                                    <li class="page-item disabled">
                                                              </core:when>
                                                             <core:otherwise>
                                                                    <li class="page-item">
                                                              </core:otherwise>
                                                            </core:choose>
                                    <a class="page-link" href="<core:url value="/adminTable?page=${page + 1}"/>">Next</a>
                                  </li>
                                </ul>
                              </nav>
    </div>
    </div>
      <script type="text/javascript"
      		src="<core:url value="/resources/javaScript/admin.js"/>"></script>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js" integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous"></script>


</body>
</html>