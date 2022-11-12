package com.jobs.model;

public class UserMessage {
	
	private String message;
	private String userId;
	private String messageboardId;
	private String chatId;
	
	public String getChatId() {
		return chatId;
	}
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMessageboardId() {
		return messageboardId;
	}
	public void setMessageboardId(String messageboardId) {
		this.messageboardId = messageboardId;
	}
	
	
	
}
