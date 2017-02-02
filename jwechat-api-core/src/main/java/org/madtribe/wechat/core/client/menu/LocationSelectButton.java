package org.madtribe.wechat.core.client.menu;

public class LocationSelectButton extends AbstractKeyedButton {

	public LocationSelectButton(String name, String key) {
		super(ButtonType.location_select, name);
		this.key = key;
	}

}
