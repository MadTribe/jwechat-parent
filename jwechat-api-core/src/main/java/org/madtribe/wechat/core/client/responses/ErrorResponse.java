package org.madtribe.wechat.core.client.responses;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.madtribe.wechat.core.client.messages.MediaType;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * {"errcode":40004,"errmsg":"invalid media type"}
 * 
 * @author paulsmout
 *
 */
public class ErrorResponse {

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
	

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(int errCode, String errMessage) {
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
		return "ErrorResponse [errCode=" + errCode + ", errMessage=" + errMessage + "]";
	}
	

	

}
