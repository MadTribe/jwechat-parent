package org.madtribe.wechat.core.configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WechatURLsConfig {

	@Valid
    @NotNull
	@JsonProperty
    private String tokenUrlforAppIdAndAppSecret = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

	public String getTokenUrlforAppIdAndAppSecret() {
		return tokenUrlforAppIdAndAppSecret;
	}


}
