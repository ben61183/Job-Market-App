package com.mastek.jobsapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mastek.jobsapp.entities.Company;
import com.mastek.jobsapp.entities.Role;
// company repository for crud operations
public interface CompanyRepository extends CrudRepository<Company, Integer>{

}
