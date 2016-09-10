package cn.xiedacon.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

public interface SongGLDao {

	void insertSongMenuGL(@Param("id") String id, @Param("songId") String songId,
			@Param("songMenuId") String songMenuId, @Param("time") Date time);

	String selectIdBySongIdAndSongMenuId(@Param("songId") String songId, @Param("songMenuId") String songMenuId);

}
