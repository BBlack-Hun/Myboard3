package com.mayfarm.ES.dao;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Repository;

@Repository
public class EsDAO {
	
	@Inject
	private RestHighLevelClient restClient;
	
	// 
	/**
	 * elastic server에 get 명령을 날려 Search.
	 * bank index에서 검색
	 * size는 Default 10;
	 * @return
	 * @throws IOException
	 */
	public SearchResponse GET() throws IOException {
		SearchRequest searchRequest = new SearchRequest("bank");
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.sort(new FieldSortBuilder("account_number").order(SortOrder.DESC));
		sourceBuilder.from(0);
		sourceBuilder.size(1000);	// Default 10개
		searchRequest.source(sourceBuilder);
		
		return restClient.search(searchRequest, RequestOptions.DEFAULT);
	}
	
	public SearchResponse GET(String str) throws IOException {
		System.out.println("값이 넘어 왔는지 확인" + str);
		SearchRequest searchRequest = new SearchRequest("bank");
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		
		BoolQueryBuilder query = QueryBuilders.boolQuery();
		
		//주석 성
		MatchQueryBuilder firstname = QueryBuilders.matchQuery("firstname", str);
		query.should(firstname);
		
		//주석 이름
		MatchQueryBuilder lastname = QueryBuilders.matchQuery("lastname", str);
		query.should(lastname);
		
		sourceBuilder.query(query);
		sourceBuilder.sort(new FieldSortBuilder("account_number").order(SortOrder.DESC));
		sourceBuilder.from(0);
		sourceBuilder.size();	// Default 10개
		searchRequest.source(sourceBuilder);
		
		return restClient.search(searchRequest, RequestOptions.DEFAULT);
	}
}
