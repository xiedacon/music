package cn.xiedacon.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class SongMenu_SongGL {

	private String id;
	private String songMenuId;
	private String songId;
	private Date time;

	public SongMenu_SongGL(String id, String songMenuId, String songId, Date time) {
		super();
		this.id = id;
		this.songMenuId = songMenuId;
		this.songId = songId;
		this.time = time;
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

	public String getSongId() {
		return songId;
	}

	public void setSongId(String songId) {
		this.songId = songId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
