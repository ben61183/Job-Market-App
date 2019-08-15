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
	
	// form param of vacancy id
	@Value("-1")
	@FormParam("vacancyId")
	private int vacancyId;
	
	// form param of title
	@Value("Default Vacancy Title")
	@FormParam("title")
	private String title;
	
	// form param of salary
	@Value("-1")
	@FormParam("salary")
	private int salary;
	
	// form param of location
	@Value("Default Vacancy location")
	@FormParam("location")
	private String location;
	
	// form param of description
	@Value("Default description")
	@FormParam("description")
	private String description;
	
	// form param of link
	@Value("Default url")
	@FormParam("link")
	private String link;
	
	// form param of post time
	@Value("Default Time")
	@FormParam("postTime")
	private String postTime;
	
	// form param of job type (permanent/contract = true/false)
	@Value("true")
	@FormParam("jobType")
	private boolean jobType;
	
	// form param of upload year
	@Value("2015")
	@FormParam("uploadYear")
	private int uploadYear;
	
	// one to many of role
	private Role thisRole;
	// one to many of company
	private Company thisCompany;
	// many to many of skills
	private Set<Skill> vacancySkills = new HashSet<>();
	// many ot many of users
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
	
	@Column(name="title", nullable= false, length=45) // set column name, nullable and maximum length
	public String getTitle() {
		return title;
	}

	
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="salary", nullable= false, length=45) // set column name, nullable and maximum length
	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Column(name="location", nullable= false, length=45) // set column name, nullable and maximum length
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name="description", nullable= false, length=150) // set column name, nullable and maximum length
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Column(name="link", nullable= false, length=100) // set column name, nullable and maximum length
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Column(name="post_time", nullable= false, length=45) // set column name, nullable and maximum length
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

	@Column(name="job_type", nullable= false, length=45) // set column name, nullable and maximum length
	public boolean isJobType() {
		return jobType;
	}

	public void setJobType(boolean jobType) {
		this.jobType = jobType;
	}
	
	// called when new vacancy created
	public Vacancy() {
	System.out.println("Vacancy Created");
	}
	
	// many to one link with role
	@ManyToOne
	@JoinColumn(name="fk_role")
	public Role getThisRole() {
		return thisRole;
	}

	public void setThisRole(Role thisRole) {
		this.thisRole = thisRole;
	}

	// object string output
	@Override
	public String toString() {
		return "Vacancy [getVacancyId()=" + getVacancyId() + ", getTitle()=" + getTitle() + ", getSalary()="
				+ getSalary() + ", getLocation()=" + getLocation() + ", getDescription()="
				+ getDescription() + ", getLink()=" + getLink()
				+ ", getPostTime()=" + getPostTime() + ", isJobType()=" + isJobType() + "]";
	}
	
	// many to many with skills (add skill to vacancy)
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER) // EAGER is not ideal, used to show skills in object
	@JoinTable(name = "JPA_VACANCYSKILLS", // join columns in new join table
		joinColumns = @JoinColumn(name = "FK_VACANCYID"), 
		inverseJoinColumns = @JoinColumn(name = "FK_SKILLID"))
	public Set<Skill> getVacancySkills() {
		return vacancySkills;
	}

	public void setVacancySkills(Set<Skill> vacancySkills) {
		this.vacancySkills = vacancySkills;
	}

	// many to many with user (add vacacny to user)
	@ManyToMany(fetch=FetchType.LAZY,mappedBy = "savedVacancies")
	@XmlTransient
	public Set<User> getVacancyUsers() {
		return vacancyUsers;
	}

	public void setVacancyUsers(Set<User> vacancyUsers) {
		this.vacancyUsers = vacancyUsers;
	}

	// many to one relationship with company
	@ManyToOne
	@JoinColumn(name="fk_company")
	public Company getThisCompany() {
		return thisCompany;
	}

	public void setThisCompany(Company thisCompany) {
		this.thisCompany = thisCompany;
	}	
	
}	
