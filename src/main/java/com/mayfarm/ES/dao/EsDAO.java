package com.mayfarm.ES.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.mayfarm.ES.vo.Criteria;
import com.mayfarm.ES.vo.EsVO;

@Repository
public class EsDAO {
	
	@Inject
	private RestHighLevelClient restClient;
	
	Gson gson = new Gson();
	
	/**
	 * 통합 검색 리쿼스트 호출 및 검색결과 반환
	 * 
	 * @param str
	 * @return 통합 검색 결과
	 * @throws IOException
	 */
	public MultiSearchResponse TGET(String[] str, Criteria cri) throws IOException {
		
		// 통합검색을 위한 Multisearch 초기화
		MultiSearchRequest multiSarRequest = new MultiSearchRequest();
		
		// JTBC
		SearchRequest searchRequst_jtbc = getJtbcRequest(cri, str[0]);
		multiSarRequest.add(searchRequst_jtbc);
		// KBS
		SearchRequest searchRequst_kbs = getKbsRequest(cri, str[0]);
		multiSarRequest.add(searchRequst_kbs);
		// MBC
		SearchRequest searchRequst_mbc = getMbcRequest(cri, str[0]);
		multiSarRequest.add(searchRequst_mbc);
		
		return restClient.msearch(multiSarRequest, RequestOptions.DEFAULT);
		
		// 카테고리 (해당 카테고리에서만 검색을 원할경우)
//		if(category !=null && category.trim().equals("") ) {
//			TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("violt_ctgr_cd_nm", category);
//			query.must(termQueryBuilder);
//			MatchQueryBuilder category = QueryBuilders.matchQuery("violt_ctgr_cd_nm", str);
//			query.should(category);
//		}
	
	}
	/**
	 * JTBC 기사 리퀘스트 호출 및 검색결과 반환
	 * @param str
	 * @param cri
	 * @return
	 * @throws IOException
	 */
	public SearchResponse JGET(String[] str, Criteria cri) throws IOException {
		SearchRequest searchRequest = getJtbcRequest(cri, str[0]);
		return restClient.search(searchRequest, RequestOptions.DEFAULT);
	}
	
	/**
	 * MBC 기사 리퀘스트 호출 및 검색결과 반환
	 * @param str
	 * @param cri
	 * @return
	 * @throws IOException
	 */
	public SearchResponse MGET(String[] str, Criteria cri) throws IOException {
		SearchRequest searchRequest = getMbcRequest(cri, str[0]);
		return restClient.search(searchRequest, RequestOptions.DEFAULT);
	}
	
	/**
	 * KBS 기사 리퀘스트 호출 및 검색결과 반환
	 * @param str
	 * @param cri
	 * @return
	 * @throws IOException
	 */
	public SearchResponse KGET(String[] str, Criteria cri) throws IOException {
		SearchRequest searchRequest = getKbsRequest(cri, str[0]);
		return restClient.search(searchRequest, RequestOptions.DEFAULT);
	}
	
	/**
	 * JTBC 기사 리퀘스트 생성 및 반환
	 * @param cri
	 * @param str
	 * @return JTBC 기사 리퀘스트
	 */
	private SearchRequest getJtbcRequest(Criteria cri, String str) {
		
		// 값 세팅
		int page = cri.getPage();
		int listSize = cri.getPerPageNum();
		int from = (page == 1) ? 0 : listSize * (page - 1);
		
		// 엘라스틱 초기화
		// 밑에 SearchRequest에 뭘 넣어 줘야 할지 모르겠음...
		SearchRequest searchRequest = new SearchRequest("data");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		// must 내부의 and 조건절 추가를 위한 추가 bool 쿼리 선언
		BoolQueryBuilder semiboolQueryBuilder = QueryBuilders.boolQuery();
		
		// JTBC가 무조건 들어간 카테고리만 검색
		TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("violt_ctgr_cd_nm","JTBC");
		boolQueryBuilder.must(termQueryBuilder);

		// title
		MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("violt_cas_nm", str);
		boolQueryBuilder.must(semiboolQueryBuilder.should(matchQueryBuilder));
		
		// Content
		MatchQueryBuilder matchQueryBuilder2 = QueryBuilders.matchQuery("violt_cas_cn", str);
		boolQueryBuilder.must(semiboolQueryBuilder.should(matchQueryBuilder2));
		
		// 정렬 ( 이름순으로 정렬, Default는 정확도순)
		//searchSourceBuilder.sort(new FieldSortBuilder("no").order(SortOrder.ASC));
		searchSourceBuilder.sort(new FieldSortBuilder("_score").order(SortOrder.DESC));
		
		searchSourceBuilder.query(boolQueryBuilder);
		searchSourceBuilder.from(from);
		searchSourceBuilder.size(listSize);
		searchSourceBuilder.highlighter(makeHighLightField());
		System.out.println(searchSourceBuilder.toString());
		searchRequest.source(searchSourceBuilder);
		return searchRequest;
	}
	
	/**
	 * KBS 기사 리퀘스트 생성 및 반환
	 * @param cri
	 * @param str
	 * @return KBS 기사 리퀘스트
	 */
	private SearchRequest getKbsRequest(Criteria cri, String str) {
			
		// 값 세팅
		int page = cri.getPage();
		int listSize = cri.getPerPageNum();
		int from = (page == 1) ? 0 : listSize * (page - 1);
		
		// 엘라스틱 초기화
		// 밑에 SearchRequest에 뭘 넣어 줘야 할지 모르겠음...
		SearchRequest searchRequest = new SearchRequest("data");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		// must 내부의 and 조건절 추가를 위한 추가 bool 쿼리 선언
		BoolQueryBuilder semiboolQueryBuilder = QueryBuilders.boolQuery();
		
		// KBS가 무조건 들어간 카테고리만 검색
		TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("violt_ctgr_cd_nm","KBS");
		boolQueryBuilder.must(termQueryBuilder);

		// title
		MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("violt_cas_nm", str);
		boolQueryBuilder.must(semiboolQueryBuilder.should(matchQueryBuilder));
		
		// Content
		MatchQueryBuilder matchQueryBuilder2 = QueryBuilders.matchQuery("violt_cas_cn", str);
		boolQueryBuilder.must(semiboolQueryBuilder.should(matchQueryBuilder2));
		
		// 정렬 ( 이름순으로 정렬, Default는 정확도순)
		//searchSourceBuilder.sort(new FieldSortBuilder("no").order(SortOrder.ASC));
		searchSourceBuilder.sort(new FieldSortBuilder("_score").order(SortOrder.DESC));
		
		searchSourceBuilder.query(boolQueryBuilder);
		searchSourceBuilder.from(from);
		searchSourceBuilder.size(listSize);
		searchSourceBuilder.highlighter(makeHighLightField());
//		System.out.println(searchSourceBuilder.toString());
		searchRequest.source(searchSourceBuilder);
		return searchRequest;
	}
	
	/**
	 * MBC 기사 리퀘스트 및 반환
	 * @param cri
	 * @param str
	 * @return MBC 기사 리퀘스트
	 */
	private SearchRequest getMbcRequest(Criteria cri, String str) {
	
		// 값 세팅
		int page = cri.getPage();
		int listSize = cri.getPerPageNum();
		int from = (page == 1) ? 0 : listSize * (page - 1);
		
		// 엘라스틱 초기화
		// 밑에 SearchRequest에 뭘 넣어 줘야 할지 모르겠음...
		SearchRequest searchRequest = new SearchRequest("data");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		// must 내부의 and 조건절 추가를 위한 추가 bool 쿼리 선언
		BoolQueryBuilder semiboolQueryBuilder = QueryBuilders.boolQuery();
				
		// MBC가 무조건 들어간 카테고리만 검색
		TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("violt_ctgr_cd_nm","MBC");
		boolQueryBuilder.must(termQueryBuilder);
	
		// title
		MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("violt_cas_nm", str);
		boolQueryBuilder.must(semiboolQueryBuilder.should(matchQueryBuilder));
		
		// Content
		MatchQueryBuilder matchQueryBuilder2 = QueryBuilders.matchQuery("violt_cas_cn", str);
		boolQueryBuilder.must(semiboolQueryBuilder.should(matchQueryBuilder2));
		
		// 정렬 ( 이름순으로 정렬, Default는 정확도순)
		//searchSourceBuilder.sort(new FieldSortBuilder("no").order(SortOrder.ASC));
		searchSourceBuilder.sort(new FieldSortBuilder("_score").order(SortOrder.DESC));
		
		searchSourceBuilder.query(boolQueryBuilder);
		searchSourceBuilder.from(from);
		searchSourceBuilder.size(listSize);
		searchSourceBuilder.highlighter(makeHighLightField());
//		System.out.println(searchSourceBuilder.toString());
		searchRequest.source(searchSourceBuilder);
		return searchRequest;
	}	
	
	public SearchResponse READ(int no) throws IOException {
		SearchRequest searchRequest = new SearchRequest("data");
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		
		MatchQueryBuilder query = QueryBuilders.matchQuery("no",  no);
		sourceBuilder.query(query);
		searchRequest.source(sourceBuilder);
		
		return restClient.search(searchRequest, RequestOptions.DEFAULT);
		
	}
	
	public void DELETE(String targetIndex) throws IOException {
		
		DeleteRequest deleteRequest = new DeleteRequest("data", targetIndex);
		
		restClient.delete(deleteRequest, RequestOptions.DEFAULT);
	}
	
	/**
	 * 하이라이트 필드
	 * @return 하이라이트 빌더
	 */
	
	private HighlightBuilder makeHighLightField() {
		HighlightBuilder highlightBuilder = new HighlightBuilder()
				.preTags("<strong style ='font-size:1.0em; color: red;'>")
				.postTags("</strong>")
				.field("*");
		
		
		return highlightBuilder;
		
	}	
}
