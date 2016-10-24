package cn.xiedacon.model;

public class SongList_SongGL {

	private String id;
	private String songId;
	private String songName;
	private Integer rank;
	private String songListId;
	private String songListName;
	private Integer rankChange;

	public SongList_SongGL() {
		super();
	}

	public SongList_SongGL(String id, String songId, Integer rank, String songListId, Integer rankChange) {
		super();
		this.id = id;
		this.songId = songId;
		this.rank = rank;
		this.songListId = songListId;
		this.rankChange = rankChange;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getSongListName() {
		return songListName;
	}

	public void setSongListName(String songListName) {
		this.songListName = songListName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
