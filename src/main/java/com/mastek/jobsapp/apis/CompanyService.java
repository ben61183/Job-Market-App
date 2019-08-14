package com.mastek.jobsapp.apis;

import javax.transaction.Transactional;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;

import com.mastek.jobsapp.entities.Company;
import com.mastek.jobsapp.entities.Vacancy;
import com.mastek.jobsapp.repositories.CompanyRepository;

@Component
@Scope("singleton")
@Path("/company/")
public class CompanyService {
	
	private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserDetailsService.class);

	
	@Autowired
	private VacancyService vacancyService;
	
	@Autowired
	private CompanyRepository companyRep;
	
	@Autowired
	private EmailService emailService;
	
	@POST // http method to send form data
	@Path("/register") // url 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) // consume form data
	@Produces(MediaType.APPLICATION_JSON) // produce json data
	@Transactional
	public Company registerOrUpdateCompany(@BeanParam Company company) {
		Company currentCom = findByCompanyId(company.getCompanyId());
		if (currentCom!=null) {
			currentCom.setCompanyName(company.getCompanyName());
			currentCom.setHqLocation(company.getHqLocation());
			currentCom.setLinkedIn(company.getLinkedIn());
			currentCom.setUsername(company.getUsername());
			currentCom.setPassword(company.getPassword());
			currentCom.setEmail(company.getEmail());
			company=companyRep.save(company);
		} else {
			company=companyRep.save(company);
		try {
			emailService.companyWelcomeEmail(company);} 
		catch (MailException e) {
			logger.info("Error Sending Email: " + e.getMessage());}
			
		}
		return company;

}

	@Path("/find/{companyId}")
	@GET //HTTP Method used to call the api
	@Produces({ //declare all possible content types of return value
		MediaType.APPLICATION_JSON, //object to be given in JSON format
		MediaType.APPLICATION_XML //object to be given in XML
	})
	@Transactional //to help fetch dependent data
	public Company findByCompanyId(@PathParam("companyId")int companyId) {
		try {
			Company com = companyRep.findById(companyId).get();
			int count = com.getCompanyVacancies().size();
			System.out.println("company vacancies count: "+count);
			return com;
		}catch (Exception e) {
			e.printStackTrace();
			//System.out.println("no vacancy found");
			return null;
		}
	}
	
	@DELETE //delete http method
	@Path("/delete/{companyId}")
	public void deleteByCompanyId(@PathParam("companyId") int companyId) {
		companyRep.deleteById(companyId);
	}
	
	@Transactional
	@GET
	@Path("/list")
	@Produces({MediaType.APPLICATION_JSON})
	public Iterable<Company> listAllCompanies(){
		//fetch all vacancies from table
		return companyRep.findAll();
	}

	@Transactional
	@POST
	@Path("/assign/vacancy")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Company assignRole(@FormParam("companyId") int companyId, @FormParam("vacancyId") int vacancyId) {
		try {
			Vacancy vac = vacancyService.findByVacanyId(vacancyId);
			Company com = findByCompanyId(companyId);
			vac.setThisCompany(com);
//			com.getCompanyVacancies().add(vac);
			System.out.println(com.getCompanyVacancies().size()); 
			com = registerOrUpdateCompany(com);
			return com;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		}
	
	@GET
	@Path("/thesevacancies/{companyId}")
	@Produces({MediaType.APPLICATION_JSON})
	@Transactional
	public Iterable<Vacancy> loadSkillsOfVacancy(@PathParam("companyId") int companyId){
		Company com = findByCompanyId(companyId);
		int count = com.getCompanyVacancies().size();
		System.out.println("company vacancies count: " + count);
		return com.getCompanyVacancies();
	}
}
