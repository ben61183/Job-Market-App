package com.mastek.jobsapp.apis;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.secure.spi.GrantedPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mastek.jobsapp.entities.User;
import com.mastek.jobsapp.repositories.UserRepository;



@Component
@Scope
@Path("/userdetails/")
public class UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
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
	
	@Path("find/{userId}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Transactional
	public User findByUserId(int userId) {
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
	


	
}

