 package com.jobs.model;

public class UserMsg {
	
	private String firstName;
	private String lastName;
	private String country;
	private String city;
	private String image;
	private Integer uid;
	private Integer cid;
	
	public UserMsg(String firstName, String lastName, String country, String city, String image, Integer uid, Integer cid) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.city = city;
		this.image = image;
		this.uid = uid;
		this.cid = cid;
	}
	
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
