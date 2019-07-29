package com.mastek.jobsapp.apis;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.jobsapp.entities.Role;
import com.mastek.jobsapp.repositories.RoleRepository;

@Component
@Scope("singleton")
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository; 
	
	@Transactional
	public Role registerOrUpdateRole(Role role) {
		role = roleRepository.save(role);
		return role; 
		
	}
	
	public List<Role> fetchRoleByCat(@QueryParam("cat") String cat){
		return roleRepository.findByCategory(cat);
	}
	
//	public Role findByRoleId(int roleId) {
//		Role role = roleRepository.findById(roleId).get(); 
//		return role;
//	}
}
