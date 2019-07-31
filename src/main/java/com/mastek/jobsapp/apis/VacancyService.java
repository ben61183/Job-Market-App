package com.mastek.jobsapp.apis;

import java.util.List;

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

import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.jobsapp.entities.Role;
import com.mastek.jobsapp.entities.Vacancy;
import com.mastek.jobsapp.repositories.VacancyRepository;

@Component
@Scope("singleton")
@Path("/vacancy/")
public class VacancyService {
		// o
		@Autowired
		private VacancyRepository vacancyRepository;

		@Autowired
		private RoleService rolSer;
		
//		
//		private Team currentTeam;

		
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
				currentVac.setCompany(job.getCompany());	
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
		public Vacancy findByVacanyId(@PathParam("vacancyId")int vacancyId) {
			try {
				return vacancyRepository.findById(vacancyId).get();
			}catch (Exception e) {
				System.out.println("no vacancy found");
				return null;
			}
		}

		@DELETE //delete http method
		@Path("/delete/{vacancyId}")
		public void deleteByVacancyId(@PathParam("vacancyId") int vacancyId) {
			vacancyRepository.deleteById(vacancyId);
		}

		
//		@ManyToOne
//		@JoinColumn(name="FK_Team_id")
//		public Team getCurrentTeam() {
//			return currentTeam;
//		}
//
//		public void setCurrentTeam(Team currentTeam) {
//			this.currentTeam = currentTeam;
//		}

		
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
			return vac;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
			
		}

}


