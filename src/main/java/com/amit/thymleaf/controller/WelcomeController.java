package com.amit.thymleaf.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
	
	@Value("${welcome.message}")
	private String message;
	
	@RequestMapping("/")
	public String welcome(Model model) {
//		String message = "Thymleaf";
		model.addAttribute("message", message);
		
		return "welcome";
	}
	
	@RequestMapping("/home")
	public String home() {
		
		return "index2";
	}

}
