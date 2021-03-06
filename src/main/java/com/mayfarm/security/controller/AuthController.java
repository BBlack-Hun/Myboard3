
package com.mayfarm.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class); 
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login() {
		logger.info("login page");
		return "login";
	}
	
	
	@RequestMapping(value="/login", method= RequestMethod.POST)
	public String Login() {
		logger.info("login success");
		return "redirect:/Main";
	}
}
