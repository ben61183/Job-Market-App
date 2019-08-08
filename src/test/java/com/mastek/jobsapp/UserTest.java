package com.mastek.jobsapp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
	@Ignore
	@Test
	public void addAccountUsingService(){
		User usr = new User();
		usr.setUserId(0);
		usr.setUsername("bob123");
		usr.setEmail("bob123@hotmail.com");
		usr = userDetailsService.registerAccountOrUpdate(usr);
		assertNotNull(usr);
	
	}
	
	@Test
	public void deleteByUserIdUsingService() {
		int userId=4;
		userDetailsService.deleteByUserId(userId);
		assertNull(userDetailsService.findByUserId(userId));
	}
	//
}
