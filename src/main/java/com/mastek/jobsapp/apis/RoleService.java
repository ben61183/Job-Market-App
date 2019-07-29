package com.mastek.jobsapp.apis;

import javax.transaction.Transactional;

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
	
	public Role findByRoleId(int roleId) {
		Role role = roleRepository.findById(roleId).get(); 
		return role;
	}
	
	public void deleteByRoleId(int roleId) {
		roleRepository.deleteById(roleId);
	}
}
