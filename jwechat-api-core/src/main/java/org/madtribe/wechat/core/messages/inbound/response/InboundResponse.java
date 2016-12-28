package org.madtribe.wechat.core.messages.inbound.response;

import java.util.Date;

import org.madtribe.wechat.core.messages.inbound.request.MessagePayload;

/**
 * Created by paul.smout on 02/04/2016.
 */
public class InboundResponse {

	private long createTime;
	private String messageType;
	private String toUserName;
	private MessagePayload payload;
	private String fromUserName;
	
	public InboundResponse(String messageType, String fromUserName, String userName, MessagePayload payload) {
		super();
		this.createTime = new Date().getTime();
		this.messageType = messageType;
		this.toUserName = userName;
		this.payload = payload;
		this.fromUserName = fromUserName;
	}


	public String getToUserName() {
		return toUserName;
	}

	public String getMessageType() {
		return messageType;
	}

	public Number getCreateTime() {
		return this.createTime;
	}

	public MessagePayload getPayload() {
		return payload;
	}

	public String getFromUserName() {
		return fromUserName;
	}


	@Override
	public String toString() {
		return "InboundResponse [createTime=" + createTime + ", messageType=" + messageType + ", toUserName="
				+ toUserName + ", payload=" + payload + ", fromUserName=" + fromUserName + "]";
	}
	
	
	
}
