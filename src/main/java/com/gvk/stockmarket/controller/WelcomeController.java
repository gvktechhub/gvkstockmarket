package com.gvk.stockmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/welcome")
public class WelcomeController {
	
	@GetMapping(value="/index")
	public String index() {
		return "welcome";
	}
	
	@GetMapping(value="/mypage")
	public String mypage() {
		return "mypage";
	}

}
