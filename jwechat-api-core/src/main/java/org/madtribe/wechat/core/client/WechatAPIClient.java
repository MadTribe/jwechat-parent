package org.madtribe.wechat.core.client;

import java.io.InputStream;
import java.util.Optional;

import javax.inject.Inject;

import org.madtribe.wechat.core.client.accesstoken.AccessToken;
import org.madtribe.wechat.core.client.errors.WeChatResponseError;
import org.madtribe.wechat.core.client.menu.Menu;
import org.madtribe.wechat.core.client.messages.CustomerServiceMessage;
import org.madtribe.wechat.core.client.messages.MediaType;
import org.madtribe.wechat.core.client.responses.MediaUploadResponse;
import org.madtribe.wechat.core.client.responses.StatusResponse;
import org.madtribe.wechat.core.client.responses.UserDetails;
import org.madtribe.wechat.core.configuration.WeChatConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WechatAPIClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultHttpRequestUtils.class);
	@Inject
	private IHttpRequestUtils httpClient;
	
	@Inject
	private WeChatConfiguration config;	

	public Optional<AccessToken> requestNewAccessToken() throws WeChatResponseError {
		String tokenUrlforAppIdAndAppSecret = config.getWechatURLsConfig().getTokenUrlforAppIdAndAppSecret();
		String url = String.format(tokenUrlforAppIdAndAppSecret, config.getWeChatAppId(), config.getWeChatAppSecret());
		Optional<AccessToken> accesToken = httpClient.getAsObject(url,AccessToken.class);
		return accesToken;
	}

	public Optional<StatusResponse> sendMessage(Optional<AccessToken> accessToken, CustomerServiceMessage message) throws WeChatResponseError {
		Optional<StatusResponse>  ret = Optional.empty();
		if (accessToken.isPresent()){
			String sendMessageUrlforAccessToken = config.getWechatURLsConfig().getSendMessageUrlforAccessToken();
			String url = String.format(sendMessageUrlforAccessToken, accessToken.get().getAccessTokenString());
			ret = httpClient.postObject(url,message, StatusResponse.class);
		} else {
			LOGGER.error("No Access token provided. Giving up.");
		}
		return ret;
	}
	
	public Optional<MediaUploadResponse> uploadTemporaryMedia(Optional<AccessToken> accessToken, InputStream inputStream, MediaType type) throws WeChatResponseError{
		String addMaterialUrlForAccessTokenAndType = config.getWechatURLsConfig().getAddMaterialforAccessTokenAndType();
		String url = String.format(addMaterialUrlForAccessTokenAndType, accessToken.get().getAccessTokenString(), type.name() );
		
		Optional<MediaUploadResponse> response = httpClient.postFile( url, inputStream, "media", MediaUploadResponse.class);
		return response;
	}

	public Optional<StatusResponse> createMenu(Optional<AccessToken> accessToken, Menu menu) throws WeChatResponseError {
		Optional<StatusResponse>  ret = Optional.empty();
		if (accessToken.isPresent()){
			String createMenuForAccessToken = config.getWechatURLsConfig().getCreateMenuForAccessToken();
			String url = String.format(createMenuForAccessToken, accessToken.get().getAccessTokenString() );
			return httpClient.postObject(url, menu, StatusResponse.class);
		} else {
			LOGGER.error("No Access token provided. Giving up.");
		}
		return ret;
	}

	public Optional<UserDetails> getUserDetails(Optional<AccessToken> accessToken, String openId) throws WeChatResponseError {
		Optional<UserDetails>  ret = Optional.empty();
		if (accessToken.isPresent()){
			String userDetailsForAccessTokenOpenIdandLang = config.getWechatURLsConfig().getUserDetailsForAccessTokenOpenIdandLang();
			String url = String.format(userDetailsForAccessTokenOpenIdandLang, accessToken.get().getAccessTokenString(), openId, "zh_CN"  );
			return httpClient.getAsObject(url, UserDetails.class);
		} else {
			LOGGER.error("No Access token provided. Giving up.");
		}
		return ret;
	}
	
}
