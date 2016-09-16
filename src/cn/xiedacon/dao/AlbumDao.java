package cn.xiedacon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.xiedacon.model.Album;

@Repository
public interface AlbumDao {

	Album selectById(String id);

	int selectCountByTagId(@Param("tagId") String tagId);

	List<Album> selectListByTagIdOrderByCreateTimeLimit(@Param("tagId") String tagId, @Param("begin") int begin,
			@Param("limit") int limit);

	int selectCountBySingerId(String singerId);

	List<Album> selectListBySingerIdOrderByCreateTimeLimit(@Param("singerId") String singerId,
			@Param("begin") int begin, @Param("limit") int limit);

	void updatePlayNumById(@Param("playNum") Integer playNum, @Param("id") String id);

	void updateCommentNumById(@Param("commentNum") Integer commentNum, @Param("id") String id);

}
