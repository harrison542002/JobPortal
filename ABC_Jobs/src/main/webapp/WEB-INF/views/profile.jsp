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
	<script type="text/javascript"
    		src="<core:url value="/resources/javaScript/profile.js"/>"></script>
   <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<!-- Profile -->
	<div class="container-fluid mt-5 profile pb-5">
		<div class="row mt-5">
			<div class="col-12 col-sm-6 mt-5">
				<div class="rounded border shadow-sm bg-light">
					<div class="container mt-3 mb-3">
						<div class="container row d-lg-flex justify-content-around">
							<img class="img-fluid"
								src="data:image/jpg;base64,${imageString}"
								alt="profile image">
							<div class="pt-4 ms-lg-3">
								<h4 class="text-center">${profile.firstName} ${profile.lastName}</h4>
								<h5 class="text-center text-secondary">
									<i class="fa-solid fa-map-pin text-danger"></i> ${loca.country},
									${loca.city}
								</h5>
							</div>
						</div>
					</div>
				</div>
				<div class="mt-2">
					<button class="btn btn-secondary" data-bs-toggle="modal"
						data-bs-target="#editModal">Edit My profile</button>
				</div>
			</div>
			<div class="col-12 col-sm-6 mt-5">
				<div class="container rounded shadow-sm h-100 bg-light">
					<div class="container p-2">
						<div class="container mt-3 mb-5">
							<h5>
								<i class="fa-solid fa-bookmark"></i>&nbsp; <b
									class="align-center">Bio Summary</b> <a data-bs-toggle="modal"
									data-bs-target="#editSummary"> <i
									class="fa-solid fa-pen-to-square"></i>
								</a>
							</h5>
							<p>
							    ${profile.self_description}
							</p>
						</div>
						<div class="container mt-3 mb-5">
							<h5>
								<i class="fa-solid fa-graduation-cap" style="color: #4287f5"></i>
								&nbsp; <b>Education Background</b> <a data-bs-toggle="modal"
									data-bs-target="#editEdu"> <i
									class="fa-regular fa-square-plus"></i>
								</a>
							</h5>
							<core:forEach items = "${edu}" var="education">
                            <p>
                            	${education.school} - ${education.qualification} <a data-bs-toggle="modal"
                             data-bs-target="#editEdu${education.edu_id}"> <i class="fa-solid fa-pen-to-square"></i></a>
                            </p>
                            </core:forEach>
						</div>
						<div class="container mb-5">
							<h5>
								<i class="fa-solid fa-user-pen" style="color: #9849ba"></i>
								&nbsp; <b>Experiences</b>
								<a data-bs-toggle="modal"
									data-bs-target="#editExperience">
									 <i class="fa-regular fa-square-plus"></i>
								</a>
							</h5>
                            <core:forEach items = "${exp}" var="experience">
                            <p>
								${experience.company} <span> ${experience.position}
								&nbsp;
							   <i>From</i> : <b>
							   <fmt:formatDate type = "date"
         							value = "${experience.start_data}"/>
							   </b>
         						
         						<i>To</i> : <b>
         						<fmt:formatDate type = "date"
         							value = "${experience.end_date}"/>
         						</b>
         						</span> <a data-bs-toggle="modal"
									data-bs-target="#editExperience${experience.experience_id}"> <i
									class="fa-solid fa-pen-to-square"></i>
								</a>
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

	<!-- Edit Profile -->
	<div class="modal fade" id="editModal" tabindex="-1"
		aria-labelledby="editLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header sticky-top bg-light">
					<h5 class="modal-title" id="exampleModalLabel">Edit Profile</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
		</div>
				<div class="modal-body">
					<form id="editForm" action="editProfile?u_id=${profile.user_profile_id}&l_id=${loca.l_id}" method="post" enctype="multipart/form-data" onsubmit="return validation();">
						<div class="container">
							<!-- First Name -->
							<div class="form-floating normal mb-3">
								<input type="text" class="form-control" id="firstName"
									placeholder="Enter Your first name"  name="firstName"
									value="${profile.firstName}"/> <label
									for="firstName">First Name</label>
							</div>
							<!-- First Name -->

							<!-- Last Name -->
							<div class="form-floating normal mb-3">
								<input type="text" class="form-control" id="lastName"
									placeholder="Enter Your last name" name="lastName"
									value="${profile.lastName}"/>
								<label for="lastName">Last Name</label>
							</div>
							<!-- Last Name -->

							<!-- Profile -->
							<div class="mb-3">
								<div class="container d-flex justify-content-center mb-3">
									<img id="frame" src="data:image/jpg;base64,${imageString}"
										width="100px" height="100px"/>
								</div>
								<label for="file" class="form-label normal mb-3"> <b>Choose
										Your Profile Picture</b></label> <input class="form-control uploader mb-5"
									accept=".jpg, .jpeg" type="file" id="file" name="file" onchange="preview();">
							</div>
							<!-- Profile -->

							<!-- Country -->
							<div class="form-floating mb-3 normal">
								<input type="text" class="form-control" id="country"
									placeholder="Country" name="country"
									value="${loca.country}"/> <label for="country">
									Country </label>
							</div>
							<!-- Country -->

							<!-- City -->
							<div class="form-floating mb-5 normal">
								<input type="text" class="form-control" id="city"
									placeholder="City" name="city"
									value="${loca.city}"/> <label for="city">
									City </label>
							</div>
							<!-- City -->

							<!-- Summary -->
							<div class="normal mb-5">
                            <label for="selfSummary" class="normal mb-3"><b>Self Description</b></label>
                            <textarea class="form-control normal mb-3" id="selfSummary" name="selfSummary"
                            rows="4">${profile.self_description}
                            </textarea>
                            <!-- Summary -->
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary"\>Save changes</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- Edit modal -->

	<!-- Edit-summary -->
    <div class="modal fade" id="editSummary" tabindex="-1"
    		aria-labelledby="exampleModalLabel" aria-hidden="true">
    		<div class="modal-dialog">
    			<div class="modal-content">
    				<div class="modal-header sticky-top bg-light">
    					<h5 class="modal-title" id="exampleModalLabel">Edit Bio
    						Summary</h5>
    					<button type="button" class="btn-close" data-bs-dismiss="modal"
    						aria-label="Close"></button>
    				</div>
    				<div class="modal-body">
    					<form method="post" action="eduDesc?u_id=${profile.user_profile_id}">
    						<div class="container">
    							<!-- Summary -->
    							<label for="selfSummary" class="normal mb-3"><b>Self
    									Description</b></label>
    							<textarea class="form-control normal mb-3" id="selfSummary" name="selfSummary"
    								rows="4">${profile.self_description}
    							</textarea>
    							<!-- Summary -->
    						</div>
    						<div class="modal-footer">
    							<button type="button" class="btn btn-secondary"
    								data-bs-dismiss="modal">Close</button>
    							<button type="submit" class="btn btn-primary"
    								data-bs-dismiss="modal">Save changes</button>
    						</div>
    					</form>
    				</div>
    			</div>
    		</div>
    	</div>
    	<!-- Edit summary -->

	<!-- Edit-Edu -->

     <core:forEach items = "${edu}" var="education">
             <div class="modal fade" id="editEdu${education.edu_id}" tabindex="-1"
             		aria-labelledby="eduLabel" aria-hidden="true">
             		<div class="modal-dialog">
             			<div class="modal-content">
             				<div class="modal-header sticky-top bg-light">
             					<h5 class="modal-title" id="eduLabel">Edit Education</h5>
             					<button type="button" class="btn-close" data-bs-dismiss="modal"
             						aria-label="Close"></button>
             				</div>
             				<div class="modal-body">
             					<form method="post" id="editEduForm" action="editEdu/${education.edu_id}">
             						<div class="container">
             							<!-- Education -->
             							<div class="form-floating mb-3 normal">
             								<input type="text" class="form-control" id="school${education.edu_id}"
             									placeholder="School Name" name="eduschool" value="${education.school}"/> <label
             									for="school"> Name of School </label>
             							</div>
             							<!-- Qualification -->
             							<div class="form-floating mb-3 normal">
             								<input type="text" class="form-control" id="qualification${education.edu_id}"
             									placeholder="Qualification" name="eduqualification"
             									value="${education.qualification}"/> <label
             									for="qualification"> Qualification </label>
             							</div>
             						</div>
             						<div class="modal-footer">
             							<button type="button" class="btn btn-secondary"
             								data-bs-dismiss="modal">Close</button>
             							<button type="submit" class="btn btn-primary">Save changes</button>
             						</div>
             					</form>
             				</div>
             			</div>
             		</div>
             	</div>
             	<!-- Edit-Edu -->
     </core:forEach>

     <!-- Insert Education -->
	<div class="modal fade" id="editEdu" tabindex="-1"
		aria-labelledby="editLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header sticky-top bg-light">
					<h5 class="modal-title" id="exampleModalLabel">Insert Education</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form method="post" id="eduForm" action="profileEdu" enctype="multipart/form-data" onsubmit="return eduValidation();">
						<div class="container">
							<!-- Education -->
							<div class="form-floating mb-3 normal">
								<input type="text" class="form-control" id="school"
									placeholder="School Name" name="school"/> <label
									for="floatingInput"> Name of School </label>
							</div>
							<!-- Qualification -->
							<div class="form-floating mb-3 normal">
								<input type="text" class="form-control" id="qualification"
									placeholder="Qualification" name="qualification" /> <label
									for="floatingInput"> Qualification </label>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Save changes</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- Insert Education -->

	<!-- Insert Experiences -->
    	<div class="modal fade" id="editExperience" tabindex="-1"
    		aria-labelledby="editLabel" aria-hidden="true">
    		<div class="modal-dialog">
    			<div class="modal-content">
    				<div class="modal-header sticky-top bg-light">
    					<h5 class="modal-title" id="exampleModalLabel">Insert Experiences</h5>
    					<button type="button" class="btn-close" data-bs-dismiss="modal"
    						aria-label="Close"></button>
    				</div>
    				<div class="modal-body">
    					<form method="post" id="expForm" action="insertExp" onsubmit="return expValidation();" >
    						<div class="container">
    							<!-- company -->
    							<div class="form-floating mb-3 normal">
    								<input type="text" class="form-control" id="company"
    									placeholder="company Name" name="company"/> <label
    									for="company"> Company Name </label>
    							</div>

    							<!-- position -->
    							<div class="form-floating mb-3 normal">
    								<input type="text" class="form-control" id="position"
    									placeholder="position" name="position" /> <label
    									for="position"> Position </label>
    							</div>

                                <!-- start_data -->
    							<div class="form-floating mb-3 normal">
    								<input type="date" class="form-control" id="start_data"
    									placeholder="start_data" name="start_data" /> <label
    									for="start_data"> Start date </label>
    							</div>

                                <!-- end_date -->
    							<div class="form-floating mb-3 normal">
    								<input type="date" class="form-control" id="end_date"
    									placeholder="end_date" name="end_date" /> <label
    									for="end_date"> End date </label>
    							</div>

                                <!-- country -->
    							<div class="form-floating mb-3 normal">
    								<input type="text" class="form-control" id="excountry"
    									placeholder="country" name="country" /> <label
    									for="country"> Country </label>
    							</div>
    						</div>
    						<div class="modal-footer">
    							<button type="button" class="btn btn-secondary"
    								data-bs-dismiss="modal">Close</button>
    							<button type="submit" class="btn btn-primary">Save changes</button>
    						</div>
    					</form>
    				</div>
    			</div>
    		</div>
    	</div>
    	<!-- Insert Experiences -->

    	<!-- Edit Experiences -->
    	<core:forEach items = "${exp}" var="experience">
            	<div class="modal fade" id="editExperience${experience.experience_id}" tabindex="-1"
            		aria-labelledby="editLabel" aria-hidden="true">
            		<div class="modal-dialog">
            			<div class="modal-content">
            				<div class="modal-header sticky-top bg-light">
            					<h5 class="modal-title" id="exampleModalLabel">Insert Experiences</h5>
            					<button type="button" class="btn-close" data-bs-dismiss="modal"
            						aria-label="Close"></button>
            				</div>
            				<div class="modal-body">
            					<form method="post" id="expForm${experience.experience_id}" action="editExp/${experience.experience_id}" >
            						<div class="container">
            							<!-- company -->
            							<div class="form-floating mb-3 normal">
            								<input type="text" class="form-control" id="company${experience.experience_id}"
            									placeholder="company Name" name="company" value="${experience.company}"/> <label
            									for="company"> Company Name </label>
            							</div>

            							<!-- position -->
            							<div class="form-floating mb-3 normal">
            								<input type="text" class="form-control" id="position${experience.experience_id}"
            									placeholder="position" name="position" value="${experience.position}"/> <label
            									for="position"> Position </label>
            							</div>

                                        <!-- start_data -->
            							<div class="form-floating mb-3 normal">
            								<input type="date" class="form-control" id="start_data${experience.experience_id}"
            									placeholder="start_data" name="start_data" value="${experience.start_data}"/> <label
            									for="start_data"> Start date </label>
            							</div>

                                        <!-- end_date -->
            							<div class="form-floating mb-3 normal">
            								<input type="date" class="form-control" id="end_date${experience.experience_id}"
            									placeholder="end_date" name="end_date" value="${experience.end_date}"/> <label
            									for="end_date"> End date </label>
            							</div>

                                        <!-- country -->
            							<div class="form-floating mb-3 normal">
            								<input type="text" class="form-control" id="country${experience.experience_id}"
            									placeholder="country" name="country" value="${experience.country}"/> <label
            									for="country"> Country </label>
            							</div>
            						</div>
            						<div class="modal-footer">
            							<button type="button" class="btn btn-secondary"
            								data-bs-dismiss="modal">Close</button>
            							<button type="submit" class="btn btn-primary">Save changes</button>
            						</div>
            					</form>
            				</div>
            			</div>
            		</div>
            	</div>
            </core:forEach>
            <!-- Edit Experiences -->

</body>
</html>