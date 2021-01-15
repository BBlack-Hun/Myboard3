package com.mayfarm.noticeboard.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mayfarm.noticeboard.service.NoticeService;

@Controller
@RequestMapping(value="/notice")
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Inject
	private NoticeService service;
	
	// 게시판 목록 보기
	@RequestMapping(value="index", method=RequestMethod.GET)
	public String index(Model model) throws Exception {
		logger.info("Notice index");
		
		model.addAttribute("list", service.list());
		return "/notice/index";
	}
	
	// 게시글 보기
	@RequestMapping(value="read", method=RequestMethod.GET)
	public String read(Model model) {
		logger.info("readView");
		
		System.out.println();
		return "/notice/read";
	}
	
}
