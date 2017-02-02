package org.madtribe.wechat.core.client.menu;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScanCodePushButton extends AbstractKeyedButton {

	
	public ScanCodePushButton(String name, String key) {
		super(ButtonType.scancode_push, name);
		this.key = key;
	}

}
