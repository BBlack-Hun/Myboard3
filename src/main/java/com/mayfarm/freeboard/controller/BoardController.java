package com.mayfarm.freeboard.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mayfarm.freeboard.service.BoardService;
import com.mayfarm.main.HomeController;

@Controller
@RequestMapping("/freeboard")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	// 게시판 목록
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(Model model) throws Exception{
		logger.info("freeboard index");
		
		model.addAttribute("list", service.list());
		
		return "/freeboard/index";
	}
	
	// 게시글 보기
		@RequestMapping(value="read", method=RequestMethod.GET)
		public String read(Model model) {
			logger.info("readView");
			
			System.out.println();
			return "/freeboard/read";
		}
	

}
