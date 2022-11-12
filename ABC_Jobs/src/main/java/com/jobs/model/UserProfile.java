package com.jobs.model;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="user_profile")
public class UserProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_profile_id")
	private Integer user_profile_id;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="self_description")
	private String self_description;
	
	@Column(name="profileImage")
	@Lob
	private byte[] profileImage;
	
	@OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="l_id", referencedColumnName="l_id")
	private Location location;

	@OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id", referencedColumnName="user_id")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@OneToMany(mappedBy="user_profile", cascade=CascadeType.MERGE)
    private List<Experience> experiences;

	@OneToMany(mappedBy="user_profile", cascade=CascadeType.MERGE)
	private List<Education> education;

	public Integer getUser_profile_id() {
		return user_profile_id;
	}

	public void setUser_profile_id(Integer user_profile_id) {
		this.user_profile_id = user_profile_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSelf_description() {
		return self_description;
	}

	public void setSelf_description(String self_description) {
		this.self_description = self_description;
	}

	public byte[] getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	public List<Education> getEducation() {
		return education;
	}

	public void setEducation(List<Education> education) {
		this.education = education;
	}
}
