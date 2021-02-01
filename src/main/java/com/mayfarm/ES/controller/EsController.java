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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.mayfarm.ES.service.EsService;
import com.mayfarm.ES.vo.Criteria;
import com.mayfarm.ES.vo.EsVO;
import com.mayfarm.ES.vo.PageMaker;



@Controller
@RequestMapping("/elastic")
public class EsController {

	private static final Logger logger = LoggerFactory.getLogger(EsController.class);
	
	@Inject
	private EsService service;
	
	// 결과 내 재검색을 위한 전역변수 선언
	// 결과 내 재검색을 위한 리스트 선언
	List<String> rList = new ArrayList<String>();

	// 결과내 재검색을 위한 String 배열 선언
	String[] str = new String[10];
		
	//게시판 목록
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(Model model, HttpServletRequest request, Criteria cri) throws Exception {
		// 로그 출력
		logger.info("elastic Search page");
		
		// 결과를 반환하기 위한 list 선언
		List<EsVO> hit = new ArrayList<EsVO>();
		
		// 한번에 값을 넘기기 위해, Map에 저장해 둔다.
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		// 통합검색 MAP 생성
		Map<String, Object> AList = new HashMap<String, Object>();
		// JTBC 카테고리 MAP 생성
		Map<String, Object> JList = new HashMap<String, Object>();
		// KBS 카테고리 MAP 생성
		Map<String, Object> KList = new HashMap<String, Object>();
		// MBC 카테고리 MAP 생성
		Map<String, Object> MList = new HashMap<String, Object>();
		
		// PageMaker 객체 생성
		PageMaker pageMaker = new PageMaker(cri);
		// 관련 변수 초기화
		String totalCount = "";
		int toCnt;
		
		// 검색어 유입
		str[0] = request.getParameter("search");
		// 재검색 단어
		String ostr = str[0];
		// 카테고리 유입
		String Category = request.getParameter("Category");
		
		try {
			switch (Category) {
				case "통합검색":
					Category = "통합검색";
					AList = service.TGET(str, cri, Category);
					
					modelMap.put("ostr", ostr);
					modelMap.put("Category", Category);
					modelMap.put("str", str[0]);
					modelMap.put("elastic", AList);
					modelMap.put("len", AList.get("total"));
					break;
					
				case "JTBC":
					Category = "JTBC";
					JList = service.JGET(str, cri, Category);
										
					// 전체 개시물 수를 구함 String -> int 형변환
					totalCount = JList.get("total").toString();
					toCnt = Integer.parseInt(totalCount);
					
					//pageMaker로 전달 -> pageMaker는 startPage, endPage, prev, next를 계산.
					pageMaker.setTotalCount(toCnt);
					
					modelMap.put("pageMaker", pageMaker);
					modelMap.put("ostr", ostr);
					modelMap.put("Category", Category);
					modelMap.put("str", str[0]);
					modelMap.put("elastic", JList);
					model.addAttribute("index", modelMap);
					return "/elastic/JTBC";
					
				case "KBS":
					Category = "KBS";
					KList = service.KGET(str, cri, Category);
					
					// 전체 개시물 수를 구함 String -> int 형변환
					totalCount = KList.get("total").toString();
					toCnt = Integer.parseInt(totalCount);
					
					//pageMaker로 전달 -> pageMaker는 startPage, endPage, prev, next를 계산.
					pageMaker.setTotalCount(toCnt);
					
					modelMap.put("pageMaker", pageMaker);
					modelMap.put("ostr", ostr);
					modelMap.put("Category", Category);
					modelMap.put("str", str[0]);
					modelMap.put("elastic", KList);
					model.addAttribute("index", modelMap);
					return "/elastic/KBS";

				case "MBC":
					Category = "MBC";
					MList = service.MGET(str, cri, Category);
					
					// 전체 개시물 수를 구함 String -> int 형변환
					totalCount = MList.get("total").toString();
					toCnt = Integer.parseInt(totalCount);
					
					//pageMaker로 전달 -> pageMaker는 startPage, endPage, prev, next를 계산.
					pageMaker.setTotalCount(toCnt);

					modelMap.put("pageMaker", pageMaker);					
					modelMap.put("on", ostr);
					modelMap.put("Category", Category);
					modelMap.put("str", str[0]);
					modelMap.put("elastic", MList);
					model.addAttribute("index", modelMap);
					return "/elastic/MBC";
					
				default:
					System.out.println("검색어 없음");
					break;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("index", modelMap);
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
