package com.mastek.jobsapp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.mastek.jobsapp.entities.Vacancy;
//vacancy repository for crud operations
@Component
public interface VacancyRepository extends CrudRepository<Vacancy,Integer>{
}
