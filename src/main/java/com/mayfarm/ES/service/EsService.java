package com.mayfarm.ES.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.MultiSearchResponse.Item;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mayfarm.ES.dao.EsDAO;
import com.mayfarm.ES.vo.Criteria;
import com.mayfarm.ES.vo.EsVO;

@Service
public class EsService {
	
	@Inject
	private EsDAO dao;
	
	Gson gson = new Gson();

	public Map<String,Object> TGET(String str, Criteria cri) throws IOException {
		
		// 검색 결과 갯수
		
		// 반환을 위한 Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		MultiSearchResponse multiSearchResponse = dao.TGET(str, cri);
		Item items[] = multiSearchResponse.getResponses();
		
		// JTBC
		SearchResponse searchResponse_jtbc = items[0].getResponse();
		// KBS
		SearchResponse searchResponse_kbs = items[1].getResponse();
		// MBC
		SearchResponse searchResponse_mbc = items[2].getResponse();
		
		// 결과 정제_JTBC
		List<EsVO> list_jtbc = new ArrayList<EsVO>();
		for (SearchHit hit : searchResponse_jtbc.getHits().getHits()) {
			EsVO esVO = gson.fromJson(hit.getSourceAsString(), EsVO.class);
			list_jtbc.add(esVO);
		}
		// 결과 정제_KBS
		List<EsVO> list_kbs = new ArrayList<EsVO>();
		for (SearchHit hit : searchResponse_kbs.getHits().getHits()) {
			EsVO esVO = gson.fromJson(hit.getSourceAsString(), EsVO.class);
			list_kbs.add(esVO);
		}
		// 결과 정제_MBC
		List<EsVO> list_mbc = new ArrayList<EsVO>();
		for (SearchHit hit : searchResponse_mbc.getHits().getHits()) {
			EsVO esVO = gson.fromJson(hit.getSourceAsString(), EsVO.class);
			list_mbc.add(esVO);
		}
		// 전체 검색 결과 수
		long total = 0;
		for (Item item : items) {
			SearchResponse response = item.getResponse();
			total += response.getHits().getTotalHits().value;
		}
		
		returnMap.put("JTBC", list_jtbc);
		returnMap.put("KBS", list_kbs);
		returnMap.put("MBC", list_mbc);
		returnMap.put("total", total);
		return returnMap;
	}
	
	public List<EsVO> AGET(List<String> oss) throws IOException {
		return dao.GET(oss);
	}
	
	public Map<String, Object> READ(int no) throws IOException {
				
		// 쿼리 결과를 response에 받아옴.
		SearchResponse response = dao.READ(no);
		
		// 데이터 추출
		SearchHit[] hits = response.getHits().getHits();
		Map<String, Object> hit = hits[0].getSourceAsMap();
		
		return hit;
	}
	
	public void DELETE(String targetIndex) throws IOException {
		dao.DELETE(targetIndex);
	}
}
