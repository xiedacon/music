package cn.xiedacon.vo;

import java.util.Date;
import java.util.List;

public class SongMenuVo {

	private String id;
	private String name;
	private String icon;
	private String creatorId;
	private String creatorName;
	private String creatorIcon;
	private Date createTime;
	private String introduction;
	private String userId;
	private Integer songNum;
	private Integer collectionNum;
	private Integer shareNum;
	private Integer commentNum;
	private Integer playNum;

	private List<SongMenuSecondTagVo> songMenuSecondTagList;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "SongMenuVo [id=" + id + ", name=" + name + ", icon=" + icon + ", creatorId=" + creatorId
				+ ", creatorName=" + creatorName + ", creatorIcon=" + creatorIcon + ", createTime=" + createTime
				+ ", introduction=" + introduction + ", userId=" + userId + ", songNum=" + songNum + ", collectionNum="
				+ collectionNum + ", shareNum=" + shareNum + ", commentNum=" + commentNum + ", playNum=" + playNum
				+ ", songMenuSecondTagList=" + songMenuSecondTagList + "]";
	}

	public String getCreatorIcon() {
		return creatorIcon;
	}

	public void setCreatorIcon(String creatorIcon) {
		this.creatorIcon = creatorIcon;
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

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
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

	public List<SongMenuSecondTagVo> getSongMenuSecondTagList() {
		return songMenuSecondTagList;
	}

	public void setSongMenuSecondTagList(List<SongMenuSecondTagVo> songMenuSecondTagList) {
		this.songMenuSecondTagList = songMenuSecondTagList;
	}

}
