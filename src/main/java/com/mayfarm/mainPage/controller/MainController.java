package com.mayfarm.mainPage.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mayfarm.freeboard.service.BoardService;
import com.mayfarm.freeboard.vo.BoardVO;
import com.mayfarm.freeboard.vo.Criteria;
import com.mayfarm.noticeboard.service.NoticeService;

@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Inject
	private BoardService Bservice;
	
	@Inject
	private NoticeService Nservice;
	
	@RequestMapping(value="/Main", method=RequestMethod.GET)
	public String Main(Model model, Criteria cri) throws Exception {
		logger.info("welcome Main");
		
		model.addAttribute("boardcnt", Bservice.totalCnt(cri));
		model.addAttribute("noticecnt", Nservice.totalCnt(cri));
		
		return "Main";
		
	}

}
