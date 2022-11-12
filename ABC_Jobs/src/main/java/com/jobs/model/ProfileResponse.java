package com.jobs.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileResponse {

	@JsonProperty(value = "profiles", required = true)
	List<UserMsg> userMsgs;

	public ProfileResponse(List<UserMsg> userMsgs) {
		super();
		this.userMsgs = userMsgs;
	}

	public List<UserMsg> getUserMsgs() {
		return userMsgs;
	}

	public void setUserMsgs(List<UserMsg> userMsgs) {
		this.userMsgs = userMsgs;
	}
	
	
	
}
