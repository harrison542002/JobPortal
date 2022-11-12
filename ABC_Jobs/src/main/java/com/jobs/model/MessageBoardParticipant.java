package com.jobs.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "message_board_participants")
public class MessageBoardParticipant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mp_id")
	private Integer mp_id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name= "participant_id", referencedColumnName = "user_id")
	private User user;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name= "message_bid", referencedColumnName = "message_bid")
	private MessageBoard messageBoard;
	
	public Integer getMp_id() {
		return mp_id;
	}

	public void setMp_id(Integer mp_id) {
		this.mp_id = mp_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MessageBoard getMessageBoard() {
		return messageBoard;
	}

	public void setMessageBoard(MessageBoard messageBoard) {
		this.messageBoard = messageBoard;
	}
	
	
	
}
