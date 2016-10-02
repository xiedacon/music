package cn.xiedacon.read.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.Album;

public interface AlbumReadDao {

	Album selectById(String id);

	int selectCountByTagId(String tagId);

	List<Album> selectListByTagIdOrderByCreateTimeLimit(@Param("tagId") String tagId, @Param("begin") int begin,
			@Param("limit") int limit);

	int selectCountBySingerId(String singerId);

	List<Album> selectListBySingerIdOrderByCreateTimeLimit(@Param("singerId") String singerId,
			@Param("begin") int begin, @Param("limit") int limit);

}
