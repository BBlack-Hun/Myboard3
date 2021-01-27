package com.mayfarm.ES.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mayfarm.ES.service.EsService;
import com.mayfarm.ES.vo.Criteria;
import com.mayfarm.ES.vo.EsVO;



@Controller
@RequestMapping("/elastic")
public class EsController {

	private static final Logger logger = LoggerFactory.getLogger(EsController.class);
	
	@Inject
	private EsService service;
		
	//게시판 목록
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(Model model, HttpServletRequest request, Criteria cri) throws Exception {
		// 로그 출력
		logger.info("elastic Search page");
		
		// 결과를 반환하기 위한 list 선언
		List<EsVO> hit = new ArrayList<EsVO>();
		
		// 한번에 값을 넘기기 위해, Map에 저장해 둔다.
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		// 카테고리별 리스트 생성
		Map<String, Object> AList = new HashMap<String, Object>();
		List<EsVO> kList = new ArrayList<EsVO>();
		List<EsVO> mList = new ArrayList<EsVO>();
		
		// 검색한 값을 넘겨 받음,
		String str = request.getParameter("search"); 
		modelMap.put("str", str);
		// 결과 내 재검색 버튼 활성화 유무
		String ostr = request.getParameter("re");
		// 결과를 계속 활성화 하기 위해 반환함.
		modelMap.put("on", ostr);
		
		// 순수한 통합검색 / 통합검색 + 결과내 재검색 (model로 검색 결과를 넘겨줌)
		try {
			if (ostr != "on") {
				if (str != "" && str != null) {
					
					// 반환된 리스트..
					AList = service.TGET(str, cri);
				
					modelMap.put("JTBC", AList.get("JTBC"));
					modelMap.put("MBC", AList.get("MBC"));
					modelMap.put("KBS", AList.get("KBS"));
					
					modelMap.put("elastic", AList);
					modelMap.put("len", AList.get("total"));
				}
			} else {
				List<String> oss = new ArrayList<String>();
				oss.add(str);
				hit = service.AGET(oss);
				modelMap.put("elastic", hit);
				modelMap.put("len", AList.get("len"));
			}
			model.addAttribute("index", modelMap);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/elastic/index";
	}
	
	// 게시물 세부 정보
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public String read(Model model, int no) throws Exception {
		logger.info("elastic read");
		
		Map<String, Object> hit = service.READ(no);
		
		model.addAttribute("read", hit);
		
		
		return "/elastic/read";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete() throws Exception {
		logger.info("elastic deleted");
		
		return "redirect:/elastic/index";
	}
}
