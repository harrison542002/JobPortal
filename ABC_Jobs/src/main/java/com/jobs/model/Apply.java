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
@Table(name = "apply")
public class Apply {
	
	@Id
	@Column(name = "apply_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer apply_id;
	
	@ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
	@JoinColumn(name = "job_id", referencedColumnName = "job_id")
	private Job job;

	public Integer getApply_id() {
		return apply_id;
	}

	public void setApply_id(Integer apply_id) {
		this.apply_id = apply_id;
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
