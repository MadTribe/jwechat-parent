package org.madtribe.wechat.core.resources;


import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.hash.Hashing;

import com.google.inject.Inject;
import org.madtribe.wechat.core.client.jsapi.JSAPITicket;
import org.madtribe.wechat.core.client.jsapi.JSAPITicketProvider;
import org.madtribe.wechat.core.configuration.WeChatConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;



/**
 * Created by paul.smout on 02/04/2016.
 */
@Path("/wxjssig")
@Produces("application/xml;charset=UTF-8")
@Consumes({MediaType.APPLICATION_JSON})
public class JSAPIEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSAPIEntryPoint.class);

    public static final Response NOT_AUTHORIZED = Response.status(Response.Status.UNAUTHORIZED).build();
    public static final Response NOT_ACCEPTABLE = Response.status(Response.Status.NOT_ACCEPTABLE).build();
    public static final String INVALID_CONFIGURATION = "invalid";

    @Inject
    private WeChatConfiguration weChatConfiguration;

    @Inject
    private JSAPITicketProvider jsapiTicketProvider;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response signURL(@QueryParam("url") final Optional<String> url,
                            @QueryParam("timestamp") final Optional<Long> timestamp,
                            @QueryParam("nonce") final Optional<String> nonce) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (url.isPresent()){
            java.util.Optional<JSAPITicket> optionalTicket = jsapiTicketProvider.obtainToken();
            if (optionalTicket.isPresent()){
                Optional<String> sig = generateSignature(nonce,
                                                         optionalTicket,
                                                         timestamp,
                                                         url);
                return Response.ok(sig).build();
            } else {
                return NOT_AUTHORIZED;
            }

        } else {
            return NOT_ACCEPTABLE;
        }
    }

    private Optional<String> generateSignature(final Optional<String> nonceOptional,
                                      final java.util.Optional<JSAPITicket> jsapiTicketOptional,
                                      final Optional<Long> timestampOptional,
                                      final Optional<String> urlOptional) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        LOGGER.debug("signature = {} timestamp={} nonce={}", jsapiTicketOptional.isPresent(), timestampOptional.isPresent(), nonceOptional.isPresent());
        Optional<String> signature = Optional.absent();

        if (jsapiTicketOptional.isPresent() &&
                timestampOptional.isPresent() &&
                    nonceOptional.isPresent() &&
                        urlOptional.isPresent()) {

            String jsapiTicket = "jsapi_ticket=" + jsapiTicketOptional.get().getTicket();
            String nonce = "&noncestr=" + nonceOptional.get();
            String timestamp = "&timestamp=" + timestampOptional.get();
            String url = "&url=" + urlOptional.get();

            // Concatenate the result in alphabetical order
            String concatenated = jsapiTicket + nonce + timestamp + url;

            System.err.println("Concatenated is {" + concatenated + "}") ;
            LOGGER.info("Concatenated is {}", concatenated);

            // Generate SHA1 of concatenation
            signature = com.google.common.base.Optional.of(sha1OfString(concatenated));

        }
        LOGGER.debug("signature valid is {}", signature);
        return signature;
    }

    private String sha1OfString(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return Hashing.sha1().hashString( input, Charsets.UTF_8 ).toString();
    }


}
