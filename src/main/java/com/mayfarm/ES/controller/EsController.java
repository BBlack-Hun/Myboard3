package com.mayfarm.ES.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mayfarm.ES.service.EsService;
import com.mayfarm.freeboard.vo.Criteria;
import com.mayfarm.freeboard.vo.PageMaker;



@Controller
@RequestMapping("/elastic")
public class EsController {

	private static final Logger logger = LoggerFactory.getLogger(EsController.class);
	
	@Inject
	private EsService service;
		
	//게시판 목록
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(Model model, String str) throws Exception {
		logger.info("elastic Search page");
		ArrayList<Map<String, Object>> hits = new ArrayList<Map<String,Object>>();
		
		// 파라미터의 유무에 따라 나눠짐. (전체 검색 or 파라미터를 이용한 검색)
		if (str != "" && str != null) {
			System.out.println("난 데이터를 가지고 있다. " + str);
			// 반환된 리스트..
			hits = (ArrayList<Map<String, Object>>) service.GET(str);
		} else {
			System.out.println("난 데이터를 가지고 있지 않지...");
			hits = (ArrayList<Map<String, Object>>) service.GET();
		}
		
		// jsp로 넘길 데이터와 이름
		model.addAttribute("elastic", hits);
		
		return "/elastic/index";
	}
	
	// 게시물 세부 정보
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public String read(Model model) throws Exception {
		logger.info("elastic read");
		
		return "/elastic/read";
	}
}
