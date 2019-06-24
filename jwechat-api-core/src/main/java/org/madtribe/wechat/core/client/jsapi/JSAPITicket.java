package org.madtribe.wechat.core.client.jsapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class JSAPITicket {
	
	@Valid
    @NotNull
	@JsonProperty("errcode")
    private int errorCode;

	@Valid
    @NotNull
    @JsonProperty("errmsg")
    private String errorMessgae;

	@Valid
	@NotNull
	@JsonProperty("ticket")
	private String ticket;

	@Valid
	@NotNull
	@JsonProperty("expires_in")
	private Long expiresInSeconds;

	private long creationTime = new Date().getTime();
	
	/**
	 * Returns +ve number if in the future -ve if in the past.
	 * @return
	 */
	public long timeInMillisToExpiry(){
		return creationTime + (expiresInSeconds * 1000) - new Date().getTime();
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMessgae() {
		return errorMessgae;
	}

	public String getTicket() {
		return ticket;
	}

	public Long getExpiresInSeconds() {
		return expiresInSeconds;
	}

	public long getCreationTime() {
		return creationTime;
	}
}
