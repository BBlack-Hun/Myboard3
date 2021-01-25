import java.io.IOException;

import javax.inject.Inject;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml", 
		"file:src/main/webapp/WEB-INF/spring/security-context.xml", 
		"file:src/main/webapp/WEB-INF/spring/elastic-context.xml" 
		}) 

public class DeleteTest {
	
	@Inject
	private RestHighLevelClient restClient;
	
	@Test
	public void Delete() {
		DeleteRequest deleteRequest = new DeleteRequest("my_index", "1");
		try {
			DeleteResponse deleteResponse = restClient.delete(deleteRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
