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
@XmlRootElement
public class InboundRequest<X extends InboundPayload> {
    private static final Logger LOGGER = LoggerFactory.getLogger(InboundRequest.class);

    @XmlElement
    private long id;
    @XmlElement
    private String sender;
    @XmlElement
    private String recipient;
    @XmlElement
    private LocalDateTime createTime;
    @XmlElement
    private InboundPayload payload;

    public InboundRequest(String raw){
        LOGGER.debug("Message Received {}", raw);

    }

    /**
     * Gets the unique id of this message.
     */
    long getId() {
        return 0;
    }

    /**
     * @return the senders openid
     */
    String getSender() {
        return null;
    }

    /**
     * @return the openid of the recipient
     */
    String getRecipient() {
        return null;
    }

    /**
     * Gets when this message was created.
     */
    LocalDateTime getCreateTime() {
        return null;
    }

    /**
     * @return the message payload.
     */
    InboundPayload getPayload() {
        return null;
    }


}
