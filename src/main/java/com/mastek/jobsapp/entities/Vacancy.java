package com.mastek.jobsapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

	
	@Scope("prototype") //one copy for each test
	@Entity // declares as an entity
	@Table(name="JPA_Vacancy")// declaring the table name for the class
	@XmlRootElement
	public class Vacancy implements Serializable {
		
		
			@Value("-1")
			@FormParam("vacancyId")
			private int vacancyId;
			@Value("Default Vacancy Title")
			@FormParam("title")
			private String title;
			@Value("-1")
			@FormParam("salary")
			private int salary;
			@Value("Default Vacancy location")
			@FormParam("location")
			private String location;
			@Value("Default description")
			@FormParam("description")
			private String description;
			@Value("Default company")
			@FormParam("company")
			private String company;
			@Value("Default link")
			@FormParam("link")
			private String link;
			@Value("Default Time")
			@FormParam("postTime")
			private String postTime;
			@Value("true")
			@FormParam("jobType")
			private boolean jobType;
			
			@Value("2015")
			@FormParam("uploadYear")
			private int uploadYear; 
			
			private Role thisRole;
			
			private Set<Skill> vacancySkills = new HashSet<>();
			
			private Set<User> vacancyUsers = new HashSet<>();
			
	@Id //declare the property as Primary Key
	@Column(name="vacancy_id")//declare the column name
	@GeneratedValue(strategy=GenerationType.AUTO) //auto-numbering the primary key
	public int getVacancyId() {
		return vacancyId;
	}

	public void setVacancyId(int vacancyId) {
		this.vacancyId = vacancyId;
	}
	
	@Column(name="title", nullable= false, length=45)
	public String getTitle() {
		return title;
	}

	
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="salary", nullable= false, length=45)
	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Column(name="location", nullable= false, length=45)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name="description", nullable= false, length=150)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="company", nullable= false, length=45)
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name="link", nullable= false, length=100)
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Column(name="post_time", nullable= false, length=45)
	public String getPostTime() {
		return postTime;
	}
	
	@Column(name="upload_year")
	public int getUploadYear() {
		return uploadYear;
	}

	public void setUploadYear(int uploadYear) {
		this.uploadYear = uploadYear;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	@Column(name="job_type", nullable= false, length=45)
	public boolean isJobType() {
		return jobType;
	}

	public void setJobType(boolean jobType) {
		this.jobType = jobType;
	}

	
	
	public Vacancy() {
	System.out.println("Vacancy Created");
	}
		
	@ManyToOne
	@JoinColumn(name="fk_role")
	public Role getThisRole() {
		return thisRole;
	}

	public void setThisRole(Role thisRole) {
		this.thisRole = thisRole;
	}

	@Override
	public String toString() {
		return "Vacancy [getVacancyId()=" + getVacancyId() + ", getTitle()=" + getTitle() + ", getSalary()="
				+ getSalary() + ", getLocation()=" + getLocation() + ", getDescription()="
				+ getDescription() + ", getCompany()=" + getCompany() + ", getLink()=" + getLink()
				+ ", getPostTime()=" + getPostTime() + ", isJobType()=" + isJobType() + "]";
	}
	
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER) // EAGER is not ideal here
	@JoinTable(name = "JPA_VACANCYSKILLS", 
		joinColumns = @JoinColumn(name = "FK_VACANCYID"),
		inverseJoinColumns = @JoinColumn(name = "FK_SKILLID"))
//	@XmlTransient
	public Set<Skill> getVacancySkills() {
		return vacancySkills;
	}

	public void setVacancySkills(Set<Skill> vacancySkills) {
		this.vacancySkills = vacancySkills;
	}

	@ManyToMany(fetch=FetchType.LAZY,mappedBy = "savedVacancies")
	@XmlTransient
	public Set<User> getVacancyUsers() {
		return vacancyUsers;
	}

	public void setVacancyUsers(Set<User> vacancyUsers) {
		this.vacancyUsers = vacancyUsers;
	}	
}	
