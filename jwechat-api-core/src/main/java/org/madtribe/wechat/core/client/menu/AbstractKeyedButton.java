package org.madtribe.wechat.core.client.menu;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractKeyedButton extends AbstractButton {

	@Valid
	@NotNull
	@JsonProperty("key")
	protected String key;

	public AbstractKeyedButton(ButtonType type, String name) {
		super(type, name);
	}

}