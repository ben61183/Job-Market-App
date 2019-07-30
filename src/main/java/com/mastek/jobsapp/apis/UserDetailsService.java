package com.mastek.jobsapp.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.jobsapp.entities.User;
import com.mastek.jobsapp.repositories.UserRepository;



@Component
@Scope
public class UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserDetailsService() {
		System.out.println("User Created");
	}
	
	
	public User registerAccountOrUpdate(User usr) {
		usr= userRepository.save(usr);
		System.out.println("User Registered"+usr);
		return usr;
	}
	
	public User findByUserId(int userId) {
		try {
			return userRepository.findById(userId).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void deleteByUserId(int userId) {
		userRepository.deleteById(userId);
	}


	
}

