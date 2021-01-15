package com.mayfarm.freeboard.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mayfarm.freeboard.vo.BoardVO;

@Repository
public class BoardDAO {
	
	@Inject
	private SqlSession sql;
	
	public List<BoardVO> list() throws Exception {
		return sql.selectList("boardMapper.list");
		
	}
	
}
