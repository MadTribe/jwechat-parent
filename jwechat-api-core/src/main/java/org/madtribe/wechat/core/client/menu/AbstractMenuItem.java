package org.madtribe.wechat.core.client.menu;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AbstractMenuItem {

	@Valid
    @NotNull
	@JsonProperty("name")
	private String name;

	public AbstractMenuItem(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	
}
