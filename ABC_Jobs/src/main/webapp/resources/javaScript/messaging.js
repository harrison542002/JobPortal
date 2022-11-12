let stompClient = null;
const chatDiv = document.getElementById('chat');
const date = new Date();
let size = "";
chatDiv.scrollTop = $('#chat')[0].scrollHeight;
const msgdbLists = document.querySelectorAll(".messageBoard");

$(document).ready(function() {
	size = $(".add").data("size");
	$("div.messageBoard").on("click", function(event) {
		onClick(event.currentTarget.dataset.mid, event.currentTarget.dataset.cid, event.currentTarget.dataset.uid,
			event.currentTarget.dataset.msg);
	});

	$("button.send").on("keypress", function(event) {
		if (event.keyCode == 13) {
			sendMessage(event);
		}
	})

	$("button.send").on("click", function(event) {
		sendMessage(event);
	})

	$(".add").on("click", function(event) {
		disconnect();
		let id = $(".add").data("uid");
		$("button.send").hide();
		$("div.box").append(`<button class="btn btn-primary start-chat" data-uid="${id}" data-size="${size}">Start Chatting</button>`)
		let searchBox = `<input type="text" class="messageBox w-100" placeholder="Type Username to Message ...">
    	<div class="searchResult">
    	</div>`;
		$("div.chat").html(searchBox);
		
		$(".start-chat").on("click", function(event) {
							let cid = event.currentTarget.dataset.cid;
							let uid = event.currentTarget.dataset.uid;
							let imageString = $(".profile").attr("src");
							let username = $(".username").text();
							let url = "/ABC_Jobs/messaging";
							console.log(cid);
							console.log(uid);
							$.post(url, {
								"uid" : uid,
								"cid" : cid
							}, function(response){
								console.log(response)
								let chatId = parseInt(uid) > parseInt(cid) ? uid + "" + cid : cid + "" + uid;
								let mid = response;
								let messageBoard = `<div class="container-fluid d-flex messageBoard mb-${size}"
										data-mid="${mid}"
										data-cid="${cid}"
										data-uid="${uid}"
										data-msg="${size}">
										<img class="pimg mt-1 mb-1"
											src="${imageString}"
											alt="image">
										<div class="mt-1 ms-3">
											<h5>
												${username}
											</h5>
											<p class="messagdb-msg msg-${size} text-secondary">
											</p>
										</div>
									</div>
									<hr>`;
								$(".mwrapper").append(messageBoard);
								$("button.start-chat").attr("data-mid", mid);
								$(".start-chat").hide();
								$("button.send").attr("data-mid", mid);
								$("button.send").attr("data-cid", cid);
								$("button.send").show();
								$(".start-chat").remove();
								connect(chatId, size);
								setTimeout(sendMessage, 500, event);
							})
						})

		$("input.messageBox").on("keyup", function(event) {

			let value = event.target.value;

			if (value.length == 0) {
				$(".searchResult").html("")
			}

			if (value.length > 0) {
				let url = "/ABC_Jobs/msgUser";
				$.post(url,
					{
						"username": value
					}, function(response) {
						let searchValues = "";
						let profiles = response;
						
						//for each profile object
						profiles["profiles"].forEach(element => {
							searchValues += 
								`<div class="container d-flex border rounded w-100 results" data-uid="${element["uid"]}" data-cid="${element["cid"]}">` +
								`<img class="img" src="data:image/jpg;base64,${element["image"]}"/>` +
								`<div class="container ms-3 p-2">` +
								`<h5>${element["firstName"]} ${element["lastName"]}</h5>` +
								`<p class="text-secondary"><i class="fa-solid fa-map-pin text-danger"></i>${element["city"]}, ${element["country"]}</p>` +
								`</div></div>`;
						});

						$(".searchResult").html(searchValues);

						/*
						set click event listener for each search results
						*/
						
						let results = document.querySelectorAll('.results');
						
						results.forEach(element => {
							
							element.addEventListener("click", function(event) {
								let cid = element.getAttribute("data-cid");
								
								msgdbLists.forEach(element => {
									let gid = element.getAttribute("data-cid");
									console.log(gid);
									if(cid == gid){
										element.click();
										return;
									}
								})
								
								
								console.log(element.getAttribute("data-uid"));
								let name = element.children[1].children[0].textContent;
								let imageString = element.firstChild.getAttribute("src");
								let location = element.children[1].lastChild.textContent;
								let profile = `<div class="container">
        				<img class="profile" src="${imageString}">
        				<div class="container p-2">
            				<h5 class="username">${name}</h5>
            				<p class="text-secondary">
            				<i class="fa-solid fa-map-pin text-danger"></i>
            					${location}
            				</p>
       				 	</div>
    					</div>`
								$(".chat").html(profile);
								$("button.start-chat").attr("data-cid", cid);
							})
						})
					})
			}
		})
	})

	let mid = $("div.mb-0").data("mid");
	let cid = $("div.mb-0").data("cid");
	let uid = $("div.mb-0").data("uid");
	let msgid = $("div.mb-0").data("msg");
	$("div.mb-0").css("background-color", "#edf7fa")
	onClick(mid, cid, uid, msgid);

})

$(document).keypress(function(e) {
	if (e.keyCode == 13) {
		$("button.send").click();
	}
})

function onClick(mid, cid, uid, msgid) {

	$("button.send").show();
	$("button.start-chat").remove();
	$("div.chat").html(`<i class="fa-solid fa-spinner fa-spin text-primary icon"></i>`);
	$("div.mwrapper").children().css({
		"background-color": "transparent"
	})
	$(`div.messageBoard[data-mid='${mid}']`).css({
		"background-color": "#edf7fa"
	})

	let chatId = parseInt(uid) > parseInt(cid) ? uid + "" + cid : cid + "" + uid;
	console.log(chatId);
	const jsonURL = "/ABC_Jobs/requestMessages?m_id=" +
		mid

	let messages = "";
	$.getJSON(
		jsonURL, function(json) {
			let date = "";
			$.each(json.messages, function() {
				if (date != (this.message_time).split("_")[0]) {

					date = (this.message_time).split("_")[0];
					messages += `<div class='date p-2'>
                	<p class="date">
                		 ${date}
                	</p>
                </div>`
				}

				messages += `<div class='container messages d-flex'>
                <img class='img mt-1 mb-1 me-2' src="data:image/jpg;base64,${this.profileImage}" alt='image'>
                <div class='content p-2'>
                    <p class='username'>${this.firstName} ${this.lastName}</p>
                    <p class='msg'>${this.message_content}</p>
                </div>
                <div class='time p-2'>
                	<p>
                		 ${(this.message_time).split("_")[1]}
                	</p>
                </div>
            </div>`
			})
			$("div.chat").html(messages);
			$('div.chat').scrollTop($('div.chat')[0].scrollHeight);
			$("button.send").attr("data-mid", mid);
			$("button.send").attr("data-cid", cid);
		})

	chatDiv.scrollTop = $('#chat')[0].scrollHeight;
	disconnect();
	connect(chatId, msgid);
}

function disconnect() {
	if (stompClient != null) {
		stompClient.disconnect();
	}
	console.log("Disconnected");
}

function connect(chatId, msgid) {

	var socket = new SockJS("http://localhost:8085/ABC_Jobs/chat");
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {

		console.log("Connected on : " + frame)
		onConnected(chatId, msgid)
	},
		function() {
			console.log("Connection Error");
		});
}

function onConnected(chatId, msgid) {

	console.log("connected");
	stompClient.subscribe(
		"/topic/" + chatId,
		function(messagingOutput) {
			console.log("Message Received")
			onReceieved(JSON.parse(messagingOutput.body), msgid);
		}
	)
}

function onReceieved(message, msgid) {

	$("div.chat").append(
		`<div class='container messages d-flex'>
                <img class='img mt-1 mb-1 me-2' src="data:image/jpg;base64,${message.profileImage}" alt='image'>
                <div class='content p-2'>
                    <p class='username'>${message.firstName} ${message.lastName}</p>
                    <p class='msg'>${message.message_content}</p>
                </div>
                <div class='time p-2'>
                	<p>
                		 ${date.toLocaleTimeString()}
                	</p>
                </div>
            	</div>`);
	chatDiv.scrollTop = $('#chat')[0].scrollHeight;
	console.log(msgid);
	if(msgid != ""){
		$(`.msg-${msgid}`).text(
		`${message.firstName} ${message.lastName}: ${message.message_content}`);
	}
	
}

function sendMessage(event) {
	let mid = event.currentTarget.dataset.mid;
	let cid = parseInt(event.currentTarget.dataset.cid);
	let uid = parseInt(event.currentTarget.dataset.uid);
	let message = document.getElementById("message").value;
	let chatId = uid > cid ? uid + "" + cid : cid + "" + uid;
	if (message.trim() != "") {
		stompClient.send("/app/snapChat", {},
			JSON.stringify({
				'message': message,
				'chatId': chatId,
				'userId': uid,
				'messageboardId': mid
			}));
	}
	$('#message').val("");
}