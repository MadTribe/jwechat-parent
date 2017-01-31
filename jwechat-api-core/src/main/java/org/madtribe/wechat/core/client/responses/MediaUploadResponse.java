package org.madtribe.wechat.core.client.responses;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.madtribe.wechat.core.client.messages.MediaType;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * {"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
 * @author paulsmout
 * 
 *
 */
public class MediaUploadResponse {

	@Valid
    @NotNull
	@JsonProperty("type")
	private MediaType type;
	
	@Valid
    @NotNull
	@JsonProperty("media_id")
	private String mediaId;
	
	@Valid
    @NotNull
	@JsonProperty("created_at")
	private Long createdAt;
	

	public MediaUploadResponse() {
		super();
	}

	public MediaUploadResponse(MediaType type, String mediaId, Long createdAt) {
		super();
		this.type = type;
		this.mediaId = mediaId;
		this.createdAt = createdAt;
	}

	public MediaType getType() {
		return type;
	}

	public String getMediaId() {
		return mediaId;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	@Override
	public String toString() {
		return "MediaUploadResponse [type=" + type + ", mediaId=" + mediaId + ", createdAt=" + createdAt + "]";
	}
	
	
}
