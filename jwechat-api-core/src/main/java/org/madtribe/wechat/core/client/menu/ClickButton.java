package org.madtribe.wechat.core.client.menu;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClickButton extends AbstractButton {

	@Valid
    @NotNull
	@JsonProperty("key")
	private String key;
	
	public ClickButton(String name, String key) {
		super(ButtonType.click, name);
		this.key = key;
	}

}
