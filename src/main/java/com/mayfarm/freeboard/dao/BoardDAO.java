package com.mayfarm.freeboard.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mayfarm.freeboard.vo.BoardVO;
import com.mayfarm.freeboard.vo.Criteria;

@Repository
public class BoardDAO {
	
	@Inject
	private SqlSession sql;
	/**
	 * mapper에 있는 list의 sql문을 쿼리로 전달후 list에 저장하여 반환값을 받아옴
	 * @return
	 * @throws Exception
	 */
	public List<BoardVO> list(Criteria cri) throws Exception {
		return sql.selectList("boardMapper.list", cri);
		
	}
	
	
	public BoardVO read(int no) throws Exception {
		return sql.selectOne("boardMapper.read", no);
	}
	
	public Integer totalCnt(Criteria cri) throws Exception {
		return sql.selectOne("boardMapper.totalCnt", cri);
	}
}
