package org.madtribe.wechat.core.resources;


import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.hash.Hashing;
import com.google.inject.Inject;
import org.madtribe.wechat.core.configuration.WeChatConfiguration;
import org.madtribe.wechat.core.messages.inbound.request.InboundRequest;
import org.madtribe.wechat.core.messages.inbound.response.InboundResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.sort;

/**
 * Created by paul.smout on 02/04/2016.
 */
@Path("/wxcallback")
@Produces("application/xml;charset=UTF-8")
@Consumes({MediaType.TEXT_XML, MediaType.APPLICATION_XML})
public class WeChatEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatEntryPoint.class);


    @Inject
    private WeChatConfiguration weChatConfiguration;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String validateService(@QueryParam("signature") final Optional<String> signatureOptional,
                                  @QueryParam("timestamp") final Optional<String> timestampOptional,
                                  @QueryParam("nonce") final Optional<String> nonceOptional,
                                  @QueryParam("echostr") final Optional<String> echoStringOptional) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String response = "invalid";
        LOGGER.debug("signature = {} timestamp={} nonce={} echostr={}", signatureOptional.isPresent(), timestampOptional.isPresent(), nonceOptional.isPresent(), echoStringOptional.isPresent());

        if (signatureOptional.isPresent() &&
                timestampOptional.isPresent() &&
                    nonceOptional.isPresent() &&
                        echoStringOptional.isPresent()) {

            String signature = signatureOptional.get();
            String timestamp = timestampOptional.get();
            String nonce = nonceOptional.get();

            LOGGER.debug("signature = {} timestamp={} nonce={} echostr={} wechatconfig ={}", signature, timestamp, nonce, echoStringOptional.get(), weChatConfiguration.getWeChatToken());

            // Sort Parameters Alphabetically
            List<String> params = Arrays.asList(weChatConfiguration.getWeChatToken(), timestamp, nonce);
            sort(params);

            // Concatenate the result
            String concatenated = params.stream().reduce("", (a, b) -> a + b);

            // Generate SHA1 of concatenation
            String codedString = sha1OfString(concatenated);


            // Compare with signature and if the same return echo string.
            if (codedString.equals(signature)){
                response = echoStringOptional.get();
            }
        }
        return response;
    }

    private String sha1OfString(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return Hashing.sha1().hashString( input, Charsets.UTF_8 ).toString();

    }

    @POST
    public InboundResponse onMessage(final InboundRequest request) {
        return null;
    }


}
