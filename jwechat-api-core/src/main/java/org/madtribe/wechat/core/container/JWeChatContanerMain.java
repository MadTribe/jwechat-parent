package org.madtribe.wechat.core.container;


import org.madtribe.wechat.core.configuration.WeChatConfiguration;

import com.google.inject.Guice;
import com.google.inject.Injector;



/**
 * Generic Guice setup class.
 * Just like the application class itself, this should not be used in other apps.
 */
public final class JWeChatContanerMain {
    private  Injector instance;

    public JWeChatContanerMain(
                     final WeChatConfiguration configuration) throws ClassNotFoundException {
        init( configuration);
    }

    private synchronized  void init(final WeChatConfiguration configuration) throws ClassNotFoundException {
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
