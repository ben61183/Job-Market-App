package com.mastek.jobsapp.repositories;

import java.util.List;

import org.aspectj.weaver.patterns.DeclareParentsMixin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.mastek.jobsapp.entities.User;

// user repository for crud operations
@Component
public interface UserRepository extends CrudRepository<User, Integer>{
// find user by username (unique)
public List<User> findByUsername(@Param("name") String name);
// find user by email adress (unique)
public List<User> findByEmail(@Param("mail") String mail);
	
}
