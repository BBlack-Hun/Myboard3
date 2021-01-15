package com.mayfarm.freeboard.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mayfarm.freeboard.service.BoardService;
import com.mayfarm.freeboard.vo.BoardVO;
import com.mayfarm.freeboard.vo.Criteria;
import com.mayfarm.freeboard.vo.PageMaker;

@Controller
@RequestMapping("/freeboard")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	
	// 게시판 목록
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(Model model, Criteria  cri) throws Exception{
		logger.info("freeboard index");
		
		model.addAttribute("list", service.list(cri));
		
		// PageMaker 객체 생성
		PageMaker pageMaker = new PageMaker(cri);
		
		// 전체 개시물 수를 구함
		int totalCount = service.totalCnt(cri);
		
		//pageMaker로 전달 -> pageMaker는 startPage, endPage, prev, next를 계산.
		pageMaker.setTotalCount(totalCount);
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "/freeboard/index";
	}
	
	// 게시글 보기
	@RequestMapping(value="read", method=RequestMethod.GET)
	public String read(Model model, BoardVO boardVO, PageMaker pageMaker) throws Exception {
		logger.info("read");
		
		model.addAttribute("read", service.read(boardVO.getNo()));
		model.addAttribute("pageMaker", pageMaker);
		
		return "/freeboard/read";
	}
	
	// 게시글 수정(GET)
	@RequestMapping(value="update", method=RequestMethod.GET)
	public void update(Model model, BoardVO boardVO) throws Exception{
				
		// 수정하기 위한 값을 가지고 감.
		model.addAttribute("read", service.read(boardVO.getNo()));

	}
	
	// 게시글 수정(POST)
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String Update(BoardVO boardVO) throws Exception{
		return "redirect:/freeboard/read?no="+boardVO.getNo();
	}
	
	// 게시글 삭제
	@RequestMapping(value="delete", method=RequestMethod.GET)
	public String delete(@RequestParam("no") int no, RedirectAttributes rttr) throws Exception {
		logger.info("remove");
		
		
		return "redirect:/freeboard/index";
	}

}
