package com.mastek.jobsapp.apis;

import java.util.Set;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.jobsapp.entities.Skill;
import com.mastek.jobsapp.entities.User;
import com.mastek.jobsapp.entities.Vacancy;
import com.mastek.jobsapp.repositories.UserRepository;



@Component
@Scope
@Path("/user/")
public class UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SkillService skiSer;
	
	public UserDetailsService() {
		System.out.println("User Created");
	}
	
	
	public User registerAccountOrUpdate(User usr) {
		usr= userRepository.save(usr);
		System.out.println("User Registered"+usr);
		return usr;
	}
	
	public User findByUserId(int userId) {
		try {
			return userRepository.findById(userId).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void deleteByUserId(int userId) {
		userRepository.deleteById(userId);
	}


	@Transactional
	@POST
	@Path("/assign/skill")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Skill> assignSkill(@FormParam("userId") int userId, @FormParam("skillId") int skillId) {
		try {
			// fetch entities to be associated
			User u = findByUserId(userId);
			Skill s = skiSer.findBySkillId(skillId);
			// manage the association
			u.getUserSkills().add(s); // one assigned with many
			u = registerAccountOrUpdate(u);
			return u.getUserSkills();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}

