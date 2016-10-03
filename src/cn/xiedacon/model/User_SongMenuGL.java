package cn.xiedacon.model;

public class User_SongMenuGL {

	private String id;
	private String userId;
	private String songMenuId;

	public User_SongMenuGL(String id, String userId, String songMenuId) {
		super();
		this.id = id;
		this.userId = userId;
		this.songMenuId = songMenuId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
