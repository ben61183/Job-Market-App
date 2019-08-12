package com.mastek.jobsapp.apis;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.jobsapp.entities.Role;
import com.mastek.jobsapp.entities.Skill;
import com.mastek.jobsapp.entities.Vacancy;
import com.mastek.jobsapp.repositories.VacancyRepository;

//@componet for spingboot to work
@Component
@Scope("singleton")
@Path("/vacancy/")
public class VacancyService {
		
	@Autowired
	private VacancyRepository vacancyRepository;

	@Autowired
	private RoleService rolSer;
	
	@Autowired
	private SkillService skiSer;

	public VacancyService() {
		System.out.println("Player Service Created");
	}
	
	
	@POST // http method to send form data
	@Path("/register") // url 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) // consume form data
	@Produces(MediaType.APPLICATION_JSON) // produce json data
	@Transactional
	public Vacancy registerOrUpdateVacancy(@BeanParam Vacancy job) {
		Vacancy currentVac = findByVacanyId(job.getVacancyId());
		if (currentVac!=null) {
			currentVac.setDescription(job.getDescription());
			currentVac.setJobType(job.isJobType());
			currentVac.setLink(job.getLink());
			currentVac.setLocation(job.getLocation());
			currentVac.setPostTime(job.getPostTime());
			currentVac.setSalary(job.getSalary());
			currentVac.setTitle(job.getTitle());
			currentVac.setUploadYear(job.getUploadYear());
			job = vacancyRepository.save(job);
			
		} else {
			job=vacancyRepository.save(job);
		}
		
		System.out.println("Vacancy" + job);
		return job;
	}
	
	@Path("/find/{vacancyId}")
	@GET //HTTP Method used to call the api
	@Produces({ //declare all possible content types of return value
		MediaType.APPLICATION_JSON, //object to be given in JSON format
		MediaType.APPLICATION_XML //object to be given in XML
	})
	@Transactional //to help fetch dependent data
	public Vacancy findByVacanyId(@PathParam("vacancyId") int vacancyId) {
		try {
			Vacancy vac = vacancyRepository.findById(vacancyId).get();
			int count = vac.getVacancySkills().size();
			System.out.println("vacancyskills count: "+count);
			return vac;
		}catch (Exception e) {
			e.printStackTrace();
			//System.out.println("no vacancy found");
			return null;
		}
	}

	@DELETE //delete http method
	@Path("/delete/{vacancyId}")
	public void deleteByVacancyId(@PathParam("vacancyId") int vacancyId) {
		vacancyRepository.deleteById(vacancyId);
	}

	@Transactional
	@GET
	@Path("/list")
	@Produces({MediaType.APPLICATION_JSON})
	public Iterable<Vacancy> listAllVacanciess(){
		//fetch all vacancies from table
		return vacancyRepository.findAll();
	}

	
	@Transactional
	@POST
	@Path("/assign/role")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Vacancy assignRole(@FormParam("roleId") int roleId, @FormParam("vacancyId") int vacancyId) {
		try {
			Vacancy vac = findByVacanyId(vacancyId);
			Role rol = rolSer.findByRoleId(roleId);
			vac.setThisRole(rol);
			vac = registerOrUpdateVacancy(vac);
			System.out.println("role assigned: "+vac.getThisRole().getRoleName()+"role id:"+vac.getThisRole().getRoleId()+"vac id:"+vac.getVacancyId());
			return vac;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		}
	
	@Transactional
	@POST
	@Path("/assign/skill")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Skill> assignSkill(@FormParam("vacancyId") int vacancyId, @FormParam("skillId") int skillId) {
		try {
			// fetch entities to be associated
			Vacancy v = findByVacanyId(vacancyId);
			Skill s = skiSer.findBySkillId(skillId);
			// manage the association
			v.getVacancySkills().add(s); // one assigned with many
			v = registerOrUpdateVacancy(v);
			return v.getVacancySkills();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path("/theseskills/{vacancyId}")
	@Produces({MediaType.APPLICATION_JSON})
	@Transactional
	public Iterable<Skill> loadSkillsOfVacancy(@PathParam("vacancyId") int vacancyId){
		Vacancy vac = findByVacanyId(vacancyId);
		int count = vac.getVacancySkills().size();
		System.out.println("vacancyskills count: "+count);
		return vac.getVacancySkills();
	}
}


