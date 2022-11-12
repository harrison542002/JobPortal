package com.jobs.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jobs.model.Message;
import com.jobs.model.MessageBoard;
import com.jobs.model.MessageResponse;
import com.jobs.model.Messages;
import com.jobs.model.ProfileResponse;
import com.jobs.model.User;
import com.jobs.model.UserMessage;
import com.jobs.model.UserMsg;
import com.jobs.model.UserProfile;
import com.jobs.services.ChatService;
import com.jobs.services.ProfileService;
import com.jobs.services.RegisterService;

@Controller
public class Messaging {

	@Autowired
	private ChatService chatService;

	@Autowired
	private RegisterService registerService;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	private ProfileService profileService;

	@PostMapping("/messaging")
	@ResponseBody
	public String startConversation(
			@RequestParam("uid") Integer user_id, 
			@RequestParam("cid") Integer participanted_id) {

		if (chatService.checkExistedMessageBoard(user_id, participanted_id).isEmpty()) {
			/*
			 * If there is no create message board Create new room
			 */
			User user = registerService.getUser(user_id).get();
			User participant = registerService.getUser(participanted_id).get();
			if (user != null && participant != null) {
				MessageBoard messageBoard = chatService.createMessageBoard(user, participant);
				return Integer.toString(messageBoard.getMessage_bid());
			}
		}
		return "fail";
	}

	@RequestMapping(value = "/requestMessages", method = RequestMethod.GET)
	@ResponseBody
	public MessageResponse requestMessages(Model model, 
			@Param("m_id") Integer m_id, 
			HttpServletRequest request) {

		List<Messages> messages = chatService.retrieveMessages(m_id);

		return new MessageResponse(messages);

	}

	@MessageMapping("/snapChat")
	public void send(@Payload UserMessage message) throws InterruptedException {

		Integer userId = Integer.valueOf(message.getUserId());
		Integer mbId = Integer.valueOf(message.getMessageboardId());
		User user = registerService.getUser(userId).get();

		System.out.println(message.getMessage());
		System.out.println(message.getChatId() + "*********");

		MessageBoard messageBoard = chatService.getMessageBoard(mbId).get();
		Message messages = new Message();
		messages.setMessage_content(message.getMessage());
		messages.setMessageBoard(messageBoard);
		messages.setUser(user);
		messages = chatService.saveMessage(messages);
		String imageString = Base64Utils.encodeToString(messages
				.getUser()
				.getUserProfile()
				.getProfileImage());
		String url = "/topic/" + message.getChatId();
		System.out.println("Hiii");
		simpMessagingTemplate.convertAndSend(url,
				new Messages(messages.getMessage_id(), "", messages.getMessage_content(), imageString,
						messages.getUser().getUserProfile().getFirstName(),
						messages.getUser().getUserProfile().getLastName()));

	}

	@GetMapping("/messageBoards")
	public String displayMessageBoard(Model model, HttpServletRequest request) {

		HashMap<String, String> userData = retrieveUserID(request);
		if (userData.isEmpty()) {
			return "login";
		}
		Integer user_id = Integer.valueOf(userData.get("id"));
		String email = userData.get("email");
		String password = userData.get("password");

		if (user_id == 0) {
			return "registration";
		}

		List<MessageBoard> messageBoards = chatService.retrieveMessageBoards(user_id);
		if (!messageBoards.isEmpty()) {
			model.addAttribute("email", email);
			model.addAttribute("password", password);
			model.addAttribute("id", user_id);
			model.addAttribute("messageBoards", messageBoards);
			return "messageBoard";
		}
		return "messageBoard";

	}
	
	
	@RequestMapping(value = "msgUser", method = RequestMethod.POST)
	@ResponseBody
	public ProfileResponse searchUserForMsg(
			@RequestParam("username") String username,
			HttpServletRequest request) {
		
		HashMap<String, String> userData = retrieveUserID(request);
		
		Integer user_id = Integer.valueOf(userData.get("id"));
		
		List<UserProfile> userProfiles = profileService.searchUserFromMessaging(username);
		List<UserMsg> userMsgs = chatService.convertProfile(userProfiles, user_id);
		return new ProfileResponse(userMsgs);
	}

	private HashMap<String, String> retrieveUserID(HttpServletRequest request) {

		HashMap<String, String> userData = new HashMap<>();

		if (request.getCookies() == null) {
			return userData;
		}

		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equalsIgnoreCase("id")) {
				userData.put("id", cookie.getValue());
			}
			if (cookie.getName().equalsIgnoreCase("email")) {
				userData.put("email", cookie.getValue());
			}
			if (cookie.getName().equalsIgnoreCase("password")) {
				userData.put("password", cookie.getValue());
			}
		}
		return userData;
	}

}
