package com.mayfarm.ES.service;

import java.io.IOException;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.MultiSearchResponse.Item;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;

import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
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
	/**
	 * 통합 검색 서비스
	 * 검색 DAO 호출 및 하이라이트 처리, 검색결과 반환
	 * @param str
	 * @param cri
	 * @return
	 * @throws IOException
	 */
	public Map<String,Object> TGET(String[] str, Criteria cri) throws IOException {
		
		Map<String, Object> semiTotal = new HashMap<String, Object>();
		
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
		EsVO esVO = new EsVO();
		for (SearchHit hit : searchResponse_jtbc.getHits().getHits()) {
			// 기존 문장에 highlight를 씌운 태그를 덧 씌운다.
			esVO = coverHighLightFields2(hit);
			
			list_jtbc.add(esVO);
		}
		// 결과 정제_KBS
		List<EsVO> list_kbs = new ArrayList<EsVO>();
		for (SearchHit hit : searchResponse_kbs.getHits().getHits()) {
			
			esVO = coverHighLightFields2(hit);
			
			list_kbs.add(esVO);
		}
		// 결과 정제_MBC
		List<EsVO> list_mbc = new ArrayList<EsVO>();
		for (SearchHit hit : searchResponse_mbc.getHits().getHits()) {
			
			esVO = coverHighLightFields2(hit);
			
			list_mbc.add(esVO);
		}
		// 전체 검색 결과 수
		long total = 0;
		int cnt = 0;
		for (Item item : items) {
			long stotal = 0;
			SearchResponse response = item.getResponse();
			total += response.getHits().getTotalHits().value;
			// 각 카테고리별 검색 결과수
			stotal = response.getHits().getTotalHits().value;
			// Map에 put 
			semiTotal.put("item"+cnt,stotal);
			cnt +=1;
			
			
		}
		
		returnMap.put("stotal", semiTotal);
		returnMap.put("JTBC", list_jtbc);
		returnMap.put("KBS", list_kbs);
		returnMap.put("MBC", list_mbc);
		returnMap.put("total", total);
		return returnMap;
	}
	
	/**
	 * JTBC 기사 카테고리 검색 서비스
	 * JTBC 기사 검색 DAO 호출 및 하이라이트 처리, 검색 결과 반환
	 * @param str
	 * @param cri
	 * @return JTBC 기사 검색결과
	 * @throws IOException
	 */
	public Map<String,Object> JGET(String[] str, Criteria cri) throws IOException {
		
		// 반환을 위한 Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		//
		SearchResponse searchResponse = dao.JGET(str, cri);
		// 결과 정제_JTBC
		List<Map<String, Object>> list_jtbc = new ArrayList<Map<String, Object>>();
		EsVO esVO = new EsVO();
		for (SearchHit hit : searchResponse.getHits().getHits()) {
			// 기존 문장에 highlight를 씌운 태그를 덧 씌운다.
			Map<String, Object> hhit = coverHighLightFields(hit);
			
//			esVO = gson.fromJson(hit.getSourceAsString(), EsVO.class);
			list_jtbc.add(hhit);
		}
		
		long total = searchResponse.getHits().getTotalHits().value;
		
		returnMap.put("total", total);
		returnMap.put("JTBC", list_jtbc);

		return returnMap;
	}
	
	/**
	 * KBS 기사 카테고리 검색 서비스
	 * KBS 기사 검색 DAO 호출 및 하이라이트 처리, 검색 결과 반환
	 * @param str
	 * @param cri
	 * @return KBS 기사 검색 결과
	 * @throws IOException
	 */
	public Map<String,Object> KGET(String[] str, Criteria cri) throws IOException {
		
		// 반환을 위한 Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		//
		SearchResponse searchResponse = dao.KGET(str, cri);
		// 결과 정제_KBS
		List<Map<String, Object>> list_ktbc = new ArrayList<Map<String, Object>>();
		EsVO esVO = new EsVO();
		for (SearchHit hit : searchResponse.getHits().getHits()) {
			// 기존 문장에 highlight를 씌운 태그를 덧 씌운다.
			Map<String, Object> hhit = coverHighLightFields(hit);
			
//			esVO = gson.fromJson(hit.getSourceAsString(), EsVO.class);
			list_ktbc.add(hhit);
		}
		
		// 검색한 KBS 기사 갯수
		long total = searchResponse.getHits().getTotalHits().value;
		
		returnMap.put("total", total);
		returnMap.put("KBS", list_ktbc);

		return returnMap;
	}
	
	/**
	 * MBC 기사 카테고리 검색 서비스
	 * MBC 기사 검색 DAO 호출 및 하이라이트 처리, 검색 결과 반환
	 * @param str
	 * @param cri
	 * @return MBC 기사 검색 결과
	 * @throws IOException
	 */
	public Map<String,Object> MGET(String[] str, Criteria cri) throws IOException {
		
		// 반환을 위한 Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		//
		SearchResponse searchResponse = dao.MGET(str, cri);
		// 결과 정제_MBC
		List<Map<String, Object>> list_mtbc = new ArrayList<Map<String, Object>>();
		EsVO esVO = new EsVO();
		for (SearchHit hit : searchResponse.getHits().getHits()) {
			// 기존 문장에 highlight를 씌운 태그를 덧 씌운다.
			Map<String, Object> hhit = coverHighLightFields(hit);
			
//			esVO = gson.fromJson(hit.getSourceAsString(), EsVO.class);
			list_mtbc.add(hhit);
		}
		
		long total = searchResponse.getHits().getTotalHits().value;
		
		returnMap.put("total", total);
		returnMap.put("MBC", list_mtbc);

		return returnMap;
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
	
	public Map<String, Object> coverHighLightFields(SearchHit hit) {
		// json형태의 hit을 map형태로 변환
		Map<String, Object> sourceMap = hit.getSourceAsMap();
		// 포팅을 위한 선언
		EsVO esVO = new EsVO();
		// 이때 sourceMap은 Map 형태임...
		// hit에서 highlight 필드에서 태그만 출력
		for (String field : hit.getHighlightFields().keySet()) {
			HighlightField highlightField = hit.getHighlightFields().get(field);
			Text[] texts = highlightField.getFragments();
			String value = texts[0].string();
			// 해당하는 필드가 있으면, sourceMap의 필드와 교체
			sourceMap.put(field, value);
		}
		return sourceMap;
		
	}
	
	public EsVO coverHighLightFields2(SearchHit hit) {
		// json형태의 hit을 map형태로 변환
		Map<String, Object> sourceMap = hit.getSourceAsMap();
		// 포팅을 위한 선언
		EsVO esVO = new EsVO();
		// 이때 sourceMap은 Map 형태임...
		// hit에서 highlight 필드에서 태그만 출력
		for (String field : hit.getHighlightFields().keySet()) {
			HighlightField highlightField = hit.getHighlightFields().get(field);
			Text[] texts = highlightField.getFragments();
			String value = texts[0].string();
			// 해당하는 필드가 있으면, sourceMap의 필드와 교체
			sourceMap.put(field, value);
		}
		esVO.setNo(sourceMap.get("no").toString());
		esVO.setViolt_cas_nm(sourceMap.get("violt_cas_nm").toString());
		esVO.setViolt_cas_cn(sourceMap.get("violt_cas_cn").toString());
		esVO.setViolt_ctgr_cd(sourceMap.get("violt_ctgr_cd").toString());
		esVO.setViolt_ctgr_cd_nm(sourceMap.get("violt_ctgr_cd_nm").toString());
		esVO.setDate(sourceMap.get("date").toString());

		return esVO;
		
	}
}
