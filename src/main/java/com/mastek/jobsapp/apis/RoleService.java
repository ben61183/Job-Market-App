package com.mastek.jobsapp.apis;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.jobsapp.entities.Role;
import com.mastek.jobsapp.repositories.RoleRepository;

@Component
@Scope("singleton")
@Path("/role/")
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository; 
	
	@POST // http method to ssend the form data
	@Path("/register") // url pattern
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) // form data
	@Produces(MediaType.APPLICATION_JSON) // json data
	@Transactional
	public Role registerOrUpdateRole(@BeanParam Role role) {
		Role currentRole = findByRoleId(role.getRoleID());
		if (currentRole!=null) {
			currentRole.setRoleName(role.getRoleName());
			currentRole.setCategory(role.getCategory());
			currentRole.setRoleVacancies(role.getRoleVacancies());
			role = roleRepository.save(role);
		} else {
			role = roleRepository.save(role);
		}
		System.out.println("Role Registered " + role);
		return role; 
	}
	

	@Path("/find/{roleid}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Transactional
	public Role findByRoleId(@PathParam("roleid") int roleId) {
		Role role = roleRepository.findById(roleId).get(); 
	
		return role;
	}
	
	@DELETE
	@Path("/delete/{roleid}")
	public void deleteByRoleId(@PathParam("roleid") int roleId) {
		roleRepository.deleteById(roleId);
	}

	@Path("/fetch_category")
	@GET // http method used to call the api
	@Produces({ // declare all possible content types of return value
		MediaType.APPLICATION_JSON, // object to be given in JSON
	})
	public List<Role> fetchRoleByCat(@QueryParam("cat") String cat){
		return roleRepository.findByCategory(cat);
	}
}

//	public Role findByRoleId(int roleId) {
//		Role role = roleRepository.findById(roleId).get(); 
//		return role;
//	}

