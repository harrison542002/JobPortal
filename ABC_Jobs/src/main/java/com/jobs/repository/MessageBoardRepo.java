package com.jobs.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobs.model.MessageBoard;

public interface MessageBoardRepo extends JpaRepository<MessageBoard, Integer>{

	@Query("SELECT msgb FROM MessageBoard msgb JOIN msgb.messageBoardParticipant msgp "
			+ "WHERE msgb.user.user_id =:c_id AND msgp.user.user_id =:p_id AND "
			+ "msgb.message_bid = msgp.messageBoard.message_bid")
	public List<MessageBoard> searchExistedMB(
			@Param("c_id") Integer c_id, 
			@Param("p_id") Integer p_id
			);

	@Query("SELECT msgb FROM MessageBoard msgb WHERE msgb.user.user_id =:u_id OR msgb.messageBoardParticipant.user.user_id =:u_id")
	public List<MessageBoard> retrieveMessageBoard(
			@Param("u_id") Integer user_id);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM MessageBoard msgb WHERE msgb.user.user_id = :u_id")
	public Integer delectMessageBoard(@Param("u_id") Integer user_id);
}
