package cn.xiedacon.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SongMenu {

	private String id;
	private String name;
	private String icon;
	private String creatorId;
	private String creatorName;
	private String creatorIcon;
	private String userId;
	private Date createTime;
	private String introduction;
	private Integer songNum;

	private Boolean isPublic;

	private Integer collectionNum;
	private Integer shareNum;
	private Integer commentNum;
	private Integer playNum;

	private Boolean visible;

	private List<SongMenuSecondTag> songMenuSecondTagList;

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public List<SongMenuSecondTag> getSongMenuSecondTagList() {
		return songMenuSecondTagList;
	}

	public void setSongMenuSecondTagList(List<SongMenuSecondTag> songMenuSecondTagList) {
		this.songMenuSecondTagList = songMenuSecondTagList;
	}

	public String getCreatorIcon() {
		return creatorIcon;
	}

	public void setCreatorIcon(String creatorIcon) {
		this.creatorIcon = creatorIcon;
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
		return "SongMenu [id=" + id + ", name=" + name + ", icon=" + icon + ", creatorId=" + creatorId
				+ ", creatorName=" + creatorName + ", creatorIcon=" + creatorIcon + ", userId=" + userId
				+ ", createTime=" + createTime + ", introduction=" + introduction + ", songNum=" + songNum
				+ ", isPublic=" + isPublic + ", collectionNum=" + collectionNum + ", shareNum=" + shareNum
				+ ", commentNum=" + commentNum + ", playNum=" + playNum + ", songMenuSecondTagList="
				+ songMenuSecondTagList + "]";
	}
}
