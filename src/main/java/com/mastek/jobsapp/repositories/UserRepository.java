package com.mastek.jobsapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.mastek.jobsapp.entities.User;

@Component
public interface UserRepository extends CrudRepository<User, Integer>{
	
}
