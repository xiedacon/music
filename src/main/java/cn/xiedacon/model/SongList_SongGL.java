package cn.xiedacon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SongList_SongGL {

	// 本表数据
	private String songId;
	private Integer rank;
	private String songListId;
	private Integer rankChange;

	// 其他数据
	private String songName;
	private String songListName;

	public String getSongListName() {
		return songListName;
	}

	public void setSongListName(String songListName) {
		this.songListName = songListName;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public SongList_SongGL() {
		super();
	}

	public SongList_SongGL(String songId, Integer rank, String songListId, Integer rankChange) {
		super();
		this.songId = songId;
		this.rank = rank;
		this.songListId = songListId;
		this.rankChange = rankChange;
	}

	public String getSongId() {
		return songId;
	}

	public void setSongId(String songId) {
		this.songId = songId;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getSongListId() {
		return songListId;
	}

	public void setSongListId(String songListId) {
		this.songListId = songListId;
	}

	public Integer getRankChange() {
		return rankChange;
	}

	public void setRankChange(Integer rankChange) {
		this.rankChange = rankChange;
	}

}
