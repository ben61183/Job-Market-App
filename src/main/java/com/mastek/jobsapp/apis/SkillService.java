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
	
	@POST // http method to send form data
	@Path("/register") // url 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) // consume form data
	@Produces(MediaType.APPLICATION_JSON) // produce json data
	@Transactional
	public Skill registerOrUpdateSkill(@BeanParam Skill skill) {
		Skill currentSkill = findBySkillId(skill.getSkillId());
		if (currentSkill!=null) {
			currentSkill.setSkill(skill.getSkill());
		} else {
			skill = skillRepository.save(skill);
		}
		
		System.out.println("skill" + skill);
		return skill;
	}
	
	@Path("/find/{skilid}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Transactional
	public Skill findBySkillId(@PathParam("skillid") int skillId) {
		try {
			Skill ski = skillRepository.findById(skillId).get();
			int count = ski.getSkillsOfVacancy().size();
			int count2 = ski.getSkillsOfUser().size();
			return ski;
		}catch (Exception e) {
			
			System.out.println("no skill found");
			return null;
		}
	}
	
	@GET
	@Path("/list")
	@Produces({MediaType.APPLICATION_JSON})
	public Iterable<Skill> listAllRoles(){
		// fetch all departments from the table
		return skillRepository.findAll();
	}
}

