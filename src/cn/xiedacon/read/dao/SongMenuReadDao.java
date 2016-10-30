package cn.xiedacon.read.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.SongMenu;

public interface SongMenuReadDao {

	SongMenu selectById(String id);

	int selectCount();

	List<SongMenu> selectListOrderByCreateTimeLimit(@Param("begin") int begin, @Param("limit") int limit);

	List<SongMenu> selectListOrderByCollectionNumLimit(@Param("begin") int begin, @Param("limit") int limit);

	int selectCountBySecondTagId(String secondTagId);

	List<SongMenu> selectListBySecondTagIdOrderByCreateTimeLimit(@Param("secondTagId") String secondTagId,
			@Param("begin") int begin, @Param("limit") int limit);

	List<SongMenu> selectListBySecondTagIdOrderByCollectionNumLimit(@Param("secondTagId") String secondTagId,
			@Param("begin") int begin, @Param("limit") int limit);

	List<SongMenu> selectListByCreatorId(String creatorId);

	List<SongMenu> selectListByCollectorId(String collectorId);

}
