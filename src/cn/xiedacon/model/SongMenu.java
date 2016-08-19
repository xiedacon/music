package cn.xiedacon.model;

import java.util.Date;

public class SongMenu {

	private String id;
	private String name;
	private String icon;
	private String creatorName;
	private Date createTime;
	private Integer songNum;
	private String introduction;
	private Boolean isPublic;
	private String creatorId;
	private String userId;

	private Integer collectionNum;
	private Integer shareNum;
	private Integer commentNum;
	private Integer playNum;

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getCollectionNum() {
		return collectionNum;
	}

	public void setCollectionNum(Integer collectionNum) {
		this.collectionNum = collectionNum;
	}

	public Integer getShareNum() {
		return shareNum;
	}

	public void setShareNum(Integer shareNum) {
		this.shareNum = shareNum;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getPlayNum() {
		return playNum;
	}

	public void setPlayNum(Integer playNum) {
		this.playNum = playNum;
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

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSongNum() {
		return songNum;
	}

	public void setSongNum(Integer songNum) {
		this.songNum = songNum;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Override
	public String toString() {
		return "SongMenu [id=" + id + ", name=" + name + ", icon=" + icon + ", creatorName=" + creatorName
				+ ", createTime=" + createTime + ", songNum=" + songNum + ", introduction=" + introduction
				+ ", isPublic=" + isPublic + ", creatorId=" + creatorId + ", userId=" + userId + ", collectionNum="
				+ collectionNum + ", shareNum=" + shareNum + ", commentNum=" + commentNum + ", playNum=" + playNum
				+ "]";
	}
}
