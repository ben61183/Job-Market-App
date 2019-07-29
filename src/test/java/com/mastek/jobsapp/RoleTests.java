package com.mastek.jobsapp;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mastek.jobsapp.apis.RoleService;
import com.mastek.jobsapp.entities.Role;

public class RoleTests {
	@Autowired
	RoleService rolSer;
	
	//@Test
	public void testRolesLoaded() {
		System.out.println("Role Tests Loaded");
	}
	
	@Autowired
	@Test
	public void addNewRole() {
		Role role1 = new Role();
		role1.setRoleID(1);
		role1.setRoleName("SQL");
		role1.setCategory("Test");
		role1 = rolSer.registerOrUpdateRole(role1);
		
		assertNotNull(role1);
	}
	
}
