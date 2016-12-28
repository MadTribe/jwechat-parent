package org.madtribe.wechat.core.wechatxml;

import java.util.function.Consumer;

import javax.xml.transform.TransformerException;

/**
 * Wechat XML seems to break the rules of normal XML by having multiple root elements
 * Using a simple StringBuilder approach right now that can wrap a different implementation later. 
 * @author paulsmout
 *
 */
public class WXmlDocumentImpl implements WXmlDocument {
	
	private StringBuilder doc = new StringBuilder();
	
	@Override
	public WXmlDocument cdata(String text) {
		doc.append("<![CDATA[").append(text).append("]]>");

		return this;
	}
	
	@Override
	public WXmlDocument number(Number num) {
		doc.append(num);

		return this;
	}

	@Override
	public WXmlDocument el(String name, Consumer<WXmlDocument> buildChildren) {
		doc.append("<").append(name).append(">");

		if (buildChildren != null) {
			buildChildren.accept(this);
		}

		doc.append("</").append(name).append(">");
		return this;
	}
	
	@Override
	public String toXMLish(){
		return doc.toString();
	}

}
