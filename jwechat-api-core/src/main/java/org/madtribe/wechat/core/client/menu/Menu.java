package org.madtribe.wechat.core.client.menu;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Menu {
	@Valid
    @NotNull
	@JsonProperty("button")
    private List<AbstractMenuItem> items;

	public Menu(List<AbstractMenuItem> items) {
		super();
		this.items = items;
	}
	
	

}
