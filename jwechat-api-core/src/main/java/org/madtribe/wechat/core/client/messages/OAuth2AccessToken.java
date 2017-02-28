package org.madtribe.wechat.core.client.messages;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OAuth2AccessToken {
	
	@Valid
    @NotNull
    @JsonProperty("access_token")
    private String accessToken;
   
	@Valid
    @NotNull
    @JsonProperty("expires_in")	   
    private int expiresIn;

	@Valid
    @NotNull
    @JsonProperty("refresh_token")	 
    private String refreshToken;
    
    @Valid
    @NotNull
    @JsonProperty("openid")
    private String openid;
    
    @Valid
    @NotNull
    @JsonProperty("scope")
    private String scope;
    

    @Valid
    @NotNull
    @JsonProperty("unionid")
    private String unionid;


	public String getAccessToken() {
		return accessToken;
	}


	public int getExpiresIn() {
		return expiresIn;
	}


	public String getRefreshToken() {
		return refreshToken;
	}


	public String getOpenid() {
		return openid;
	}


	public String getScope() {
		return scope;
	}


	public String getUnionid() {
		return unionid;
	}

	

}
