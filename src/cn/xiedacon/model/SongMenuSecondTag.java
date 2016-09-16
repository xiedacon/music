package cn.xiedacon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SongMenuSecondTag {

	private String id;
	private String name;
	private SongMenuFirstTag songMenuFirstTag;

	public SongMenuSecondTag() {

	}

	public SongMenuSecondTag(String id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "SongMenuSecondTag [id=" + id + ", name=" + name + ", songMenuFirstTag=" + songMenuFirstTag + "]";
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

	public SongMenuFirstTag getSongMenuFirstTag() {
		return songMenuFirstTag;
	}

	public void setSongMenuFirstTag(SongMenuFirstTag songMenuFirstTag) {
		this.songMenuFirstTag = songMenuFirstTag;
	}
}
