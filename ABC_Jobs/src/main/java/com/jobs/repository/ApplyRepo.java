
package com.jobs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobs.model.Apply;

public interface ApplyRepo extends JpaRepository<Apply, Integer>{

	@Query("SELECT apply FROM Apply apply WHERE apply.job.job_id = :jid AND apply.user.user_id= :uid")
	public List<Apply> findJobs(
			@Param("jid") Integer jid,
			@Param("uid") Integer uid);
	
}
