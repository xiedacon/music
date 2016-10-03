package cn.xiedacon.write.dao;

import cn.xiedacon.model.SongMenu;

public interface SongMenuWriteDao {

	void insertSongMenu_base(SongMenu songMenu);

	void insertSongMenu_record(SongMenu songMenu);

	void delete(SongMenu songMenu);

	void update(SongMenu songMenu);

	void updateIconById(String icon, String songMenuId);

	void updatePlayNumById(Integer playNum, String songMenuId);

	void updateCommentNumById(Integer commentNum, String id);

	void updateCreatorNameByCreatorId(String name, String icon);

	void updateSongNumById(Integer songNum, String id);

	void updateCollectionNumById(Integer collectionNum, String id);

}
