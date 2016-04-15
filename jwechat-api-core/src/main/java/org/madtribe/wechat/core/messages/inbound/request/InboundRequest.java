package org.madtribe.wechat.core.messages.inbound.request;

import org.madtribe.wechat.core.messages.inbound.request.InboundPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

/**
 * Created by paul.smout on 02/04/2016.
 */
public class InboundRequest<X extends InboundPayload> {
    private static final Logger LOGGER = LoggerFactory.getLogger(InboundRequest.class);

    private long id;
    private String sender;
    private String recipient;
    private LocalDateTime createTime;
    private InboundPayload payload;

    public InboundRequest(long id, String sender, String recipient, LocalDateTime createTime, InboundPayload payload) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.createTime = createTime;
        this.payload = payload;
    }

    public long getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public InboundPayload getPayload() {
        return payload;
    }
}
