package com.jobs.model;

import javax.persistence.*;

@Entity
@Table(name="education")
public class Education
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="edu_id")
	private Integer edu_id;
	
	@Column(name="school")
	private String school;
	
	@Column(name="qualification")
	private String qualification;
	
	@ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name="user_profile_id")
	private UserProfile user_profile;

	public Integer getEdu_id() {
		return edu_id;
	}

	public void setEdu_id(Integer edu_id) {
		this.edu_id = edu_id;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public UserProfile getUser_profile() {
		return user_profile;
	}

	public void setUser_profile(UserProfile user_profile) {
		this.user_profile = user_profile;
	}
	
}
