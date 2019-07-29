package com.mastek.jobsapp.apis;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;

import com.mastek.jobsapp.entities.Role;


public class RoleService {

	@Autowired
	RoleService rolRep = new RoleService();
	
	public List<Role> fetchRolesByCategory(@QueryParam("cat") String category){
		return rolRep.fetchRolesByCategory(category);
	}

	public Role registerOrUpdateRole(Role role) {
		System.out.println("");
		return role;
			
	}
}
