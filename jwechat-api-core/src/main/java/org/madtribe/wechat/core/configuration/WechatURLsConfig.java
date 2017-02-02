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
	

}
