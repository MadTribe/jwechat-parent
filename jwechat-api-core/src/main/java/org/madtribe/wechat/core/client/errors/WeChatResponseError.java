package org.madtribe.wechat.core.client.errors;

import org.madtribe.wechat.core.client.responses.ErrorResponse;

public class WeChatResponseError extends Exception{
	private static final long serialVersionUID = 1L;

	private ErrorResponse errorResponse;

	public WeChatResponseError(ErrorResponse errorResponse) {
		super();
		this.errorResponse = errorResponse;
	}

	public WeChatResponseError() {
		super();
	}

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}

	@Override
	public String toString() {
		return "WeChatResponseError [errorResponse=" + errorResponse + "]";
	}
	
	
	
}
