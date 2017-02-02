package org.madtribe.wechat.core.client.menu;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractMediaButton extends AbstractButton {

	@Valid
	@NotNull
	@JsonProperty("media_id")
	protected String mediaId;

	public AbstractMediaButton(ButtonType type, String name, String mediaId) {
		super(type, name);
		this.mediaId = mediaId;
	}

}