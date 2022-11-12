<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<script src="<core:url value="/resources/javaScript/sockjs-0.3.4.js"/>"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js" 
integrity="sha512-tL4PIUsPy+Rks1go4kQG8M8/ItpRMvKnbBjQm4d2DQnFwgcBYRRN00QdyQnWSCwNMsoY/MfJY8nHp2CzlNdtZA==" 
crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript">
	var stompClient = null;
	function setConnected(connected){
		document.getElementById('connect').disabled = connected;
		document.getElementById('disconnect').disabled = !connected;
		document.getElementById('conversationDiv').style.visibility 
		= connected ? 'visible' : 'hidden';
		document.getElementById('response').innerHTML = '';
	}
	
	function connect(){
		var socket = new SockJS("<core:url value="/msgtest"/>");
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame){
			setConnected(true);
			console.log('Connected : ' + frame)
			stompClient.subscribe('/chatting/messages', function(messagingOutput){
				showMessageOutput(JSON.parse(messagingOutput.body));
			})
		})
	}
	
	function disconnect(){
		if(stompClient != null){
			stompClient.disconnect();
		}
		setConnected(false);
		console.log("Disconnected");
	}
	
	function sendMessage(){
		var from = document.getElementById('from').value;
		var text = document.getElementById('text').value;
		stompClient.send("/app/snapChat", {},
				JSON.stringify({'from' : from , 'text' : text}));
	}
	
	function showMessageOutput(messageOutput){
		var response = document.getElementById('response');
		var p = document.createElement('p');
		p.style.wordWrap = 'break-word';
		p.appendChild(document.createTextNode(messageOutput['from'] + ": " +
				messageOutput.text));
		response.appendChild(p);
	}
</script>
</head>
<body onload="disconnect();">

	<div>
		<div>
			<input type="text" id="from" placeholder="Choose a nickname"/>
		</div>
		<br/>
		<div>
			<button id="connect" onclick="connect();">Connect</button>
			<button id="disconnect" onclick="disconnect();">Disconnect</button>
		</div>
		<br/>
		<div id="conversationDiv">
			<input type="text" id="text" placeholder="Write a message ..."/>
			<button id="sendMessage" onclick="sendMessage();">Send</button>
			<p id="response"></p>
		</div>
	</div>
</body>
</html>