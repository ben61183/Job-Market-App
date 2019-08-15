package com.mastek.jobsapp.apis;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mastek.jobsapp.entities.Role;
import com.mastek.jobsapp.entities.Skill;
import com.mastek.jobsapp.entities.Vacancy;
import com.mastek.jobsapp.repositories.SkillRepository;

@Component
@Scope("singleton")
@Path("/skill/")
public class SkillService {

@Autowired
private SkillRepository skillRepository;

@POST // HTTP method to send form data
@Path("/register") // URL 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) // consume form data
@Produces(MediaType.APPLICATION_JSON) // produce JSON data
@Transactional
public Skill registerOrUpdateSkill(@BeanParam Skill skill) {
	Skill currentSkill = findBySkillId(skill.getSkillId()); // find skill by skill ID 
	if (currentSkill!=null) { // if it exists, update 
		currentSkill.setSkill(skill.getSkill());
	} else { // if it dosen't save as  new 
		skill = skillRepository.save(skill);
	}
	System.out.println("skill" + skill);
	return skill;
}

// tries to find skill by skillid and if not returns "no skill found"
@Path("/find/{skilid}")
@GET
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Transactional 
public Skill findBySkillId(@PathParam("skillid") int skillId) {
	try {
		Skill ski = skillRepository.findById(skillId).get();
		int count = ski.getSkillsOfVacancy().size();
		int count2 = ski.getSkillsOfUser().size();
		return ski;}
	catch (Exception e) {
		System.out.println("no skill found");
		return null;
	}
}

// list all roles available 
@GET
@Path("/list")
@Produces({MediaType.APPLICATION_JSON})
public Iterable<Skill> listAllRoles(){
	// fetch all departments from the table
		return skillRepository.findAll();
	}
}

