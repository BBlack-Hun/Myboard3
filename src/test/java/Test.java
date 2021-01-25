import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml", 
		"file:src/main/webapp/WEB-INF/spring/security-context.xml", 
		"file:src/main/webapp/WEB-INF/spring/elastic-context.xml" 
		}) 

public class Test { 
	
	@Autowired 
	private RestHighLevelClient restClient; 
	
	@org.junit.Test
	public void search() { 
		SearchRequest searchRequest = new SearchRequest("bank"); // 조회하고자 하는 index 
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); 
//		sourceBuilder.query(QueryBuilders.matchQuery("gender", "f")); // match 조건 1 
//		sourceBuilder.query(QueryBuilders.matchQuery("v_keyword", "hyundai")); // match 조건 2 
//		sourceBuilder.query(QueryBuilders.matchQuery("v_status", "C030")); // match 조건 3 
//		sourceBuilder.sort(new FieldSortBuilder("balance").order(SortOrder.DESC)); // 정렬 조건 
		sourceBuilder.from(10); // paging from 
		sourceBuilder.size(10); // paging to 
		searchRequest.source(sourceBuilder); 
		
		try { 
			SearchResponse response = restClient.search(searchRequest, RequestOptions.DEFAULT); // 조회
			System.out.println("response string \n" + response); // 응답 출력 
			LinkedList<Map<String, Object>> list = new LinkedList<>(); // 가공 
			SearchHit[] hits = response.getHits().getHits(); // 데이터만 추출 
			for(SearchHit hit : hits) { 
				// 검색된 결과 중 hit의 데이터를 Map으로하여 sourceMap에 key - value 형태로 저장.
				Map<String, Object> sourceMap = hit.getSourceAsMap(); // hit의 내용을 Map으로 받아옴
				// 
				list.add(sourceMap);
				System.out.println(sourceMap);
				System.out.println(sourceMap.get("firstname"));	// 각 sourceMap에서 firstname에 해당하는 값을 출력
				}
			// list의 갯수를 출력 (지금 넣을 줄 몰라서... 0개 ㅎㅎ)
			System.out.println(list.size());
			
			} catch (IOException e) { 
				e.printStackTrace(); 
				} 
		} 
}