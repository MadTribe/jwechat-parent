package org.madtribe.wechat.service.container;


import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.setup.Environment;
import org.madtribe.wechat.service.WechatExampleConfig;

/**
 * Generic Guice setup class.
 * Just like the application class itself, this should not be used in other apps.
 */
public final class GuiceMain {
    private  Injector instance;

    public GuiceMain(Environment environment,
                     final WechatExampleConfig configuration) throws ClassNotFoundException {
        init( configuration);
    }

    private synchronized  void init(final WechatExampleConfig configuration) throws ClassNotFoundException {
        if (instance != null) {
            return;
        }

        final WeChatModule weChatModule = new WeChatModule( configuration);
        instance = Guice.createInjector(weChatModule);
    }


    public  <T> T get(Class<T> clazz) {
        return instance.getInstance(clazz);
    }
}
