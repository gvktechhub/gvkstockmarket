package com.gvk.stockmarket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	protected void configure(AuthenticationManagerBuilder auth) {
		try {
			auth.inMemoryAuthentication().withUser("vamsi").password("{noop}krishna").authorities("ADMIN");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void configure(HttpSecurity http) {
		try {
			http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/welcome/**").authenticated()
			.antMatchers("/apis/**").permitAll()
			.anyRequest().permitAll()
			
			.and()
			.formLogin()
			.defaultSuccessUrl("/welcome/index", true)
			
			.and()
			.logout()
			
			.and()
			.exceptionHandling()
			.accessDeniedPage("/denied")
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
