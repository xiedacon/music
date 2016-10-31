package cn.xiedacon.write.dao;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.SongMenu_SongGL;

public interface SongMenu_SongGLDao {

	void insert(SongMenu_SongGL songMenu_SongGL);

	SongMenu_SongGL selectExistBySongIdAndSongMenuId(@Param("songId") String songId,
			@Param("songMenuId") String songMenuId);

}
