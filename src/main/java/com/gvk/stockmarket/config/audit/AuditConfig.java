package com.gvk.stockmarket.config.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.gvk.stockmarket.models.User;
import com.gvk.stockmarket.services.impl.AuditAwareImpl;

@Configuration
@EnableJpaAuditing
public class AuditConfig {
	
	@Bean
	public AuditorAware<User> auditorAware() {
		return new AuditAwareImpl();
	}

}
