//package com.mastek.jobsapp;
//
//import static org.junit.Assert.*;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.mastek.jobsapp.apis.VacancyService;
//
//public class VacancyTests {
//
//	@Autowired
//	VacancyService vacSer;
//	
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
//
//}
package com.mastek.jobsapp;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastek.jobsapp.apis.RoleService;
import com.mastek.jobsapp.apis.SkillService;
import com.mastek.jobsapp.apis.VacancyService;
import com.mastek.jobsapp.entities.Role;
import com.mastek.jobsapp.entities.Skill;
import com.mastek.jobsapp.entities.Vacancy;
import com.mastek.jobsapp.repositories.VacancyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VacancyTests {

	@Autowired
	VacancyService vacSer;
	
	@Autowired
	RoleService roleSer; 
	
	@Autowired
	SkillService skillSer;
	
	@Autowired
	VacancyRepository vacRepository; 
	
	
	
	@Ignore
	@Test
	public void addVacancyUsingService() {
		Vacancy vac= new Vacancy();
		vac.setTitle("New Vacancy");
		vac.setSalary(1000);
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
		int vacancyId = 2; 
		assertNotNull(vacSer.findByVacanyId(vacancyId));
		
	}
	
	@Test
	public void updateJobTitle() {
		String[] jobTitles = new String[20];
			jobTitles[0] = ".NET Developer"; jobTitles[1] = "Java Developer"; jobTitles[2] = "C# Developer"; jobTitles[3] = "DevOps Engineer"; jobTitles[4] = "Front End Developer"; jobTitles[5] = "SQL Developer";
			jobTitles[6] = "IT Engineer"; jobTitles[7] = "Full Stack Developer"; jobTitles[8] = "Web Developer"; jobTitles[9] = "PHP Developer"; jobTitles[10] = "JavaScript Developer"; jobTitles[11]="Infastructure Developer";
			jobTitles[12] = "Network Engineer"; jobTitles[13] = "React Developer"; 
			
		 
			
		for (int i = 1; i < 1001; i++) {
			Vacancy currentVac = vacSer.findByVacanyId(i);
			Random random = new Random();
			int n = random.nextInt(13);
			n+=1; 
			Role currentRole = roleSer.findByRoleId(n);
			// user to randomise the job titles 
			//currentVac.setTitle(jobTitles[n]);
			
			// used to randomise the foreign keys 
			currentRole = roleSer.registerOrUpdateRole(currentRole);
			currentVac.setThisRole(currentRole);
			currentVac = vacSer.registerOrUpdateVacancy(currentVac);
		}
	}
	
	@Test
	public void addSkill() {
		int vId = 115;
		int sId = 125;
		vacSer.assignSkill(vId, sId);
	}
	

}



	

