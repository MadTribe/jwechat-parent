package org.madtribe.wechat.core.client.menu;

public class PicPhotoOrAlbumButton extends AbstractKeyedButton {

	public PicPhotoOrAlbumButton(String name, String key) {
		super(ButtonType.pic_photo_or_album, name);
		this.key = key;
	}

}
