package com.mastek.jobsapp.entities;
import java.io.Serializable;





import java.io.Serializable;

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
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

@Scope("prototype") // default scope
@Entity // declate role as an entity
@Table(name = "JPA_ROLE")
@NamedQueries({ // named query to find by category
	@NamedQuery(name="Role.findByCategory", // name = "<classname>.<queryname>"
			query="select r from Role r where role_name like concat('%', :searchParam, '%')") // query = "object_query"
})
@XmlRootElement
public class Role implements Serializable {
	
	// form param for id
	@FormParam("roleId")
	@Value("0")
	private int roleId;
	// form param for category
	@FormParam("category")
	@Value("defult")
	private String category;
	// form param for role title
	@FormParam("roleName")
	@Value("defult")
	private String roleName;

	// vacancy object
	private Set<Vacancy> roleVacancies = new HashSet<>();
	
	public Role() {
		System.out.println("new role created");
	}

	// auto generate the roleId
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

	// one to many with vacancy
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL, mappedBy="thisRole")
	@XmlTransient // ignore the collections while using API
	public Set<Vacancy> getRoleVacancies() {
		return roleVacancies;
	}

	public void setRoleVacancies(Set<Vacancy> roleVacancies) {
		this.roleVacancies = roleVacancies;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", category=" + category + ", roleName=" + roleName + ", roleVacancies="
				+ roleVacancies + "]";
	}
}





