package cn.xiedacon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.SongMenu;

public interface SongMenuDao {

	SongMenu selectSongMenuById(String id);

	int selectCount();

	int selectCountBySecondTagId(String secondTagId);

	List<SongMenu> selectBySecondTagIdLimit(@Param("secondTagId") String secondTagId, @Param("begin") int begin,
			@Param("limit") int limit);

	List<SongMenu> selectBySecondTagIdOrderByCollectionNumLimit(@Param("secondTagId") String secondTagId,
			@Param("begin") int begin, @Param("limit") int limit);

	List<SongMenu> selectByLimit(@Param("begin") int begin, @Param("limit") int limit);

	List<SongMenu> selectByLimitOrderByCollectionNum(@Param("begin") int begin, @Param("limit") int limit);

	List<SongMenu> selectListByCreatorId(String creatorId);

	List<SongMenu> selectListByCollectorId(String collectorId);

	void insertSongMenu_base(SongMenu songMenu);

	void insertSongMenu_record(SongMenu songMenu);

	void updateCreatorNameByCreatorId(@Param("creatorName") String creatorName, @Param("creatorId") String creatorId);

	void deleteById(String songMenuId);

	void updateIconById(@Param("icon") String icon, @Param("id") String songMenuId);
}
