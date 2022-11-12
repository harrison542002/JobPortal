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
import javax.persistence.Table;

@Entity
@Table(name = "savedjob")
public class SaveJob {
	
	@Id
	@Column(name = "save_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer save_id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id" , referencedColumnName = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade =CascadeType.MERGE)
	@JoinColumn(name = "job_id", referencedColumnName = "job_id")
	private Job job;

	public Integer getSave_id() {
		return save_id;
	}

	public void setSave_id(Integer save_id) {
		this.save_id = save_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
}
