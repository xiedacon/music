package cn.xiedacon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SongMenu_SongMenuTagGL {

	// 本表数据
	private String songMenuId;
	private String songMenuTagId;

	public SongMenu_SongMenuTagGL() {
		super();
	}

	public SongMenu_SongMenuTagGL(String songMenuId, String songMenuTagId) {
		super();
		this.songMenuId = songMenuId;
		this.songMenuTagId = songMenuTagId;
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
