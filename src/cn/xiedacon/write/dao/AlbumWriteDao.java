package cn.xiedacon.write.dao;

import org.apache.ibatis.annotations.Param;

public interface AlbumWriteDao {

	void updatePlayNumById(@Param("playNum") Integer playNum, @Param("id") String id);

	void updateCommentNumById(@Param("commentNum") Integer commentNum, @Param("id") String id);

}
