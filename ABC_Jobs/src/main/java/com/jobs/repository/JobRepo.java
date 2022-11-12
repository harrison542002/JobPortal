package com.jobs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobs.model.Job;

public interface JobRepo extends JpaRepository<Job, Integer> {

	@Query("SELECT job FROM Job job WHERE job.job_id != :jid")
	public Page<Job> getOtherJobs(@Param("jid") Integer jid, Pageable pageable);
	
}
