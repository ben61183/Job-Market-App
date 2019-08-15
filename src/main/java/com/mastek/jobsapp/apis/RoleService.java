package com.mastek.jobsapp.apis;

import java.util.List;

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
import org.springframework.transaction.annotation.Transactional;

import com.mastek.jobsapp.entities.Role;
import com.mastek.jobsapp.entities.Vacancy;
import com.mastek.jobsapp.repositories.RoleRepository;

@Component
@Scope("singleton")
@Path("/role/")
public class RoleService {
	
// connect to role repository
@Autowired
private RoleRepository roleRepository; 
	
@POST // HTTP method to send the form data
@Path("/register") // URL path
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) // form data
@Produces(MediaType.APPLICATION_JSON) // json data
@Transactional
public Role registerOrUpdateRole(@BeanParam Role role) { // register or update a role 
	Role currentRole = findByRoleId(role.getRoleId()); // finds role by role id 
	if (currentRole!=null) {	// if the role already exists, update it 
		currentRole.setRoleName(role.getRoleName());
		currentRole.setCategory(role.getCategory());
		role = roleRepository.save(role);
		System.out.println("Role Editied" + role);
	} else {	// if the role dosen't exist, create a new one 
		role = roleRepository.save(role);
		System.out.println("Role Registered " + role);

	}

	return role; 
}

@Path("/find/{roleid}") // URL path 
@GET // HTTP method to get the data
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) // data will be returned as JSON or XML 
@Transactional 
public Role findByRoleId(@PathParam("roleid") int roleId) { // try to find by role id and return the role
	try {
		return roleRepository.findById(roleId).get();}
	catch (Exception e) {
		System.out.println("no role found");
		return null;
	}
}
	
@DELETE
@Path("/delete/{roleid}")
public void deleteByRoleId(@PathParam("roleid") int roleId) {
	roleRepository.deleteById(roleId); // deletes a role by role ID
}

@Path("/fetch_category") // fetch the associated category
@GET // HTTP method used to call the API
@Produces({ // declare all possible content types of return value
	MediaType.APPLICATION_JSON})// object to be given in JSON
public List<Role> fetchRoleByCat(@QueryParam("searchParam") String searchParam){
	return roleRepository.findByCategory(searchParam);
}

@GET
@Path("/list")
@Produces({MediaType.APPLICATION_JSON})
public Iterable<Role> listAllRoles(){
	// fetch all departments from the table
	return roleRepository.findAll();
}

@GET
@Path("/thesevacancies/{roleId}") // find all vacancies of a specific role
@Produces({MediaType.APPLICATION_JSON})
@Transactional
public Iterable<Vacancy> listAllVacanciesOfRole(@PathParam("roleId") int roleId){
	Role role = findByRoleId(roleId); // find by role ID
	int count = role.getRoleVacancies().size(); // used to combat bug 
	return role.getRoleVacancies(); // return the roles of specific vacancies 
	}
}

