package com.mastek.jobsapp.entities;
<<<<<<< HEAD
import java.io.Serializable;


=======
import java.io.Serializable;
import java.io.Serializable;
>>>>>>> branch 'master' of https://github.com/ben61183/Job-Market-App.git
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CascadeType;
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
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
@Entity
@Table(name = "JPA_ROLE")
@NamedQueries({
	@NamedQuery(name="Role.findByCategory", // name = "<classname>.<queryname>"
			query="select r from Role r where role_name like concat('%', :searchParam, '%')") // query = "object_query"
})
@XmlRootElement
public class Role implements Serializable {
	
	//ensure lower case 'd' in roleId
	@Value("0")
	private int roleId;
	
	@FormParam("category")
	@Value("defult")
	private String category;
	
	@FormParam("roleName")
	@Value("defult")
	private String roleName;
<<<<<<< HEAD
=======
	
	
>>>>>>> branch 'master' of https://github.com/ben61183/Job-Market-App.git

	private Set<Vacancy> roleVacancies = new HashSet<>();
	
	public Role() {
		System.out.println("new role created");
	}

	@Id
	@Column(name="roleid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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
	

<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/ben61183/Job-Market-App.git
	}

<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/ben61183/Job-Market-App.git
	

