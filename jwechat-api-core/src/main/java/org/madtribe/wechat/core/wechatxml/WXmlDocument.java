package org.madtribe.wechat.core.wechatxml;

import java.util.function.Consumer;

public interface WXmlDocument {

	WXmlDocument cdata(String text);

	WXmlDocument number(Number num);

	WXmlDocument el(String name, Consumer<WXmlDocument> buildChildren);

	String toXMLish();

}
