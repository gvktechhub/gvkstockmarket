package com.gvk.stockmarket.services.impl;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.gvk.stockmarket.models.User;
import com.gvk.stockmarket.services.IUserService;

@Component
@Primary
public class AuditAwareImpl implements AuditorAware<User> {
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private IUserService userService;

	@Override
	public Optional<User> getCurrentAuditor() {
		System.out.println("from audit aware: "+httpSession.getAttribute("user"));
		//return (Optional<User>) httpSession.getAttribute("user");
		return userService.findByUsername("vamsi");
	}

}
