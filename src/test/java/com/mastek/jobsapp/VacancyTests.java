
package com.mastek.jobsapp;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastek.jobsapp.apis.VacancyService;
import com.mastek.jobsapp.entities.Role;
import com.mastek.jobsapp.entities.Vacancy;
import com.mastek.jobsapp.repositories.VacancyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VacancyTests {

	@Autowired
	VacancyService vacSer;
	
	@Autowired
	VacancyRepository vacRepository; 
	
	@Test
	public void addVacancyUsingService() {
		Vacancy vac= new Vacancy();
		vac.setTitle("New Vacancy");
		vac.setSalary(1000);
		vac.setCompany("New Company");
		vac.setDescription("new description");
		vac.setJobType(true);
		vac.setLink("www.sadas.com");
		vac.setPostTime("45 minutes ago");
		vac.setLocation("default location");
		vac.setUploadYear(2015);
		vac=vacSer.registerOrUpdateVacancy(vac);
		assertNotNull(vac);
	}



	@Test
	public void findByVacancyById() {
		int vacancyId = 3; 
		assertNotNull(vacSer.findByVacanyId(vacancyId));
		
	}
	
	@Test
	public void updateJobTitle() {
		String[] jobTitles = new String[20];
			jobTitles[0] = ".NET Developer"; jobTitles[1] = "Java Developer"; jobTitles[2] = "C# Developer"; jobTitles[3] = "DevOps Engineer"; jobTitles[4] = "Front End Developer"; jobTitles[5] = "C# Developer";
			jobTitles[6] = "IT Engineer"; jobTitles[7] = "Full Stack Developer"; jobTitles[8] = "Web Developer"; jobTitles[9] = "PHP Developer"; jobTitles[10] = "JavaScript Developer"; jobTitles[11]="Infastructure Developer";
			jobTitles[11] = "Network Engineer"; jobTitles[12] = "React Developer"; 
			
		Random random = new Random(); 
			
		for (int i = 1; i < 1001; i++) {
			Vacancy currentVac = vacSer.findByVacanyId(i);
			int n = random.nextInt(12);
			n+=1; 
			currentVac.setTitle(jobTitles[n]);
			currentVac = vacRepository.save(currentVac);
		}
		
	}
//	@Test
//	public void deleteByVacancyId() {
//		int vacancyId = 4; 
//		vacSer.deleteByVacancyId(vacancyId);
//		assertNull(vacSer.findByVacanyId(vacancyId));
//	}

	

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}


}



