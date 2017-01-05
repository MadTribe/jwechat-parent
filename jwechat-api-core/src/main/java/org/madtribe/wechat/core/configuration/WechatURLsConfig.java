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

	
	public String getTokenUrlforAppIdAndAppSecret() {
		return tokenUrlforAppIdAndAppSecret;
	}


	public String getSendMessageUrlforAccessToken() {
		return sendMessageUrlforAccessToken;
	}


	
	
}
