package com.jobs.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobs.model.MessageBoardParticipant;

public interface MessageBoardParticipantRepo extends JpaRepository<MessageBoardParticipant, Integer>{

	@Modifying
	@Transactional
	@Query("DELETE FROM MessageBoardParticipant msgbp WHERE msgbp.messageBoard.message_bid=:m_id")
	public Integer delecteMSB(@Param("m_id") Integer m_id);
	
}
