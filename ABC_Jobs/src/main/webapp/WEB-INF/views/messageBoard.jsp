<%@ page import="org.springframework.util.Base64Utils" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
	isELIgnored="false"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/convert.tld" prefix="convert"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Messaging | ABC Jobs</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<core:url value="/resources/css/message.css"/>">
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.6.1/dist/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js" 
integrity="sha512-tL4PIUsPy+Rks1go4kQG8M8/ItpRMvKnbBjQm4d2DQnFwgcBYRRN00QdyQnWSCwNMsoY/MfJY8nHp2CzlNdtZA==" 
crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="container-fluid mt-5">
		<div class="row pt-5">
			<div class="col-md-3 messageboard">
				
				<div class="container-fluid bg-light mb-3 border rounded">
					<div class="pt-2 pb-2 d-flex justify-content-between">
						<h4>Messaging</h4>
						<div class="mt-1">
							<i class="fa-solid fa-square-plus fa-2xl text-primary add" data-uid="${id}"
							data-size="${messageBoards.size()}"></i>
						</div>						
					</div>
				</div>
				<div class="fixed mwrapper border shadow-sm bg-light">
					<core:if test="${not empty messageBoards}">
							<core:forEach items="${messageBoards}" var="messageboard"
								varStatus="status">
								<core:if test="${ messageboard.message.size() != 0}">
								<core:if test="${ id eq messageboard.user.user_id}">
									<div class="container-fluid d-flex messageBoard mb-${status.index}"
										data-mid="${messageboard.message_bid}"
										data-cid="${messageboard.messageBoardParticipant.user.user_id}"
										data-uid="${id}"
										data-msg="${status.index}">
										<img class="pimg mt-1 mb-1"
											src="data:image/jpg;base64,
											${convert:getString(messageboard.messageBoardParticipant.user.userProfile.profileImage)}"
											alt="image">
										<div class="mt-1 ms-3">
											<h5>
												${messageboard.messageBoardParticipant.user.userProfile.firstName}
												${messageboard.messageBoardParticipant.user.userProfile.lastName}
											</h5>
											<p class="messagdb-msg msg-${status.index} text-secondary">
												${messageboard.message.get(messageboard.message.size() - 1).user.userProfile.firstName} 
												${messageboard.message.get(messageboard.message.size() - 1).user.userProfile.lastName} 
												:
												${messageboard.message.get(messageboard.message.size() - 1).message_content}
											</p>
										</div>
									</div>
									<hr>
								</core:if>
								</core:if>

								<core:if test="${ messageboard.message.size() != 0}">
								<core:if test="${id != messageboard.user.user_id}">
									<div class="container-fluid d-flex messageBoard mb-${status.index}"
										data-mid="${messageboard.message_bid}"
										data-cid="${messageboard.user.user_id}" 
										data-uid="${id}"
										data-msg="${status.index}">
										<img class="pimg mt-1 mb-1"
											src="data:image/jpg;base64,${convert:getString(messageboard.user.userProfile.profileImage)}"
											alt="image">
										<div class="mt-1 ms-3">
											<h5>${messageboard.user.userProfile.firstName}
												${messageboard.user.userProfile.lastName}</h5>
											<p class="messagdb-msg msg-${status.index} text-secondary">
												${messageboard.message.get(messageboard.message.size() - 1).user.userProfile.firstName} 
												${messageboard.message.get(messageboard.message.size() - 1).user.userProfile.lastName} 
												:
												${messageboard.message.get(messageboard.message.size() - 1).message_content}
											</p>
										</div>
									</div>
									<hr>
								</core:if>
								</core:if>

							</core:forEach>
					</core:if>
				</div>
			</div>
			<div class="col-md-9 messageChat mb-3">
				<div class="room border shadow-sm bg-light">
					<div class="container chat mt-2" id="chat">
						<i class="fa-solid fa-spinner fa-spin text-primary icon"></i>
					</div>
					<div class="message-box">
						<div class="box d-flex mb-3">
							<input type="text" class="message" name="message" id="message"
								placeholder="Write a message">
							<button class="btn send" data-uid="${id}">Send</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Footer-->
	<%@ include file="/WEB-INF/views/footer.jsp"%>
	<script src="https://code.jquery.com/jquery-3.6.1.min.js"
		integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
		crossorigin="anonymous"></script>
	<script src="<core:url value="/resources/javaScript/messaging.js"/>"></script>
</body>
</html>