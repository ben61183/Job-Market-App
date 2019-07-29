package com.mastek.jobsapp.apis;

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
	
	@Autowired
	public Role registerOrUpdateRole(Role role) {
		role = roleRepository.save(role);
		return role;
			
	}
	
//	public Role findByRoleId(int roleId) {
//		Role role = roleRepository.findById(roleId).get(); 
//		return role;
//	}
}
