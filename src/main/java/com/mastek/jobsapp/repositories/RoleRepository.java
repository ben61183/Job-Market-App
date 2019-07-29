package com.mastek.jobsapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.mastek.jobsapp.entities.Role;

@Component
public interface RoleRepository extends CrudRepository<Role, Integer>{
	
}
