package com.mastek.jobsapp.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
@Entity
@Table(name = "JPA_SKILL")
@XmlRootElement
public class Skill {

	// id doesnt need form param
	@Value("0")
	private int skillId;
	// form param for skill name
	@FormParam("skill")
	@Value("default")
	private String skill;
	// many to many with vacancy
	private Set<Vacancy> skillsOfVacancy = new HashSet<>();
	// many to many with user
	private Set<User> skillsOfUser = new HashSet<>();
	
	// auto generated id for skillId
	@Id
	@Column(name="skillId")
	@GeneratedValue
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	
	@Column(name="skill",nullable=false) // declare name of column and not nullable
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	// many to many relationship with vacancy
	@ManyToMany(fetch=FetchType.LAZY,mappedBy = "vacancySkills")
	@XmlTransient
	public Set<Vacancy> getSkillsOfVacancy() {
		return skillsOfVacancy;
	}
	public void setSkillsOfVacancy(Set<Vacancy> skillsOfVacancy) {
		this.skillsOfVacancy = skillsOfVacancy;
	}
	
	// many to many relationship with user
	@ManyToMany(fetch=FetchType.LAZY,mappedBy = "userSkills")
	@XmlTransient
	public Set<User> getSkillsOfUser() {
		return skillsOfUser;
	}
	public void setSkillsOfUser(Set<User> skillsOfUser) {
		this.skillsOfUser = skillsOfUser;
	}
}
