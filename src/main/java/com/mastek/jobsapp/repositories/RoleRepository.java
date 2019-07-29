package com.mastek.jobsapp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.mastek.jobsapp.entities.Role;

@Component
public interface RoleRepository extends CrudRepository<Role, Integer>{

	List<Role> findByCategory(@Param("cat") String cat);
	
}
