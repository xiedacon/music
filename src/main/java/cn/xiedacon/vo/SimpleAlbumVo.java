package cn.xiedacon.vo;

import java.util.Date;

public class SimpleAlbumVo {

	private String id;
	private String name;
	private String icon;
	private String singerName;
	private String singerId;
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSingerId() {
		return singerId;
	}

	public void setSingerId(String singerId) {
		this.singerId = singerId;
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

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

	@Override
	public String toString() {
		return "SimpleAlbumVo [id=" + id + ", name=" + name + ", icon=" + icon + ", singerName=" + singerName
				+ ", singerId=" + singerId + ", createTime=" + createTime + "]";
	}

}
