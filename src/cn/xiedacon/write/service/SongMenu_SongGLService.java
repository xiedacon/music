package cn.xiedacon.write.service;

import cn.xiedacon.model.SongMenu_SongGL;

public interface SongMenu_SongGLService {

	SongMenu_SongGL selectExistBySongIdAndSongMenuId(String songId, String songMenuId);

	void insert(SongMenu_SongGL songMenu_SongGL);

}
