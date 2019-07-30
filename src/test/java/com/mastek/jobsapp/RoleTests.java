
package com.mastek.jobsapp;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mastek.jobsapp.apis.RoleService;
import com.mastek.jobsapp.entities.Role;	
import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.assertNull;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastek.jobsapp.apis.RoleService;
import com.mastek.jobsapp.apis.VacancyService;
import com.mastek.jobsapp.entities.Role;
import com.mastek.jobsapp.entities.Vacancy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleTests {
	
	@Autowired
	RoleService rolSer;
	
	@Autowired
	VacancyService vacSer;
	
	//@Test
	public void testRolesLoaded() {
		System.out.println("Role Tests Loaded");
	}
	
<<<<<<< HEAD


=======
>>>>>>> branch 'master' of https://github.com/ben61183/Job-Market-App.git

//	@Test
//	public void findByRoleId() {
//		int roleid = 4; 
//		assertNotNull(rolSer.findByRoleId(roleid));
//	}
<<<<<<< HEAD


=======
>>>>>>> branch 'master' of https://github.com/ben61183/Job-Market-App.git
	@Test
	public void addNewRole() {

		Role role1 = new Role();
		role1.setRoleName("SQL");
		role1.setCategory("Test");
		role1 = rolSer.registerOrUpdateRole(role1);
		
		Role role2 = new Role();
		role2.setRoleName("Python");
		role2.setCategory("Cloud Services");
<<<<<<< HEAD
		role2 = rolSer.registerOrUpdateRole(role2);

=======
		role2 = rolSer.registerOrUpdateRole(role2);
>>>>>>> branch 'master' of https://github.com/ben61183/Job-Market-App.git
		
		assertNotNull(role1);
	}



	@Test
	public void findByRoleId() {

		int roleid = 11; 
<<<<<<< HEAD
		assertNotNull(rolSer.findByRoleId(roleid));
=======
>>>>>>> branch 'master' of https://github.com/ben61183/Job-Market-App.git
	}
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/ben61183/Job-Market-App.git

	// add back later
	@Ignore
	// add back later
	@Test
	public void deleteByRoleId() {
		int roleid = 12; 
		rolSer.deleteByRoleId(roleid);
		//assertNull(rolSer.findByRoleId(roleid));
	}

<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/ben61183/Job-Market-App.git
	public void findByCat() {
		List<Role> rolesOfCategory = rolSer.fetchRoleByCat("Test");
		System.out.println("test roles:"+rolesOfCategory);
		assertTrue(rolesOfCategory.size()>0);
<<<<<<< HEAD
	}

=======
	}
>>>>>>> branch 'master' of https://github.com/ben61183/Job-Market-App.git

	@Test
	public void assignRole() {
		Role role2 = new Role();
		role2.setRoleName("java");
		role2.setCategory("applications");
		role2 = rolSer.registerOrUpdateRole(role2);
		
		Vacancy vac= new Vacancy();
		vac.setTitle("New Vacancy");
		vac.setSalary(1000);
		vac.setCompany("New Company");
		vac.setDescription("new description");
		vac.setJobType(true);
		vac.setLink("www.sadas.com");
		vac.setPostTime("45 minutes ago");
		vac.setLocation("default location");
		vac.setThisRole(role2);
		vac = vacSer.registerOrUpdateVacancy(vac);
<<<<<<< HEAD
	}

}
=======
	}
}
>>>>>>> branch 'master' of https://github.com/ben61183/Job-Market-App.git
