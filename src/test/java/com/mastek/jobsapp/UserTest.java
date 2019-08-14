package com.mastek.jobsapp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastek.jobsapp.apis.UserDetailsService;
import com.mastek.jobsapp.entities.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	@Autowired
	UserDetailsService userDetailsService;
	
	@Test
	public void addAccountUsingService(){
		User usr = new User();
		usr.setUserId(10000);
		usr.setUsername("bob123");
		usr.setEmail("bob123@hotmail.com");
		usr.setPassword("123");
		usr = userDetailsService.registerAccountOrUpdate(usr);
		assertNotNull(usr);
	}
	
	@Test
	public void deleteByUserIdUsingService() {
		int userId=332;
		userDetailsService.deleteByUserId(userId);
		assertNull(userDetailsService.findByUserId(userId));
	}
	
	@Test
	public void addSkillToUser() {
		userDetailsService.assignSkill(1, 2);
		assertNotNull(userDetailsService.findByUserId(1).getUserSkills());
	}
	
	@Test
	public void saveVacancyToUser() {
		userDetailsService.assignVacancy(1, 5);
		assertNotNull(userDetailsService.findByUserId(1).getSavedVacancies());
	}
	
	@Test
	public void deleteVacancyFromUser() {
		int vacanciesPre = userDetailsService.findByUserId(1).getSavedVacancies().size();
		userDetailsService.deleteVacancy(1, 5);
		assertTrue(vacanciesPre>userDetailsService.findByUserId(1).getSavedVacancies().size());
	}
	
	
	@Test
	public void deleteSkillFromUser() {
		int skillsPre = userDetailsService.findByUserId(1).getUserSkills().size();
		userDetailsService.deleteSkill(1,2);
		System.out.println(skillsPre);
		System.out.println(userDetailsService.findByUserId(1).getUserSkills().size());
		assertTrue(skillsPre>userDetailsService.findByUserId(1).getUserSkills().size());
	}
	
}
