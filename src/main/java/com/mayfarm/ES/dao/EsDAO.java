package com.mayfarm.ES.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.lucene.search.TermQuery;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.search.MatchQuery;
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
	 * data 인덱스에서 검색
	 * 제목, 내용, 카테고리에서 검색을 시작
	 * 카테고리는 keyword로 full-text로 입력을 해야함.
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public MultiSearchResponse TGET(String str, Criteria cri) throws IOException {
		
		// 페이지네이션
		int page = cri.getPage();
		int listSize = cri.getPerPageNum();
		int from = (page == 1) ? 0 : listSize * (page - 1);
		// 데이터를 반환활 리스트 선언
		List<EsVO> json = new ArrayList<EsVO>();
		
		// 엘라스틱 서치 초기화
		SearchRequest searchRequest = new SearchRequest("data");
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		
		// bool쿼리 선언
		BoolQueryBuilder query = QueryBuilders.boolQuery();
		
		// 이하 검색할 테이블을 선언하여 bool 쿼리에 넣음.
		// 제목
		MatchQueryBuilder firstname = QueryBuilders.matchQuery("violt_cas_nm", str);
		query.should(firstname);
		
		// 내용
		MatchQueryBuilder lastname = QueryBuilders.matchQuery("violt_cas_cn", str);
		query.should(lastname);
		
		// 카테고리 (해당 카테고리에서만 검색을 원할경우)
//		if(category !=null && category.trim().equals("") ) {
//			TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("violt_ctgr_cd_nm", category);
//			query.must(termQueryBuilder);
//			MatchQueryBuilder category = QueryBuilders.matchQuery("violt_ctgr_cd_nm", str);
//			query.should(category);
//		}
		
		
		// 통합검색을 위한 Multisearch 초기화
		MultiSearchRequest multiSarRequest = new MultiSearchRequest();
		
		// JTBC
		SearchRequest searchRequst_jtbc = getJtbcRequest(cri, str);
		SearchRequest searchRequst_kbs = getKbsRequest(cri, str);
		SearchRequest searchRequst_mbc = getMbcRequest(cri, str);
		
		
		
		
//		sourceBuilder.query(query);
//		sourceBuilder.sort(new FieldSortBuilder("date").order(SortOrder.ASC));
//		sourceBuilder.from(from);
//		sourceBuilder.size(listSize);	// Default 10개
//		System.out.println(sourceBuilder.toString());
//		searchRequest.source(sourceBuilder);
		multiSarRequest.add(searchRequst_jtbc);
		multiSarRequest.add(searchRequst_kbs);
		multiSarRequest.add(searchRequst_mbc);
		return restClient.msearch(multiSarRequest, RequestOptions.DEFAULT);
//		SearchHit[] hits = response.getHits().getHits();
		
//		for (SearchHit hit : hits) {
//			EsVO esVO = gson.fromJson(hit.getSourceAsString(), EsVO.class);
//			json.add(esVO);
//		}
//		return json;
		
	}
	
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
		
		// JTBC가 무조건 들어간 카테고리만 검색
		TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("violt_ctgr_cd_nm","JTBC");
		boolQueryBuilder.must(termQueryBuilder);

		// title
		MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("violt_cas_nm", str);
		boolQueryBuilder.should(matchQueryBuilder);
		
		MatchQueryBuilder matchQueryBuilder2 = QueryBuilders.matchQuery("violt_cas_cn", str);
		boolQueryBuilder.should(matchQueryBuilder2);
		
		searchSourceBuilder.query(boolQueryBuilder);
		searchSourceBuilder.from(from);
		searchSourceBuilder.size(listSize);
		System.out.println(searchSourceBuilder.toString());
		searchRequest.source(searchSourceBuilder);
		return searchRequest;
	}
	
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
		
		// JTBC가 무조건 들어간 카테고리만 검색
		TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("violt_ctgr_cd_nm","KBS");
		boolQueryBuilder.must(termQueryBuilder);

		// title
		MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("violt_cas_nm", str);
		boolQueryBuilder.should(matchQueryBuilder);
		
		MatchQueryBuilder matchQueryBuilder2 = QueryBuilders.matchQuery("violt_cas_cn", str);
		boolQueryBuilder.should(matchQueryBuilder2);
		
		searchSourceBuilder.query(boolQueryBuilder);
		searchSourceBuilder.from(from);
		searchSourceBuilder.size(listSize);
		System.out.println(searchSourceBuilder.toString());
		searchRequest.source(searchSourceBuilder);
		return searchRequest;
	}

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
	
	// JTBC가 무조건 들어간 카테고리만 검색
	TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("violt_ctgr_cd_nm","MBC");
	boolQueryBuilder.must(termQueryBuilder);

	// title
	MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("violt_cas_nm", str);
	boolQueryBuilder.should(matchQueryBuilder);
	
	MatchQueryBuilder matchQueryBuilder2 = QueryBuilders.matchQuery("violt_cas_cn", str);
	boolQueryBuilder.should(matchQueryBuilder2);
	
	searchSourceBuilder.query(boolQueryBuilder);
	searchSourceBuilder.from(from);
	searchSourceBuilder.size(listSize);
	System.out.println(searchSourceBuilder.toString());
	searchRequest.source(searchSourceBuilder);
	return searchRequest;
}
	
	
	
	public List<EsVO> GET(List<String> oss) throws IOException {
		
		List<EsVO> json = new ArrayList<EsVO>();
		
		SearchRequest searchRequest = new SearchRequest("data");
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		
		BoolQueryBuilder query = QueryBuilders.boolQuery();
		for (String str : oss) {
			// 제목
			MatchQueryBuilder firstname = QueryBuilders.matchQuery("violt_cas_nm", str);
			query.should(firstname);
			
			// 내용
			MatchQueryBuilder lastname = QueryBuilders.matchQuery("violt_cas_cn", str);
			query.should(lastname);
			
			// 카테고리
			MatchQueryBuilder category = QueryBuilders.matchQuery("violt_ctgr_cd_nm", str);
			query.should(category);
		}
		
		sourceBuilder.query(query);
		sourceBuilder.highlighter();
		sourceBuilder.sort(new FieldSortBuilder("date").order(SortOrder.ASC));
		sourceBuilder.from(0);
		sourceBuilder.size(10);	// Default 10개
		searchRequest.source(sourceBuilder);
		SearchResponse response = restClient.search(searchRequest, RequestOptions.DEFAULT);
		SearchHit[] hits = response.getHits().getHits();
		
		for (SearchHit hit : hits) {
			EsVO esVO = gson.fromJson(hit.getSourceAsString(), EsVO.class);
			json.add(esVO);
		}
		return json;
		
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
}
