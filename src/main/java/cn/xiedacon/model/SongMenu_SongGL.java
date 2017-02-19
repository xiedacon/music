package cn.xiedacon.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SongMenu_SongGL {

	// 本表数据
	private String songMenuId;
	private String songId;
	private Date time;

	// 其他表数据（需要与主要数据在同一事务中完成）
	private Integer songNum;

	public SongMenu_SongGL() {
		super();
	}

	public SongMenu_SongGL(String songMenuId, String songId, Date time, Integer songNum) {
		super();
		this.songMenuId = songMenuId;
		this.songId = songId;
		this.time = time;
		this.songNum = songNum;
	}

	public Integer getSongNum() {
		return songNum;
	}

	public void setSongNum(Integer songNum) {
		this.songNum = songNum;
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
