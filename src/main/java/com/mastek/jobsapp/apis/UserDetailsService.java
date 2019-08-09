package com.mastek.jobsapp.apis;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.secure.spi.GrantedPermission;
import java.util.Set;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	
	@Autowired
	private VacancyService vacSer;
	
	public UserDetailsService() {
		System.out.println("User Created");
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public User registerAccountOrUpdate(User usr) {
		usr= userRepository.save(usr);
		System.out.println("User Registered"+usr);
		return usr;
	}
	
	@Transactional
	@GET
	@Path("/find/{userId}")
	@Produces({MediaType.APPLICATION_JSON})
	public User findByUserId(@PathParam("userId") int userId) {
		try {
			return userRepository.findById(userId).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@DELETE
	@Path("delete/{userId}")
	public void deleteByUserId(int userId) {
		userRepository.deleteById(userId);
	}
	

	@Transactional
	@POST
	@Path("/assign/skill")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public User assignSkill(@FormParam("userId") int userId, @FormParam("skillId") int skillId) {
		try {
			// fetch entities to be associated
			User u = findByUserId(userId);
			Skill s = skiSer.findBySkillId(skillId);
			// manage the association
			u.getUserSkills().add(s); // one assigned with many
			u = registerAccountOrUpdate(u);
			return u;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	@Transactional
	@POST
	@Path("/assign/vacancy")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public User assignVacancy(@FormParam("userId") int userId, @FormParam("vacancyId") int vacancyId) {
		try {
			// fetch entities to be associated
			User u = findByUserId(userId);
			Vacancy s = vacSer.findByVacanyId(vacancyId);
			// manage the association
			u.getSavedVacancies().add(s); // one assigned with many
			u = registerAccountOrUpdate(u);
			return u;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	@POST
	@Path("/delete/skill")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
		public User deleteSkill(@FormParam("userId") int userId, @FormParam("skillId") int skillId) {
		try {
			// fetch entities to be associated
			User u = findByUserId(userId);
			Skill s = skiSer.findBySkillId(skillId);

			u.getUserSkills().remove(s); 
			u = registerAccountOrUpdate(u);
			return u;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	@POST
	@Path("/delete/vacancy")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
		public User deleteVacancy(@FormParam("userId") int userId, @FormParam("vacancyId") int vacancyId) {
		try {
			// fetch entities to be associated
			User u = findByUserId(userId);
			Vacancy v = vacSer.findByVacanyId(vacancyId);

			u.getSavedVacancies().remove(v); 
			u = registerAccountOrUpdate(u);
			return u;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
