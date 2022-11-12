package com.jobs.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobs.model.Job;
import com.jobs.model.SaveJob;

public interface SavedJobRepository extends JpaRepository<SaveJob, Integer>{
	
	@Query("SELECT savejob FROM SaveJob savejob WHERE savejob.job.job_id = :jid AND savejob.user.user_id= :uid")
	public List<SaveJob> findJobs(
			@Param("jid") Integer jid,
			@Param("uid") Integer uid);
	
	@Query("SELECT savejob.job FROM SaveJob savejob WHERE savejob.user.user_id= :uid")
	public List<Job> myJobs(
			@Param("uid") Integer uid);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM SaveJob savejob WHERE savejob.job.job_id=:jid AND savejob.user.user_id=:uid")
	public void deleteSavedJob(
			@Param("jid") Integer jid,
			@Param("uid") Integer uid);
}
