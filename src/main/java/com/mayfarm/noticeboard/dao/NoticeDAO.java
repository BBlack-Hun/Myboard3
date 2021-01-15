package com.mayfarm.noticeboard.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mayfarm.noticeboard.vo.NoticeVO;

@Repository
public class NoticeDAO {
	
	@Inject
	private SqlSession sql;
	
	// 게시물 목록 조회
	public List<NoticeVO> list() throws Exception {
		System.out.println("sql에서 명령문을 실행");
		
		return sql.selectList("noticeMapper.list");
	}
}
