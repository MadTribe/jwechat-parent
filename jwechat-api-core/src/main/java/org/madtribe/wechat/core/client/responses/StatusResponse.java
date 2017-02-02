package org.madtribe.wechat.core.client.responses;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * {"errcode":40004,"errmsg":"invalid media type"}
 * 
 * @author paulsmout
 *
 */
public class StatusResponse {

	@Valid
    @NotNull
	@JsonProperty("errcode")
	private int errCode;
	
	@Valid
    @NotNull
	@JsonProperty("errmsg")
	private String errMessage;

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
	

	public StatusResponse() {
		super();
	}

	public StatusResponse(int errCode, String errMessage) {
		super();
		this.errCode = errCode;
		this.errMessage = errMessage;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	@Override
	public String toString() {
		return "StatusResponse [errCode=" + errCode + ", errMessage=" + errMessage + "]";
	}
	

	

}
