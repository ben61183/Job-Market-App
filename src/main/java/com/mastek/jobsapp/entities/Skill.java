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

	@Value("0")
	private int skillId;
	@FormParam("skill")
	@Value("default")
	private String skill;
	
	private Set<Vacancy> skillsOfVacancy = new HashSet<>();
	
	private Set<User> skillsOfUser = new HashSet<>();
	
	@Id
	@Column(name="skillId")
	@GeneratedValue
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	
	@Column(name="skill",nullable=false)
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	@ManyToMany(fetch=FetchType.LAZY,mappedBy = "vacancySkills")
	@XmlTransient
	public Set<Vacancy> getSkillsOfVacancy() {
		return skillsOfVacancy;
	}
	public void setSkillsOfVacancy(Set<Vacancy> skillsOfVacancy) {
		this.skillsOfVacancy = skillsOfVacancy;
	}
	
	@ManyToMany(fetch=FetchType.LAZY,mappedBy = "userSkills")
	@XmlTransient
	public Set<User> getSkillsOfUser() {
		return skillsOfUser;
	}
	public void setSkillsOfUser(Set<User> skillsOfUser) {
		this.skillsOfUser = skillsOfUser;
	}
	
	
	
}
