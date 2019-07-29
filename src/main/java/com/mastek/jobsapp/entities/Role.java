package com.mastek.jobsapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
@Entity
@Table(name = "JPA_ROLE")
@NamedQueries({
	@NamedQuery(name="Role.findByCategory", // name = "<classname>.<queryname>"
			query="select e from Role e where e.category = :cat") // query = "object_query"
})
@XmlRootElement
public class Role implements Serializable {
	
	@Value("0")
	private int roleID;
	@FormParam("category")
	@Value("defult")
	private String category;
	@FormParam("roleName")
	@Value("defult")
	private String roleName;

	
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



	
	
	
	
}
