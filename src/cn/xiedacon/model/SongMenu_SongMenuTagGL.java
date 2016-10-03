package cn.xiedacon.model;

public class SongMenu_SongMenuTagGL {

	private String id;
	private String songMenuId;
	private String songMenuTagId;

	public SongMenu_SongMenuTagGL(String id, String songMenuId, String songMenuTagId) {
		super();
		this.id = id;
		this.songMenuId = songMenuId;
		this.songMenuTagId = songMenuTagId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSongMenuId() {
		return songMenuId;
	}

	public void setSongMenuId(String songMenuId) {
		this.songMenuId = songMenuId;
	}

	public String getSongMenuTagId() {
		return songMenuTagId;
	}

	public void setSongMenuTagId(String songMenuTagId) {
		this.songMenuTagId = songMenuTagId;
	}

}
