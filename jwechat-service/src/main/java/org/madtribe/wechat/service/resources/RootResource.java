package org.madtribe.wechat.service.resources;

import org.madtribe.wechat.core.messages.inbound.request.InboundRequest;
import org.madtribe.wechat.core.messages.inbound.response.InboundResponse;
import org.madtribe.wechat.service.WechatExampleConfig;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

/**
 * Created by paul.smout on 02/04/2016.
 */
@Path("/")
@Produces("text/plain")
@Consumes({MediaType.TEXT_XML, MediaType.APPLICATION_XML})
public class RootResource {

    private WechatExampleConfig config;

    public RootResource(WechatExampleConfig configuration) {
        this.config = configuration;
    }

    @GET
    public String validateService() {
        return "JWechat Example service version: " + config.getVersion();
    }

}
