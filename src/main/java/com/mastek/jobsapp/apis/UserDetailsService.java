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
	
// used to debug email's sent 
private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserDetailsService.class);

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
	
@POST // HTTP method to send form data 
@Path("/register") // URL  
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) // consume form data
@Produces(MediaType.APPLICATION_JSON) // returns JSON 
@Transactional
public User registerAccountOrUpdate(@BeanParam User usr) {
	User currentUser = findByUserId(usr.getUserId()); // get user by user id
	if (currentUser!=null) { //update an existing user, if already exists 
		currentUser.setUsername(usr.getUsername());	
		currentUser.setPassword(usr.getPassword());
		currentUser.setEmail(usr.getEmail());
		usr = userRepository.save(usr);
		try {
			emailService.userChangeDetailsEmail(usr);} // if a new user is created send an email
		catch (MailException e) {
			logger.info("Error Sending Email: " + e.getMessage());
		}
	} else {
		usr=userRepository.save(usr); // if user doesn't exist, create a new one 
		try {
			emailService.userWelcomeEmail(usr);} // if a new user is created send an email
		catch (MailException e) {
			logger.info("Error Sending Email: " + e.getMessage());
		}
	}		
	return usr;
}
	
@Path("/find/{userId}") // URL
@GET // HTTP method to get form data
@Produces({ //declare all possible content types of return value
	MediaType.APPLICATION_JSON, //object to be given in JSON format
	MediaType.APPLICATION_XML}) //object to be given in XML})
@Transactional
public User findByUserId(@PathParam("userId") int userId) {
	try { // find user by user id 
		User use = userRepository.findById(userId).get();
		return use; 
	} catch (Exception e) {
		System.out.println("user not present.");
		return null;
	}
}
	
@Transactional
@GET
@Path("/userlist/") //fetch all vacancies from table
@Produces({MediaType.APPLICATION_JSON})
public Iterable<User> listAllUsers(){
	return userRepository.findAll();
}

	
@Path("find/username/")
@GET
@Produces({ //declare all possible content types of return value
	MediaType.APPLICATION_JSON, //object to be given in JSON format
})
@Transactional // find user by username 
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
//@Transactional // search by email address
public List<User> findUserByEmail(@QueryParam("mail") String mail) {
	try {
		return userRepository.findByEmail(mail);//find by email
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}
	
@DELETE
@Path("delete/{userId}") // delete user by user ID 
public void deleteByUserId(@PathParam("userId") int userId) {
	userRepository.deleteById(userId);
}
	

@Transactional // defines the scope of a database transaction 
@POST
@Path("/assign/skill")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public User assignSkill(@FormParam("userId") int userId, @FormParam("skillId") int skillId) {
	try {
		User u = findByUserId(userId); // fetch entities to be associated
		Skill s = skiSer.findBySkillId(skillId);
		u.getUserSkills().add(s); // manage the association
		u = registerAccountOrUpdate(u); // one assigned with many
		return u;
	} catch(Exception e) {
		e.printStackTrace();
		return null;
	}
}
	
// assign a vacancy to a user 
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

// delete a skill from a specific user 
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
		// remove the skill from the user
		u.getUserSkills().remove(s); 
		u = registerAccountOrUpdate(u); // update the user
		return u;
	} catch(Exception e) {
		e.printStackTrace();
		return null;
	}
}

// delete a vacancy from a user 
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
		// remove a vacancy from a user 
		u.getSavedVacancies().remove(v);
		// save the changes
		u = registerAccountOrUpdate(u); 
		return u;
	} catch(Exception e) {
		e.printStackTrace();
		return null;
	}
}
}
