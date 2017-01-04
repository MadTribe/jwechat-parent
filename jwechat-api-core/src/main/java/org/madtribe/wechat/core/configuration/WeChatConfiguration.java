package org.madtribe.wechat.core.configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by paul.smout on 08/04/2016.
 */
public class WeChatConfiguration {

	@Valid
    @NotNull
	@JsonProperty
    private String weChatToken;

	@Valid
    @NotNull
	@JsonProperty
    private String weChatAppId;

	@Valid
    @NotNull
	@JsonProperty
    private String weChatAppSecret;
	
	@Valid
    @NotNull
	@JsonProperty
	WechatURLsConfig wechatURLsConfig = new WechatURLsConfig();

	
    public String getWeChatToken() {
        return weChatToken;
    }

    public String getWeChatAppId() {
        return weChatAppId;
    }

    public String getWeChatAppSecret() {
        return weChatAppSecret;
    }
    
    public WechatURLsConfig getWechatURLsConfig() {
		return wechatURLsConfig;
	}
}
