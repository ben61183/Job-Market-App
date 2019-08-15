package com.mastek.jobsapp.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.mastek.jobsapp.entities.Skill;
// skill repository for crud operations
@Component
public interface SkillRepository extends CrudRepository<Skill, Integer>{

}
