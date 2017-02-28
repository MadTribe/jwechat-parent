package org.madtribe.wechat.core.configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WechatURLsConfig {

	@Valid
    @NotNull
	@JsonProperty
    private String tokenUrlforAppIdAndAppSecret = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

	@Valid
    @NotNull
	@JsonProperty
    private String sendMessageUrlforAccessToken = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";

	@Valid
    @NotNull
	@JsonProperty
    private String addMaterialforAccessTokenAndType = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s";

	private String createMenuForAccessToken =  "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";


	private String oAuth2URLForAppIdRedirectUriScopeAndState = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";


	private String oAuth2AccessTokenURLForAppIdSecretAndCode = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

	private String userDetailsForAccessTokenOpenIdandLang = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=%s";

	
	
	public String getTokenUrlforAppIdAndAppSecret() {
		return tokenUrlforAppIdAndAppSecret;
	}


	public String getSendMessageUrlforAccessToken() {
		return sendMessageUrlforAccessToken;
	}


	public String getAddMaterialforAccessTokenAndType() {
		return addMaterialforAccessTokenAndType;
	}


	public String getCreateMenuForAccessToken() {
		return createMenuForAccessToken;
	}

	public String getOAuth2URLForAppIdRedirectUriScopeAndStateL() {
		return oAuth2URLForAppIdRedirectUriScopeAndState ;
	}


	public String getOAuth2AccessTokenURLForAppIdSecretAndCode() {
		return oAuth2AccessTokenURLForAppIdSecretAndCode;
  }
    
	public String getUserDetailsForAccessTokenOpenIdandLang() {
		return userDetailsForAccessTokenOpenIdandLang;

	}
	

}
