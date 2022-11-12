package com.jobs.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageResponse {
	
	@JsonProperty(value = "messages" , required = true)
	List<Messages> messageList;

	public MessageResponse(List<Messages> messageList) {
		super();
		this.messageList = messageList;
	}

	public List<Messages> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<Messages> messageList) {
		this.messageList = messageList;
	}
	
	

	
	
}
