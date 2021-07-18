package com.gvk.stockmarket.services.impl;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.gvk.stockmarket.models.User;

@Component
@Primary
public class AuditAwareImpl implements AuditorAware<User> {
	
	@Autowired
	private HttpSession httpSession;

	@Override
	public Optional<User> getCurrentAuditor() {
		return (Optional<User>) httpSession.getAttribute("user");
	}

}
