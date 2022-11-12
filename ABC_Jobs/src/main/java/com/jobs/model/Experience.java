package com.jobs.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="experience")
public class Experience {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="experience_id")
	private Integer experience_id;
	
	@Column(name="company")
	private String company;
	
	@Column(name="position")
	private String position;
	
	@Column(name="country")
	private String country;
	
	@Column(name="start_data")
	private Date start_data;
	
	@Column(name="end_date")
	private Date end_date;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade =
			CascadeType.MERGE)
	@JoinColumn(name="user_profile_id")
	private UserProfile user_profile;

	public Integer getExperience_id() {
		return experience_id;
	}

	public void setExperience_id(Integer experience_id) {
		this.experience_id = experience_id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getStart_data() {
		return start_data;
	}

	public void setStart_data(Date start_data) {
		this.start_data = start_data;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public UserProfile getUser_profile() {
		return user_profile;
	}

	public void setUser_profile(UserProfile user_profile) {
		this.user_profile = user_profile;
	}
}
