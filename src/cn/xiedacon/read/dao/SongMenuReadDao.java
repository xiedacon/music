package cn.xiedacon.read.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.SongMenu;

public interface SongMenuReadDao {

	SongMenu selectById(String id);

	int selectCount();

	List<SongMenu> selectLimit(@Param("begin") int begin, @Param("limit") int limit);

	List<SongMenu> selectOrderByCollectionNumLimit(@Param("begin") int begin, @Param("limit") int limit);

	int selectCountBySecondTagId(String secondTagId);

	List<SongMenu> selectBySecondTagIdLimit(@Param("secondTagId") String secondTagId, @Param("begin") int begin,
			@Param("limit") int limit);

	List<SongMenu> selectBySecondTagIdOrderByCollectionNumLimit(@Param("secondTagId") String secondTagId,
			@Param("begin") int begin, @Param("limit") int limit);

	List<SongMenu> selectListByCreatorId(String creatorId);

	List<SongMenu> selectListByCollectorId(String collectorId);

}
