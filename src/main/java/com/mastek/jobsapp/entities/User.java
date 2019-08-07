package com.mastek.jobsapp.entities;

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
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope
@Entity
@Table(name="user")
public class User {
	private int userId;
	private String username;
	private String password;
	private String email;
	private String passwordConfirm;
	
	private Set<Skill> userSkills = new HashSet<>();
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	} 
	@Override
	public String toString() {
		return "User [getUserId()=" + getUserId() + ", getUsername()=" + getUsername() + ", getPassword()="
				+ getPassword() + ", getEmail()=" + getEmail() + ", getPasswordConfirm()=" + getPasswordConfirm() + "]";
	}
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY) // has cascade -> primary class
	@JoinTable(name = "JPA_USERSKILLS", 
		joinColumns = @JoinColumn(name = "FK_USERID"),
		inverseJoinColumns = @JoinColumn(name = "FK_SKILLID"))
	public Set<Skill> getUserSkills() {
		return userSkills;
	}
	public void setUserSkills(Set<Skill> userSkills) {
		this.userSkills = userSkills;
	}
	
	
	
	
	
}
