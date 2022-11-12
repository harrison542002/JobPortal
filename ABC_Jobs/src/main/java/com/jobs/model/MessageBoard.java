package com.jobs.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "message_board")
public class MessageBoard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_bid")
	private Integer message_bid;
	
	@Basic(optional = false)
	@Column(name = "created_time", insertable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	private Date created_time;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "created_personID" , referencedColumnName = "user_id")
	private User user;
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "messageBoard")
	private List<Message> message;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy = "messageBoard", cascade = CascadeType.ALL)
	private MessageBoardParticipant messageBoardParticipant;

	public MessageBoardParticipant getMessageBoardParticipant() {
		return messageBoardParticipant;
	}

	public void setMessageBoardParticipant(MessageBoardParticipant messageBoardParticipant) {
		this.messageBoardParticipant = messageBoardParticipant;
	}

	public Integer getMessage_bid() {
		return message_bid;
	}

	public void setMessage_bid(Integer message_bid) {
		this.message_bid = message_bid;
	}

	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Message> getMessage() {
		return message;
	}

	public void setMessage(List<Message> message) {
		this.message = message;
	}

	
	
}
