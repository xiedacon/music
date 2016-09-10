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

	void delete(SongMenu songMenu);

	void updateIconById(@Param("icon") String icon, @Param("id") String songMenuId);

	void update(SongMenu songMenu);

	void updateSongNumById(@Param("songNum") Integer songNum, @Param("id") String id);

	void updateCollectionNumById(@Param("collectionNum") Integer collectionNum, @Param("id") String id);

	void updatePlayNumById(@Param("playNum") Integer playNum, @Param("id") String songMenuId);

	void updateCommentNumById(@Param("commentNum") Integer commentNum, @Param("id") String id);
}
