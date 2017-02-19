package cn.xiedacon.write.dao;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.SongMenu;

public interface SongMenuWriteDao {

	void insertSongMenu_base(SongMenu songMenu);

	void insertSongMenu_record(SongMenu songMenu);

	void delete(SongMenu songMenu);

	void update(SongMenu songMenu);

	void updateIconById(@Param("icon") String icon, @Param("id") String id);

	void updatePlayNumById(@Param("playNum") Integer playNum, @Param("id") String id);

	void updateCommentNumById(@Param("commentNum") Integer commentNum, @Param("id") String id);

	void updateCreatorNameByCreatorId(@Param("creatorName") String creatorName, @Param("creatorId") String creatorId);

	void updateSongNumById(@Param("songNum") Integer songNum, @Param("id") String id);

	void updateCollectionNumById(@Param("collectionNum") Integer collectionNum, @Param("id") String id);

}
