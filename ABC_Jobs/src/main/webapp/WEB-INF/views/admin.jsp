<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administration</title>
    <link rel="stylesheet"
    	href="<core:url value="/resources/css/admin.css" />">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/dfffc10f23.js" crossorigin="anonymous"></script>
</head>
<body>
    <div class="row" style="height: 100vh;">
    <%@ include file="/WEB-INF/views/adminNav.jsp"%>
        <div class="col-md-11">
            <h2 class="title text-center mb-5 mt-5">
                Send Bulk Email <i class="fa-sharp fa-solid fa-envelopes-bulk"></i>
            </h2>
            <div class="container">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <form class="form" action="sendEmail" method="post">
                            <div class="mb-3">
                            <div class="normal">
                                <label for="toEmail" class="col-form-label">To</label>
                                <input type="email" class="form-control" id="toEmail" name="toEmail"
                               	placeholder="user@email.com">
                            </div>
                              </div>
                              <div class="mb-3">
                              <div class="normal">
                                <label for="subject" class="col-form-label">Subject</label>
                                  <input type="text" class="form-control" id="subject" name="subject"
                                  placeholder="Email Title">
                               </div>
                              </div>
                              <div class="mb-3">
                              <div class="normal">
                                <label for="Message" class="col-sm-2 col-form-label">Message</label>
                                  <textarea type="text" class="form-control mb-5" id="Message" name="message"
                                  placeholder="Type in Bulk message ... ">
                                </textarea>
                                <button type="submit" class="btn btn-primary">Send</button>
                              </div>
                              <div>
                              </div>
                              </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <core:set var="successState" scope="session" value="${success}"/>
            <core:if test="${successState == 'true'}">
        	<div id="successMsg" class="modal" tabindex="-1">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title">Success Message</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <i class="fa-solid fa-message-check fa-2xl text-success"></i>
                    <p>Message has been sent successfully</p>
                  </div>
                </div>
              </div>
            </div>
            </core:if>

      <script type="text/javascript"
      		src="<core:url value="/resources/javaScript/admin.js"/>"></script>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js" integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous"></script>


</body>
</html>