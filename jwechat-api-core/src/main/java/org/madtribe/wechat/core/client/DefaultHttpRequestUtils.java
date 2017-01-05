package org.madtribe.wechat.core.client;

import java.io.IOException;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.madtribe.wechat.core.client.accesstoken.AccessToken;
import org.madtribe.wechat.core.client.accesstoken.DefaultAccessTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Simple wrapper for an HttpClient 
 * @author paulsmout
 *
 */
public class DefaultHttpRequestUtils implements IHttpRequestUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultHttpRequestUtils.class);
	
	private CloseableHttpClient httpClient;
	private ObjectMapper objectMapper;
	
	public DefaultHttpRequestUtils(){
		 httpClient = HttpClients.createDefault();
		 objectMapper = new ObjectMapper();
	}
	

	@Override
	public <ENTITY_TYPE> Optional<ENTITY_TYPE> getAsObject(String url, Class<ENTITY_TYPE> class1) {
		LOGGER.info("GET request to {}", url);
		HttpGet httpGet = new HttpGet(url);
		Optional<ENTITY_TYPE> ret = Optional.empty();
		try {
			try(CloseableHttpResponse response1 = httpClient.execute(httpGet)) {
				
				HttpEntity httpEntity = response1.getEntity();
				String entiryAsString =  EntityUtils.toString(httpEntity);
				LOGGER.info("GET response is {}  --- status line is {}", entiryAsString, response1.getStatusLine().toString());
				
				
				ENTITY_TYPE entityObject = objectMapper.readValue(entiryAsString, class1);
				
				ret = Optional.of(entityObject);
				
				EntityUtils.consume(httpEntity);
				
			} 
		} catch (IOException e) {
			// TODO work out what to do here...
			// throw an error? return Optional.empty 
			// What kind of recovery is possible if we can't get a resource. 
			// if it is an API token then maybe very little, if it is a media object then lots
			LOGGER.error("Error sending get request",e);
		
		}
		return ret;
	}


	@Override
	public void postObject(String url, Object object) {
		//Object to JSON in String
		try {
			String jsonInString = objectMapper.writeValueAsString(object);
			
			LOGGER.debug("Sending {} to {}", jsonInString, url);
			
			HttpEntity httpEntity = new StringEntity(jsonInString);
			
			HttpPost httpPost = new HttpPost(url);

			httpPost.setEntity(httpEntity);


			try(CloseableHttpResponse response2 = httpClient.execute(httpPost)) {
			    
			    HttpEntity entity2 = response2.getEntity();
			    String entiryAsString =  EntityUtils.toString(httpEntity);
				LOGGER.info("GET response is {}  --- status line is {}", entiryAsString, response2.getStatusLine().toString());
				
			    EntityUtils.consume(entity2);
			}
		} catch (IOException e) {
			LOGGER.error("Error sending get request",e);
		}
		
	}
	
}
