package com.mayfarm.noticeboard.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mayfarm.freeboard.vo.Criteria;
import com.mayfarm.freeboard.vo.PageMaker;
import com.mayfarm.noticeboard.service.NoticeService;

@Controller
@RequestMapping(value="/notice")
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Inject
	private NoticeService service;
	
	// 게시판 목록 보기
	@RequestMapping(value="index", method=RequestMethod.GET)
	public String index(Model model, Criteria cri) throws Exception {
		logger.info("Notice index");
		
		model.addAttribute("list", service.list());
		
		// PageMaker 객체 생성
		PageMaker pageMaker = new PageMaker(cri);
		
		// 전체 개시물 수를 구함
		int totalCount = service.totalCnt(cri);
		
		//pageMaker로 전달 -> pageMaker는 startPage, endPage, prev, next를 계산.
		pageMaker.setTotalCount(totalCount);
		
		model.addAttribute("pageMaker", pageMaker);
				
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
