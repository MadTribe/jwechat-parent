package org.madtribe.wechat.core.client.errors;

import org.madtribe.wechat.core.client.responses.StatusResponse;

public class WeChatResponseError extends Exception{
	private static final long serialVersionUID = 1L;

	private StatusResponse errorResponse;

	public WeChatResponseError(StatusResponse errorResponse) {
		super();
		this.errorResponse = errorResponse;
	}

	public WeChatResponseError() {
		super();
	}

	public StatusResponse getErrorResponse() {
		return errorResponse;
	}

	@Override
	public String toString() {
		return "WeChatResponseError [errorResponse=" + errorResponse + "]";
	}
	
	
	
}
