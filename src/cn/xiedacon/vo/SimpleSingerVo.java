package cn.xiedacon.vo;

public class SimpleSingerVo {

	private String id;
	private String name;
	private String icon;
	private String userId;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "SimpleSingerVo [id=" + id + ", name=" + name + ", icon=" + icon + ", userId=" + userId + "]";
	}
}
