package org.madtribe.wechat.core.client.accesstoken;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessToken {
	
	@Valid
    @NotNull
	@JsonProperty("access_token")
    private String accessTokenString;

	@Valid
    @NotNull
    @JsonProperty("expires_in")
    private long expiresInSeconds;
	
	private long creationTime = new Date().getTime();
	
	/**
	 * Returns +ve number if in the future -ve if in the past.
	 * @return
	 */
	public long timeInMillisToExpiry(){
		return creationTime + (expiresInSeconds * 1000) - new Date().getTime();
	}

	public String getAccessTokenString() {
		return accessTokenString;
	}

	@Override
	public String toString() {
		return "AccessToken [accessTokenString=" + accessTokenString + ", expiresInSeconds=" + expiresInSeconds
				+ ", creationTime=" + creationTime + "]";
	}
	

}
