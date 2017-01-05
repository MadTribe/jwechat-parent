package org.madtribe.wechat.core.client.messages;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerServiceMessage {
	@Valid
    @NotNull
	@JsonProperty("touser")
    private String toUser;

	@Valid
    @NotNull
    @JsonProperty("msgtype")
    private String msgtype;

	public CustomerServiceMessage(String toUser, String msgtype) {
		super();
		this.toUser = toUser;
		this.msgtype = msgtype;
	}

	public String getToUser() {
		return toUser;
	}

	public String getMsgtype() {
		return msgtype;
	}
	
	
	

}
