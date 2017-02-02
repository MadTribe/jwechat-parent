package org.madtribe.wechat.core.client.menu;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubMenu extends AbstractMenuItem {

	@Valid
    @NotNull
	@JsonProperty("sub_button")
    private List<AbstractMenuItem> subButtons;
	
	public SubMenu(String name, List<AbstractMenuItem> subButtons ) {
		super(name);
		this.subButtons = subButtons;
	}

	public List<AbstractMenuItem> getSubButtons() {
		return subButtons;
	}

	
}
