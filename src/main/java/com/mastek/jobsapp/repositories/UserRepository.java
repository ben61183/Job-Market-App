package com.mastek.jobsapp.repositories;

import java.util.List;

import org.aspectj.weaver.patterns.DeclareParentsMixin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.mastek.jobsapp.entities.User;

@Component
public interface UserRepository extends CrudRepository<User, Integer>{

public List<User> findByUsername(@Param("name") String name);
public List<User> findByEmail(@Param("mail") String mail);
	
}
