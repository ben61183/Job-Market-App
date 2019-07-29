package com.mastek.jobsapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "JPA_ROLE")
@XmlRootElement
public class Role {
	@Value("0")
	private int roleID;
	@Value("defult")
	private String category;
	@Value("defult")
	private String roleName;
	
	// to be calculated from vacancy
//	@Value("0")
//	private int rankNow;
//	@Value("0")
//	private int medianSalaryNow;
//	@Value("0")
//	private int numVacanciesNow;
//	@Value("0")
//	private int rankLast;
//	@Value("0")
//	private int medianSalaryLast;
//	@Value("0")
//	private int numVacanciesLast;
	
	public Role() {
		System.out.println("new role created");
	}

	@Id
	@Column(name="roleid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	@Column(name="category", nullable=false)
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Column(name="roleName",nullable=false)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	// end of tabulated results
	
	
	
	
}
