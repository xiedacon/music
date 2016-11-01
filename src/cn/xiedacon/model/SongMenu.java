package cn.xiedacon.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SongMenu {

	// 本表数据
	// 常用
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
	// 记录相关
	private Integer collectionNum;
	private Integer shareNum;
	private Integer commentNum;
	private Integer playNum;
	// 可见性相关
	private Boolean visible;

	// 关联表数据
	// R
	private List<SongMenuSecondTag> songMenuSecondTagList;
	// CUD（谁拥有谁维护）
	private List<SongMenu_SongMenuTagGL> songMenu_SongMenuTagGLList;

	public List<SongMenu_SongMenuTagGL> getSongMenu_SongMenuTagGLList() {
		return songMenu_SongMenuTagGLList;
	}

	public void setSongMenu_SongMenuTagGLList(List<SongMenu_SongMenuTagGL> songMenu_SongMenuTagGLList) {
		this.songMenu_SongMenuTagGLList = songMenu_SongMenuTagGLList;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
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

}
