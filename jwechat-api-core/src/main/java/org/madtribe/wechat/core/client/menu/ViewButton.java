package org.madtribe.wechat.core.client.menu;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ViewButton extends AbstractButton {

	@Valid
    @NotNull
	@JsonProperty("url")
	private String url;
	
	public ViewButton(String name, String url) {
		super(ButtonType.view, name);
		this.url = url;
	}

}
