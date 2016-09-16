package cn.xiedacon.dao;

import org.apache.ibatis.annotations.Param;

public interface SongMenuGLDao {

	void insert(@Param("id") String id, @Param("collectorId") String collectorId,
			@Param("songMenuId") String songMenuId);

	String selectIdBySongMenuIdAndCollectorId(@Param("songMenuId") String songMenuId,
			@Param("collectorId") String collectorId);

}
