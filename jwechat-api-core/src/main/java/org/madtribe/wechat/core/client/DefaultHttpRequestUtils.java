package org.madtribe.wechat.core.client;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.madtribe.wechat.core.client.errors.WeChatResponseError;
import org.madtribe.wechat.core.client.responses.StatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;

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
	public <ENTITY_TYPE> Optional<ENTITY_TYPE> getAsObject(String url, Class<ENTITY_TYPE> class1) throws WeChatResponseError {
		LOGGER.info("GET request to {}", url);
		HttpGet httpGet = new HttpGet(url);
		Optional<ENTITY_TYPE> ret = Optional.empty();
		try {
			try(CloseableHttpResponse response1 = httpClient.execute(httpGet)) {
				
				HttpEntity httpEntity = response1.getEntity();
				String entiryAsString =  EntityUtils.toString(httpEntity);
				LOGGER.info("GET response is {}  --- status line is {}", entiryAsString, response1.getStatusLine().toString());
				
		
				ret  = parseEntityToObject(class1, ret, entiryAsString);
				
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
    public <ENTITY_TYPE> Optional<ENTITY_TYPE> postObject(String url, Object object, Class<ENTITY_TYPE> class1) throws WeChatResponseError {
    	Optional<ENTITY_TYPE> ret = Optional.empty();
		try {
			String jsonInString = objectMapper.writeValueAsString(object);
			
			LOGGER.debug("Sending {} to {}", jsonInString, url);
			
			HttpEntity httpEntity = new StringEntity(jsonInString);
			
			HttpPost httpPost = new HttpPost(url);

			httpPost.setEntity(httpEntity);


			try(CloseableHttpResponse response2 = httpClient.execute(httpPost)) {
			    
			    HttpEntity entity2 = response2.getEntity();
			    String entiryAsString =  EntityUtils.toString(entity2);
				LOGGER.info("GET response is {}  --- status line is {}", entiryAsString, response2.getStatusLine().toString());
				ret = parseEntityToObject(class1, ret, entiryAsString);
			    EntityUtils.consume(entity2);
			}
		} catch (IOException e) {
			LOGGER.error("Error sending get request",e);
		}
		
		return ret;
	}
	
	
	@Override
	public <ENTITY_TYPE> Optional<ENTITY_TYPE> postFile(String url, InputStream inputStream, String fieldName, Class<ENTITY_TYPE> class1 ) throws WeChatResponseError{

		Optional<ENTITY_TYPE> ret = Optional.empty();
		
		
			
		try {
			
			byte[] bytes = ByteStreams.toByteArray(inputStream);
			
			LOGGER.debug("Sending to {} fieldName {}", url, fieldName);
			
			HttpEntity httpEntity = MultipartEntityBuilder.create()
					.addBinaryBody(fieldName, bytes,  ContentType.MULTIPART_FORM_DATA,
				            "DEFAULT.jpg")
					.build();
			
			LOGGER.debug("built entity");
			
			HttpPost httpPost = new HttpPost(url);

			httpPost.setEntity(httpEntity);

			LOGGER.debug("constructed post request");
			try(CloseableHttpResponse response2 = httpClient.execute(httpPost)) {
			    
				LOGGER.debug("executed post request {}", response2);
				
			    HttpEntity entity2 = response2.getEntity();
			    String entiryAsString =  EntityUtils.toString(entity2);
				LOGGER.info("POST response is {}  --- status line is {}", entiryAsString, response2.getStatusLine().toString());
				
				ret = parseEntityToObject(class1, ret, entiryAsString);
				
				
			    EntityUtils.consume(entity2);
			}
		}  catch (IOException e) {
			LOGGER.error("Error sending Post request",e);
		}
									
		return ret;		
		
	}


	private <ENTITY_TYPE> Optional<ENTITY_TYPE> parseEntityToObject(Class<ENTITY_TYPE> class1,
			Optional<ENTITY_TYPE> ret, String entityAsString)
			throws IOException, JsonParseException, JsonMappingException, WeChatResponseError {
		try {
			LOGGER.debug("Attempting to parse response {} as {}",entityAsString, class1);
			ENTITY_TYPE entityObject = objectMapper.readValue(entityAsString, class1);
		
			ret = Optional.of(entityObject);
		} catch (JsonMappingException jsonMappingException){
			LOGGER.error("Error occurred will try to parse respose as StatusResponse");
			StatusResponse errorEntity = objectMapper.readValue(entityAsString, StatusResponse.class);
			WeChatResponseError exception = new WeChatResponseError(errorEntity);
			throw exception;
		}
		return ret;
	}
	
}
