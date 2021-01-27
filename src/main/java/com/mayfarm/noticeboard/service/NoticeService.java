package com.mayfarm.noticeboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mayfarm.freeboard.vo.Criteria;
import com.mayfarm.noticeboard.dao.NoticeDAO;
import com.mayfarm.noticeboard.vo.NoticeVO;

@Service
public class NoticeService {
	
	@Inject
	private NoticeDAO dao;
	
	/**
	 * 게시물 목록 조회
	 * dao에 있는 list()함수를 실행
	 * @return
	 */
	public List<NoticeVO> list() throws Exception {
		System.out.println("dao에게 명령어 전달!");
		return dao.list();
	}
	
	public Integer totalCnt(Criteria cri) throws Exception {
		return dao.totalCnt(cri);
	}
}
