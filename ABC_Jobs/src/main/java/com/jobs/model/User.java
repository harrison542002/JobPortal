package com.jobs.model;

import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer user_id;

	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;

	@OneToOne(fetch = FetchType.EAGER,mappedBy="user")
	private UserProfile userProfile;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.MERGE, mappedBy="user")
	private List<Message> message;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.MERGE,mappedBy = "user")
	private List<MessageBoard> messageBoard;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.MERGE,mappedBy = "user")
	private List<MessageBoardParticipant> messageBoardParticipants;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "user")
	private List<SaveJob> saveJobs;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade =CascadeType.MERGE, mappedBy = "user")
	private List<Apply> applies;
	
	
	public List<Apply> getApplies() {
		return applies;
	}

	public void setApplies(List<Apply> applies) {
		this.applies = applies;
	}

	public List<MessageBoardParticipant> getMessageBoardParticipants() {
		return messageBoardParticipants;
	}

	public void setMessageBoardParticipants(List<MessageBoardParticipant> messageBoardParticipants) {
		this.messageBoardParticipants = messageBoardParticipants;
	}

	public List<SaveJob> getSaveJobs() {
		return saveJobs;
	}

	public void setSaveJobs(List<SaveJob> saveJobs) {
		this.saveJobs = saveJobs;
	}

	public List<Message> getMessage() {
		return message;
	}

	public void setMessage(List<Message> message) {
		this.message = message;
	}

	public List<MessageBoard> getMessageBoard() {
		return messageBoard;
	}

	public void setMessageBoard(List<MessageBoard> messageBoard) {
		this.messageBoard = messageBoard;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
