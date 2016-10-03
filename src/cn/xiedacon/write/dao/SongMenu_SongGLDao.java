package cn.xiedacon.write.dao;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.SongMenu_SongGL;

public interface SongMenu_SongGLDao {

	String selectIdBySongIdAndSongMenuId(@Param("songId") String songId, @Param("songMenuId") String songMenuId);

	void insert(SongMenu_SongGL songMenu_SongGL);

}
