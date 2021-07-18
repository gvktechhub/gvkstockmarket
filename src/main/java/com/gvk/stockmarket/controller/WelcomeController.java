package com.gvk.stockmarket.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gvk.stockmarket.services.IUserService;

@Controller
@RequestMapping(value="/welcome")
public class WelcomeController {
	
	private static final Logger log = LoggerFactory.getLogger(WelcomeController.class);
	
	@Autowired
	private IUserService userService;
	
	@GetMapping(value="/index")
	public String index(Principal principal, HttpSession session) {
		session.setAttribute("username", principal.getName().toUpperCase());
		session.setAttribute("user", userService.findByUsername(principal.getName()));
		System.out.println(session.getAttribute("user"));
		return "welcome";
	}
	
	@GetMapping(value="/mypage")
	public String mypage() {
		return "mypage";
	}

}
