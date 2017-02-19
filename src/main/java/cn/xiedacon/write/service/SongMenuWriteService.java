package cn.xiedacon.write.service;

import cn.xiedacon.model.SongMenu;

public interface SongMenuWriteService {

	void insert(SongMenu songMenu);

	void delete(SongMenu songMenu);

	void update(SongMenu songMenu);

	void updateIconById(String icon, String songMenuId);

	void updatePlayNumById(Integer playNum, String songMenuId);

	void updateCommentNumById(Integer commentNum, String id);
}
