package com.mastek.jobsapp.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.mastek.jobsapp.entities.Role;
import com.mastek.jobsapp.entities.Skill;


@Component
public interface SkillRepository extends CrudRepository<Skill, Integer>{

}
