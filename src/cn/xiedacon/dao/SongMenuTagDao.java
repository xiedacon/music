package cn.xiedacon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.SongMenuFirstTag;
import cn.xiedacon.model.SongMenuSecondTag;

public interface SongMenuTagDao {

	List<SongMenuFirstTag> selectList();

	SongMenuSecondTag selectSecondTagById(String id);

	void deleteGLBySongMenuId(String songMenuId);

	void insertGL(@Param("id") String id, @Param("songMenuId") String songMenuId, @Param("tagId") String tagId);

}
