package org.madtribe.wechat.core.client.menu;

public class ScanCodeWaitMsgButton extends AbstractKeyedButton {

	public ScanCodeWaitMsgButton(String name, String key) {
		super(ButtonType.scancode_waitmsg, name);
		this.key = key;
	}

}
