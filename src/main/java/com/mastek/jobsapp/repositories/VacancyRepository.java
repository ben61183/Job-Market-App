package com.mastek.jobsapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.mastek.jobsapp.entities.Vacancy;
//comments
@Component
public interface VacancyRepository extends CrudRepository<Vacancy,Integer>{
	
	

}
