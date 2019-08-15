package com.mastek.jobsapp.apis;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
//Allows rest API components to be accessed 
@Component
public class APIConfig extends ResourceConfig{
	public APIConfig(){
		register(CORSFilter.class);
		register(RoleService.class);
		register(VacancyService.class);
		register(UserDetailsService.class);
		register(SkillService.class);
		register(CompanyService.class);

	}
}


