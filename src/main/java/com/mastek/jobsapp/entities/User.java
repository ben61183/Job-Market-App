package com.mastek.jobsapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope
@Entity
@Table(name="user")
@XmlRootElement
public class User implements Serializable{
	private int userId;
	private String username;
	private String password;
	private String email;

	
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
	
	@Override
	public String toString() {
		return "User [getUserId()=" + getUserId() + ", getUsername()=" + getUsername() + ", getPassword()="
				+ getPassword() + ", getEmail()=" + getEmail() + "]";
	}
	
	
	
	
	
}
