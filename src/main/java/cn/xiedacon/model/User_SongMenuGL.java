package cn.xiedacon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class User_SongMenuGL {

	// 本表数据
	private String userId;
	private String songMenuId;

	// 关联数据
	private Integer collectionNum;
	private Integer songMenuNum;

	public User_SongMenuGL() {
		super();
	}

	public User_SongMenuGL(String userId, String songMenuId, Integer collectionNum, Integer songMenuNum) {
		super();
		this.userId = userId;
		this.songMenuId = songMenuId;
		this.collectionNum = collectionNum;
		this.songMenuNum = songMenuNum;
	}

	public Integer getCollectionNum() {
		return collectionNum;
	}

	public void setCollectionNum(Integer collectionNum) {
		this.collectionNum = collectionNum;
	}

	public Integer getSongMenuNum() {
		return songMenuNum;
	}

	public void setSongMenuNum(Integer songMenuNum) {
		this.songMenuNum = songMenuNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSongMenuId() {
		return songMenuId;
	}

	public void setSongMenuId(String songMenuId) {
		this.songMenuId = songMenuId;
	}

}
