package org.madtribe.wechat.core.client.menu;

public class PicWeixinButton extends AbstractKeyedButton {

	public PicWeixinButton(String name, String key) {
		super(ButtonType.pic_weixin, name);
		this.key = key;
	}

}
