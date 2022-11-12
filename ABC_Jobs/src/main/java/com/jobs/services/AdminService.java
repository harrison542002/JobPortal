package com.jobs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobs.model.Job;
import com.jobs.repository.JobRepo;

@Service
public class AdminService {

	@Autowired
	private JobRepo jobRepo;
	
	public Job saveJob(Job job) {
		return jobRepo.save(job);
	}
	
}
