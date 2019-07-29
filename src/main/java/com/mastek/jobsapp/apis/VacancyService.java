package com.mastek.jobsapp.apis;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
		
		@Autowired
		private VacancyRepository vacancyRepository;
		
		@Autowired
		private RoleService rolSer;
		
//		
//		private Team currentTeam;
		
		public VacancyService() {
			System.out.println("Player Service Created");
		}
		
		public Vacancy registerOrUpdateVacancy(Vacancy job) {
			job=vacancyRepository.save(job);
			System.out.println("Vacancy" + job);
			return job;
		}
		
		public Vacancy findByVacanyId(int vacancyId) {
			try {
				return vacancyRepository.findById(vacancyId).get();
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		public void deleteByVacancyId(int vacancyId) {
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


