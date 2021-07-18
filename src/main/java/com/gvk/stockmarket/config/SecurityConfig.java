package com.gvk.stockmarket.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.gvk.stockmarket.enums.MemoryTypes;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${myapp.security.authenticationType}")
	private String memoryType;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private NoOpPasswordEncoder noOpPasswordEncoder;
	
	protected void configure(AuthenticationManagerBuilder auth) {
		if(memoryType.equals(MemoryTypes.RAM.toString())) {
			try {
				auth.inMemoryAuthentication().withUser("vamsi").password("{noop}krishna").authorities("ADMIN");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(memoryType.equals(MemoryTypes.JDBC.toString())) {
			try {
				auth.jdbcAuthentication()
				.dataSource(dataSource)
				.passwordEncoder(noOpPasswordEncoder)
				.usersByUsernameQuery("select username, password, enabled from user where username=?")
				.authoritiesByUsernameQuery("select u.username, r.name from user_roles ur, user u, role r where username=?  and u.id = ur.user_id and r.id = ur.role_id");
			} catch (Exception e) {
				e.printStackTrace();
			}
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
