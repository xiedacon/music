package cn.xiedacon.write.service;

import cn.xiedacon.model.Song;
import cn.xiedacon.model.SongMenu;

public interface SongMenu_SongGLService {

	String selectIdBySongIdAndSongMenuId(String songId, String songMenuId);

	void insert(SongMenu songMenu, Song song);

}