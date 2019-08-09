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
@Table(name = "JPA_COMPANY")
@XmlRootElement
public class Company {
	@Value("0")
	private int companyId;
	
	@FormParam("companyName")
	@Value("defult")
	private String companyName;

	@FormParam("hqLocation")
	@Value("defult")
	private String hqLocation;
	
	@FormParam("linkedIn")
	@Value("defult")
	private String linkedIn;
	
	private Set<Vacancy> companyVacancies = new HashSet<>();

	@Id
	@Column(name="companyid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	@Column(name="companyname",nullable=false)
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name="hqlocation",nullable=false)
	public String getHqLocation() {
		return hqLocation;
	}

	public void setHqLocation(String hqLocation) {
		this.hqLocation = hqLocation;
	}

	@Column(name="linkedin",nullable=false)
	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL, mappedBy="thisCompany")
	@XmlTransient // ignore the collections while using API
	public Set<Vacancy> getCompanyVacancies() {
		return companyVacancies;
	}

	public void setCompanyVacancies(Set<Vacancy> companyVacancies) {
		this.companyVacancies = companyVacancies;
	}

}
