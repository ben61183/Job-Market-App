package com.mastek.jobsapp.entities;

import java.io.Serializable;


import java.util.HashSet;
import java.util.Set;


import javax.persistence.CascadeType;


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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.ws.rs.Path;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlTransient;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Scope
@Entity
@Table(name="user")
@NamedQueries({
	@NamedQuery(name="User.findByUsername", // name = "<classname>.<queryname>"
			query="select u from User u where u.username=:name"), 
	@NamedQuery(name="User.findByEmail", // name = "<classname>.<queryname>"
	query="select u from User u where u.email=:mail")// query = "object_query"
})
@XmlRootElement
public class User implements Serializable{
	@FormParam("userId")
	private int userId;
	@FormParam("username")
	private String username;
	@FormParam("password")
	private String password;
	@FormParam("email")
	private String email;


	
	private Set<Skill> userSkills = new HashSet<>();
	
	private Set<Vacancy> savedVacancies = new HashSet<>();

	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Column(name="username", nullable=false, unique=true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="password", nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="email", nullable=false, unique=true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}



	@Override
	public String toString() {
		return "User [getUserId()=" + getUserId() + ", getUsername()=" + getUsername() + ", getPassword()="
				+ getPassword() + ", getEmail()=" + getEmail() + "]";

	}
	// user-skill
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER) // has cascade -> primary class. EAGER is not ideal
	@JoinTable(name = "JPA_USERSKILLS", 
		joinColumns = @JoinColumn(name = "FK_USERID"),
		inverseJoinColumns = @JoinColumn(name = "FK_SKILLID"))
	public Set<Skill> getUserSkills() {
		return userSkills;
	}
	public void setUserSkills(Set<Skill> userSkills) {
		this.userSkills = userSkills;
	}
	
	
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER) // has cascade -> primary class
	@JoinTable(name = "JPA_USERVACANCIES", 
		joinColumns = @JoinColumn(name = "FK_USERID"),
		inverseJoinColumns = @JoinColumn(name = "FK_VACANCYID"))
	public Set<Vacancy> getSavedVacancies() {
		return savedVacancies;
	}
	public void setSavedVacancies(Set<Vacancy> savedJobs) {
		this.savedVacancies = savedJobs;

	}
	
	
	
	
	
}
