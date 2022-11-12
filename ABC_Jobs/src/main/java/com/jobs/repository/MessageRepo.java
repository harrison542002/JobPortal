package com.jobs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobs.model.Message;

public interface MessageRepo extends JpaRepository<Message, Integer>{

	@Query("SELECT message FROM Message message JOIN message.messageBoard message_board WHERE "
			+ "message.messageBoard.message_bid =:mb_id AND "
			+ "message.messageBoard.message_bid = message_board.message_bid")
	public List<Message> retrieveMessages(@Param("mb_id") Integer mb_id);
}
