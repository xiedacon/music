package cn.xiedacon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.SongList;

public interface SongListDao {

	SongList selectById(String id);

	List<SongList> selectList();

	void updatePlayNumById(@Param("playNum") Integer playNum, @Param("id") String id);

	void updateCommentNumById(@Param("commentNum") Integer commentNum, @Param("id") String id);

}
