package org.madtribe.wechat.core.client.menu;

public class ClickButton extends AbstractKeyedButton {

	public ClickButton(String name, String key) {
		super(ButtonType.click, name);
		this.key = key;
	}

}
