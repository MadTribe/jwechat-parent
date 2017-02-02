package org.madtribe.wechat.core.client.menu;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractButton extends AbstractMenuItem{
	public AbstractButton(ButtonType type,String name) {
		super(name);
		this.type = type;
	}

	@Valid
    @NotNull
	@JsonProperty("type")
	private ButtonType type;

	
	public ButtonType getType() {
		return type;
	}


}
