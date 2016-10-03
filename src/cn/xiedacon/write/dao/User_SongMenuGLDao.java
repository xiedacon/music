package cn.xiedacon.write.dao;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.User_SongMenuGL;

public interface User_SongMenuGLDao {

	String selectIdBySongMenuIdAndCollectorId(@Param("songMenuId") String songMenuId,
			@Param("collectorId") String collectorId);

	void insert(User_SongMenuGL gl);

}
