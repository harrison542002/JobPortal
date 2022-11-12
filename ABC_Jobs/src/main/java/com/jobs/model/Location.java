package com.jobs.model;

import javax.persistence.*;

@Entity
@Table(name="location")
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="l_id")
	private Integer l_id;
	@Column(name="country")
	private String country;
	@Column(name="city")
	private String city;
	@OneToOne(fetch = FetchType.EAGER, mappedBy="location")
	private UserProfile userProfile;
	public Integer getL_id() {
		return l_id;
	}

	public void setL_id(Integer l_id) {
		this.l_id = l_id;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
