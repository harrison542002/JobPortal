package com.jobs.model;

import java.io.Serializable;

public class Messages implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer m_id;
	private String message_time;
	private String message_content;
	private String profileImage;
	private String firstName;
	private String lastName;
	
	public Messages(Integer m_id, 
			String message_time, 
			String message_content, 
			String profileImage, 
			String firstName,
			String lastName) {
		this.m_id = m_id;
		this.message_time = message_time;
		this.message_content = message_content;
		this.profileImage = profileImage;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Integer getM_id() {
		return m_id;
	}
	public void setM_id(Integer m_id) {
		this.m_id = m_id;
	}
	public String getMessage_time() {
		return message_time;
	}
	public void setMessage_time(String message_time) {
		this.message_time = message_time;
	}
	public String getMessage_content() {
		return message_content;
	}
	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
	
}
