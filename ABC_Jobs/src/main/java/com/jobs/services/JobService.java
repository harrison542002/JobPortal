package com.jobs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jobs.model.Apply;
import com.jobs.model.Job;
import com.jobs.model.SaveJob;
import com.jobs.model.User;
import com.jobs.repository.ApplyRepo;
import com.jobs.repository.JobRepo;
import com.jobs.repository.RegisterRepository;
import com.jobs.repository.SavedJobRepository;

@Service
public class JobService {
	
	@Autowired 
	JobRepo jobRepo;
	
	@Autowired
	SavedJobRepository savedJobRepository;
	
	@Autowired
	RegisterRepository registerRepository;
	
	@Autowired
	ApplyRepo applyRepo;
	
	public Page<Job> getJobs(Pageable pageable){
		
		return jobRepo.findAll(pageable);
		
	}
	
	public Integer getTotaljobs() {
		return (int) jobRepo.count();
	}
	
	public Job getJob(Integer job_id) {
		return jobRepo.findById(job_id).get();
	}
	
	public SaveJob saveJob(User user, Job job) {
		SaveJob saveJob = new SaveJob();
		saveJob.setJob(job);
		saveJob.setUser(user);
		saveJob =  savedJobRepository.save(saveJob);
		return saveJob;
	}
	
	public List<Job> getOtherJob(Integer jid) {
		
		List<Job> jobs = null;
		Pageable pageable = PageRequest.of(0, 2);
		jobs = jobRepo.getOtherJobs(jid, pageable).getContent();
		return jobs;
		
	}
	
	public List<SaveJob> checkSavedJob(Integer jid, Integer uid) {
		return savedJobRepository.findJobs(jid, uid);
	}
	
	public List<Job> retrieveMyJobs(Integer uid){
		return savedJobRepository.myJobs(uid);
	}
	
	public void deleteSaveJob(Integer uid, Integer jid) {
		savedJobRepository.deleteSavedJob(jid, uid);
	}
	
	public Apply applyJob(Integer jid, Integer uid) {
		
		User user = registerRepository.findById(uid).get();
		Job job = jobRepo.findById(jid).get();
		Apply apply = new Apply();
		apply.setJob(job);
		apply.setUser(user);
		apply = applyRepo.save(apply);
		return apply;
	}
	
	public List<Apply> checkJobs(Integer jid, Integer uid) {
		return applyRepo.findJobs(jid, uid);
	}
}
