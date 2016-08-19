package cn.xiedacon.model;

public class Singer {

	private String id;
	private String name;
	private String icon;
	private String remark;
	private String introduction;
	private User user;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Singer [id=" + id + ", name=" + name + ", icon=" + icon + ", remark=" + remark + ", introduction="
				+ introduction + ", user=" + user + "]";
	}
	
}
