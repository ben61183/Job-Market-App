package com.mastek.jobsapp.apis;


import javax.transaction.Transactional;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.hibernate.secure.spi.GrantedPermission;


import java.util.List;
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
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;


import org.springframework.stereotype.Component;

import com.mastek.jobsapp.entities.Skill;
import com.mastek.jobsapp.entities.User;
import com.mastek.jobsapp.entities.Vacancy;
import com.mastek.jobsapp.repositories.UserRepository;


@Component
@Scope
@Path("/user/")
public class UserDetailsService {
	
//	private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserDetailsService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SkillService skiSer;
	
	@Autowired
	private VacancyService vacSer;
	
	@Autowired
	private EmailService emailService;
	
	public UserDetailsService() {
		System.out.println("User Created");
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public User registerAccountOrUpdate(@BeanParam User usr) {
		User currentUser = findByUserId(usr.getUserId());
		if (currentUser!=null) {
			//update an existing user 
			currentUser.setUsername(usr.getUsername());	
			currentUser.setPassword(usr.getPassword());
			currentUser.setEmail(usr.getEmail());
			usr = userRepository.save(usr);
		} else {
			usr=userRepository.save(usr);
		
		try {
			emailService.userWelcomeEmail(usr);
		} catch (MailException e) {
//			logger.info("Error Sending Email: " + e.getMessage());
			}
		}		
		
		return usr;
	}
	
	@Path("/find/{userId}")
	@GET
	@Produces({ //declare all possible content types of return value
		MediaType.APPLICATION_JSON, //object to be given in JSON format
		MediaType.APPLICATION_XML}) //object to be given in XML})
	@Transactional
	public User findByUserId(@PathParam("userId") int userId) {
		try {
			User use = userRepository.findById(userId).get();
			return use; 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	@GET
	@Path("/userlist/")
	@Produces({MediaType.APPLICATION_JSON})
	public Iterable<User> listAllUsers(){
		//fetch all vacancies from table
		return userRepository.findAll();
	}

	
	@Path("find/username/")
	@GET
	@Produces({ //declare all possible content types of return value
		MediaType.APPLICATION_JSON, //object to be given in JSON format
	})
	//@Transactional
	public List<User> findUserByUsername(@QueryParam("name") String name) {
		try {
			return userRepository.findByUsername(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Path("find/email/")
	@GET
	@Produces({ //declare all possible content types of return value
		MediaType.APPLICATION_JSON, //object to be given in JSON format
	})
	//@Transactional
	public List<User> findUserByEmail(@QueryParam("mail") String mail) {
		try {
			return userRepository.findByEmail(mail);//find by email
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@DELETE
	@Path("delete/{userId}")
	public void deleteByUserId(@PathParam("userId") int userId) {
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

//user