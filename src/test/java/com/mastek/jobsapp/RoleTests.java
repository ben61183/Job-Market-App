package com.mastek.jobsapp;

import static org.junit.Assert.assertNotNull;
<<<<<<< HEAD
import static org.junit.Assert.assertNull;
=======
import static org.junit.Assert.assertTrue;

import java.util.List;
>>>>>>> branch 'master' of https://github.com/ben61183/Job-Market-App.git

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastek.jobsapp.apis.RoleService;
import com.mastek.jobsapp.entities.Role;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleTests {
	@Autowired
	RoleService rolSer;
	
	//@Test
	public void testRolesLoaded() {
		System.out.println("Role Tests Loaded");
	}
	
//	//@Autowired
//	@Test
//	public void addNewRole() {
//		Role role1 = new Role();
//		role1.setRoleID(1);
//		role1.setRoleName("SQL");
//		role1.setCategory("Test");
//		role1 = rolSer.registerOrUpdateRole(role1);
//		
//		assertNotNull(role1);
//	}
//	
//	@Test
//	public void findByRoleId() {
//		int roleid = 4; 
//		assertNotNull(rolSer.findByRoleId(roleid));
//	}
	
	@Test
<<<<<<< HEAD
	public void deleteByRoleId() {
		int roleid = 9; 
		rolSer.deleteByRoleId(roleid);
		//assertNull(rolSer.findByRoleId(roleid));
	}
=======
	public void findByCat() {
		List<Role> rolesOfCategory = rolSer.fetchRoleByCat("Test");
		System.out.println("test roles:"+rolesOfCategory);
		assertTrue(rolesOfCategory.size()>0);
	}
	

>>>>>>> branch 'master' of https://github.com/ben61183/Job-Market-App.git
}
