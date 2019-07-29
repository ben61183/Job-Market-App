package com.mastek.jobsapp.apis;

import org.springframework.beans.factory.annotation.Autowired;

import com.mastek.jobsapp.entities.Role;
import com.mastek.jobsapp.repositories.RoleRepository;

public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository; 
	
	@Autowired
	public Role registerOrUpdateRole(Role role) {
		role = roleRepository.save(role);
		return role;
			
	}
	
	public Role findByRoleId(int roleId) {
		Role role = roleRepository.findById(roleId).get(); 
		return role;
	}
}
