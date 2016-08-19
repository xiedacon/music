package cn.xiedacon.factory;

public enum SongMenuEnum {

	LOVE("喜欢的音乐", "image/default_songMenu_love.jpg"), DEFAULT(null, "image/default_songMenu.jpg");

	SongMenuEnum(String name, String icon) {
		this.name = name;
		this.icon = icon;
	}

	public String name;
	public String icon;
}
