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
import com.mastek.jobsapp.entities.Vacancy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VacancyTests {

	@Autowired
	VacancyService vacSer;
	
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
		
		vac=vacSer.registerOrUpdateVacancy(vac);
		assertNotNull(vac);
	}

//	@Autowired
//	VacancyService vacSer;
//	
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	//sda

}


