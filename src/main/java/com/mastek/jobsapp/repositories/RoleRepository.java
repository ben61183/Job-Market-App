package com.mastek.jobsapp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.mastek.jobsapp.entities.Role;
// role repository for crud operations
@Component
public interface RoleRepository extends CrudRepository<Role, Integer>{
	// find role by category for sorting
	List<Role> findByCategory(@Param("searchParam") String searchParam);
	
}
