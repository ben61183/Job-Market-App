package com.mastek.jobsapp.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.jobsapp.entities.Vacancy;
import com.mastek.jobsapp.repositories.VacancyRepository;

@Component
@Scope("singleton")
public class VacancyService {
		
		
		@Autowired
		private VacancyRepository vacancyRepository;
		
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



	}


