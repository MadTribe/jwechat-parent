package org.madtribe.wechat.core.client;

import java.io.InputStream;
import java.util.Optional;

import javax.inject.Inject;

import org.madtribe.wechat.core.client.accesstoken.AccessToken;
import org.madtribe.wechat.core.client.accesstoken.DefaultAccessTokenProvider;
import org.madtribe.wechat.core.client.errors.WeChatResponseError;
import org.madtribe.wechat.core.client.messages.CustomerServiceMessage;
import org.madtribe.wechat.core.client.messages.MediaType;
import org.madtribe.wechat.core.client.responses.MediaUploadResponse;


public class WechatAPI {
	@Inject 
	private WechatAPIClient wechatAPIClient;
	
	@Inject 
	private DefaultAccessTokenProvider accessTokenProvider;

	public Optional<AccessToken> getAccessToken() {
		
		return accessTokenProvider.obtainToken();
	}

	public void sendCustomerServiceMessage(CustomerServiceMessage message) throws WeChatResponseError{
		Optional<AccessToken> accessToken = wechatAPIClient.requestNewAccessToken();
		wechatAPIClient.sendMessage(accessToken, message);;
	
	}

	public Optional<MediaUploadResponse> uploadTemporaryMedia(InputStream inputStream, MediaType type) throws WeChatResponseError{
		Optional<AccessToken> accessToken = wechatAPIClient.requestNewAccessToken();
		
		return wechatAPIClient.uploadTemporaryMedia(accessToken, inputStream, type);
	
	}
	
	
}
