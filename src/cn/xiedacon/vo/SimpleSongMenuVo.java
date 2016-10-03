package cn.xiedacon.vo;

public class SimpleSongMenuVo {

	private String id;
	private String name;
	private String icon;
	private Integer playNum;
	private Integer songNum;
	private String creatorId;
	private String creatorName;
	private String userId;

	public Integer getSongNum() {
		return songNum;
	}

	public void setSongNum(Integer songNum) {
		this.songNum = songNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	@Override
	public String toString() {
		return "SimpleSongMenuVo [id=" + id + ", name=" + name + ", icon=" + icon + ", playNum=" + playNum
				+ ", songNum=" + songNum + ", creatorId=" + creatorId + ", creatorName=" + creatorName + ", userId="
				+ userId + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getPlayNum() {
		return playNum;
	}

	public void setPlayNum(Integer playNum) {
		this.playNum = playNum;
	}
}
