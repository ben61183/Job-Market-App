package com.mastek.jobsapp.apis;

import org.glassfish.jersey.server.ResourceConfig;

<<<<<<< HEAD
	
	
=======

public class APIConfig extends ResourceConfig{
	public APIConfig(){
		register(RoleService.class);
		register(VacancyService.class);
		register(CORSFilter.class);
	}
>>>>>>> branch 'master' of https://github.com/ben61183/Job-Market-App.git
}
