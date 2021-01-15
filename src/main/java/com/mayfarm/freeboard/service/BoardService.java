package com.mayfarm.freeboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mayfarm.freeboard.dao.BoardDAO;
import com.mayfarm.freeboard.vo.BoardVO;
import com.mayfarm.freeboard.vo.Criteria;

@Service
public class BoardService {

	@Inject
	private BoardDAO dao;
	
	/**
	 * dao로부터 list()란 함수의 결과물인 데이터들을 받아온다.
	 * @return
	 * @throws Exception
	 */
	public List<BoardVO> list(Criteria cri) throws Exception {
		System.out.println("dao에게 명령어 전달!!");
		return dao.list(cri);
	}
	
	public BoardVO read(int no) throws Exception {
		return dao.read(no);
	}
	
	public Integer totalCnt(Criteria cri) throws Exception {
		return dao.totalCnt(cri);
	}
}
