package com.mayfarm.freeboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mayfarm.freeboard.dao.BoardDAO;
import com.mayfarm.freeboard.vo.BoardVO;

@Service
public class BoardService {

	@Inject
	private BoardDAO dao;
	
	public List<BoardVO> list() throws Exception {
		System.out.println("dao에게 명령어 전달!!");
		return dao.list();
	}
}
