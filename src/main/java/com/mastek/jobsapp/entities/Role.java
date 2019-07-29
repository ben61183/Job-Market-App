package com.mastek.jobsapp.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "JPA_ROLE")
@NamedQueries({
	@NamedQuery(name="Role.findByCategory", // name = "<classname>.<queryname>"
			query="select e from Role e where e.category = :cat") // query = "object_query"
})
@XmlRootElement
public class Role {
	@Value("0")
	private int roleID;
	@Value("defult")
	private String category;
	@Value("defult")
	private String roleName;
	
	
	private Set<Vacancy> roleVacancies = new HashSet<>();
	

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
	

	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL, mappedBy="thisRole")
	@XmlTransient // ignore the collections while using API
	public Set<Vacancy> getRoleVacancies() {
		return roleVacancies;
	}

	public void setRoleVacancies(Set<Vacancy> roleVacancies) {
		this.roleVacancies = roleVacancies;
	}
	
	

	
	
	
	
}
