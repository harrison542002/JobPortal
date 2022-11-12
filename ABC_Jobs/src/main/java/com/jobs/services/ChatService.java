package com.jobs.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.jobs.model.Message;
import com.jobs.model.MessageBoard;
import com.jobs.model.MessageBoardParticipant;
import com.jobs.model.Messages;
import com.jobs.model.User;
import com.jobs.model.UserMsg;
import com.jobs.model.UserProfile;
import com.jobs.repository.MessageBoardParticipantRepo;
import com.jobs.repository.MessageBoardRepo;
import com.jobs.repository.MessageRepo;

@Service
@Transactional
public class ChatService {

	@Autowired
	MessageBoardRepo messageBoardRepo;

	@Autowired
	MessageRepo messageRepo;

	@Autowired
	MessageBoardParticipantRepo messageBoardParticipantRepo;

	public List<Messages> retrieveMessages(Integer messageboard_id) {

		/*
		 * Retrieve Messages for particular message board
		 */

		List<Message> messages = messageRepo.retrieveMessages(messageboard_id);

		if (messages != null) {
			return extractMessages(messages);
		}
		return null;

	}

	public List<MessageBoard> checkExistedMessageBoard(Integer createPerson_id, Integer participant_id) {
		/*
		 * Check whether there is already a message board chat between two user
		 */
		return messageBoardRepo.searchExistedMB(createPerson_id, participant_id);
	}

	public MessageBoard createMessageBoard(User user, User participant) {

		MessageBoard messageBoard = new MessageBoard();
		messageBoard.setUser(user);
		messageBoard = messageBoardRepo.save(messageBoard);

		MessageBoardParticipant meBoardParticipant = new MessageBoardParticipant();
		meBoardParticipant.setUser(participant);
		meBoardParticipant.setMessageBoard(messageBoard);
		messageBoardParticipantRepo.save(meBoardParticipant);
		return messageBoard;
	}
	
	public Optional<MessageBoard> getMessageBoard(Integer mb_id) {
		
		return messageBoardRepo.findById(mb_id);
		
	}
	
	public Message saveMessage(Message message) {
		return messageRepo.save(message);
	}

	public List<MessageBoard> retrieveMessageBoards(Integer user_id) {

		return messageBoardRepo.retrieveMessageBoard(user_id);

	}

	private List<Messages> extractMessages(List<Message> messages) {

		List<Messages> extractMessages = new ArrayList<>();

		if (messages == null || messages.isEmpty()) {
			return extractMessages;
		}

		if (messages != null) {
			messages.forEach(message -> {
				extractMessages.add(convertMessage(message));
			});
		}
		return extractMessages;

	}
	
	public List<UserMsg> convertProfile(List<UserProfile> userProfiles, Integer uid){
		
		List<UserMsg> userMsgs = new ArrayList<>();
		
		userProfiles.forEach(userprofile ->{
			userMsgs.add(createdConvertedProfile(userprofile, uid));
		});
		
		return userMsgs;
	}
	
	
	private UserMsg createdConvertedProfile(UserProfile userProfile, Integer uid) {
		
		if(userProfile == null) {
			return null;
		}
		
		return new UserMsg(userProfile.getFirstName(), userProfile.getLastName(),userProfile.getLocation().getCountry(),
				userProfile.getLocation().getCity(), Base64Utils.encodeToString(userProfile.getProfileImage()),
				uid, userProfile.getUser().getUser_id());
		
	}
	
	public Messages convertMessage(Message message) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy MMMM dd_h:mm:ss aa");
		
		if(message == null) {
			return null;
		}
		
		return new Messages(message.getMessage_id(), 
				dateFormat.format(message.getMessage_time()), message.getMessage_content(),
				Base64Utils.encodeToString(message.getUser().getUserProfile().getProfileImage()), 
				message.getUser().getUserProfile().getFirstName(),
				message.getUser().getUserProfile().getLastName());
		
	}
}
