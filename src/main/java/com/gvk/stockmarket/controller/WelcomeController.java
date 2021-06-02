package com.gvk.stockmarket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/welcome")
public class WelcomeController {
	
	private static final Logger log = LoggerFactory.getLogger(WelcomeController.class);
	
	@GetMapping(value="/index")
	public String index() {
		return "welcome";
	}
	
	@GetMapping(value="/mypage")
	public String mypage() {
		return "mypage";
	}

}
