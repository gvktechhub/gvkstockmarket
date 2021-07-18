package com.gvk.stockmarket.runners;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.gvk.stockmarket.common.Constants;
import com.gvk.stockmarket.enums.RoleTypes;
import com.gvk.stockmarket.models.Role;
import com.gvk.stockmarket.models.User;
import com.gvk.stockmarket.services.IRoleService;
import com.gvk.stockmarket.services.IUserService;

@Component
public class InitialRunner implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(InitialRunner.class);
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private Environment environment;
	
	@Override
	public void run(String... args) throws Exception {
		log.info("STARTED :: Initializing initial runner");
		if(roleService.list().isEmpty()) loadRoles();
		if(userService.list().isEmpty()) {
			createUser(RoleTypes.ROLE_APP_ADMIN);
			createUser(RoleTypes.ROLE_ADMIN);
		}
		log.info("ENDED :: initial runner");
	}

	private void loadRoles() {
		Arrays.asList(RoleTypes.values()).forEach(role-> {
			roleService.saveOrUpdate(new Role(role.toString()));
		});
	}
	
	private void createUser(RoleTypes roleType) {
		Boolean isAppAdmin = RoleTypes.ROLE_APP_ADMIN.equals(roleType);
		userService.saveOrUpdaate(
				new User(environment.getProperty(isAppAdmin ? Constants.APPADMIN_USERNAME_KEY : Constants.ADMIN_USERNAME_KEY), 
						environment.getProperty(isAppAdmin ? Constants.APPADMIN_PASSWORD_KEY : Constants.ADMIN_PASSWORD_KEY), 
						environment.getProperty(isAppAdmin ? Constants.APPADMIN_MOBILE_KEY : Constants.ADMIN_MOBILE_KEY), 
						environment.getProperty(isAppAdmin ? Constants.APPADMIN_EMAIL_KEY : Constants.ADMIN_EMAIL_KEY), 
						isAppAdmin ? roleService.list() : Arrays.asList(roleService.get(RoleTypes.ROLE_ADMIN.toString())))
				);
	}

}
