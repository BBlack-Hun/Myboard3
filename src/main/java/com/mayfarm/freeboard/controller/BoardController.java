package com.mayfarm.freeboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mayfarm.main.HomeController;

@Controller
@RequestMapping("/freeboard")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index() {
		logger.info("freeboard index");
		
		return "/freeboard/index";
	}
	
	

}
