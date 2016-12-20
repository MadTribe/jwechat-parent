package org.madtribe.wechat.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;
import org.madtribe.wechat.core.configuration.WeChatConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by paul.smout on 08/04/2016.
 */
public class WechatExampleConfig extends Configuration {
    @NotEmpty
    private String version;

    @Valid
    @NotNull
    @JsonProperty
    private WeChatConfiguration weChatConfiguration;

    @Valid
    @NotNull
    @JsonProperty
    private String publicAddress;

    @Valid
    @NotNull
    @JsonProperty
    private String publicPath;

    public String getVersion() {
        return version;
    }

    public WeChatConfiguration getWeChatToken() {
        return weChatConfiguration;
    }

    public WeChatConfiguration getWeChatConfiguration() {
        return weChatConfiguration;
    }

    public String getPublicAddress() {
        return publicAddress;
    }

    public String getPublicPath() {
        return publicPath;
    }
}
