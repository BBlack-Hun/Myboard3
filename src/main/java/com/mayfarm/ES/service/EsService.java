package com.mayfarm.ES.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Service;

import com.mayfarm.ES.dao.EsDAO;

@Service
public class EsService {
	
	@Inject
	private EsDAO dao;
	
	public List <Map<String, Object>> GET() throws IOException {
		// 포팅하기 위한 list 선언
		List <Map<String, Object>> arrList = new ArrayList<>();
		
		// 쿼리 결과를 response에 받아옴
		SearchResponse response = dao.GET();
		
		// 데이터 추출
		SearchHit[] hits = response.getHits().getHits();
		
		// 추출된 데이터를 list로 변환하여 반환
		for (SearchHit hit : hits) {
			Map<String, Object> sourceMap = hit.getSourceAsMap();
			arrList.add(sourceMap);
		}
		return arrList;
	}
	
	public List <Map<String, Object>> GET(String str) throws IOException {
		// 포팅하기 위한 list 선언
		List <Map<String, Object>> arrList = new ArrayList<>();
		
		// 쿼리 결과를 response에 받아옴
		SearchResponse response = dao.GET(str);
		
		// 데이터 추출
		SearchHit[] hits = response.getHits().getHits();
		
		// 추출된 데이터를 list로 변환하여 반환
		for (SearchHit hit : hits) {
			Map<String, Object> sourceMap = hit.getSourceAsMap();
			arrList.add(sourceMap);
		}
		return arrList;
	}
	
	public Map<String, Object> READ(int no) throws IOException {
				
		// 쿼리 결과를 response에 받아옴.
		SearchResponse response = dao.READ(no);
		
		// 데이터 추출
		SearchHit[] hits = response.getHits().getHits();
		Map<String, Object> hit = hits[0].getSourceAsMap();
		System.out.println("json" + hit);
		System.out.println("추출 중...." + hit.get("firstname"));
		
		// 추출된 데이터를 list로 변환하여 반환
//		for (SearchHit hit : hits) {
//			Map<String, Object> sourceMap = hit.getSourceAsMap();
//			System.out.println(sourceMap.get(0));
//			arrList.add(sourceMap);
//			
//		}
		
		return hit;
	}
	
	public void DELETE(String targetIndex) throws IOException {
		dao.DELETE(targetIndex);
	}
}
