package com.jobs.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "job")
public class Job {

	@Id
	@Column(name = "job_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer job_id;

	@Column(name = "salary")
	private String salary;

	@Column(name = "requirements")
	private String requirements;

	@Column(name = "job_title")
	private String job_title;

	@Column(name = "benefits")
	private String benefits;

	@Column(name = "company_Information")
	private String company_Information;

	@Column(name = "company_name")
	private String company_name;

	@Column(name = "contact_info")
	private String contact_info;

	@Column(name = "additional_information")
	private String additional_information;

	@Column(name = "country")
	private String country;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "job")
	private List<SaveJob> saveJobs;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "job")
	private List<Apply> applies;
	
	
	
	public List<Apply> getApplies() {
		return applies;
	}

	public void setApplies(List<Apply> applies) {
		this.applies = applies;
	}

	public List<SaveJob> getSaveJobs() {
		return saveJobs;
	}

	public void setSaveJobs(List<SaveJob> saveJobs) {
		this.saveJobs = saveJobs;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getJob_id() {
		return job_id;
	}

	public void setJob_id(Integer job_id) {
		this.job_id = job_id;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public String getBenefits() {
		return benefits;
	}

	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}

	public String getCompany_Information() {
		return company_Information;
	}

	public void setCompany_Information(String company_Information) {
		this.company_Information = company_Information;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getContact_info() {
		return contact_info;
	}

	public void setContact_info(String contact_info) {
		this.contact_info = contact_info;
	}

	public String getAdditional_information() {
		return additional_information;
	}

	public void setAdditional_information(String additional_information) {
		this.additional_information = additional_information;
	}
}
