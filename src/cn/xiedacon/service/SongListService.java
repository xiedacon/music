package cn.xiedacon.service;

import java.util.List;

import cn.xiedacon.model.SongList;

public interface SongListService {

	SongList selectById(String id);

	List<SongList> selectList();

	void updatePlayNumById(Integer playNum, String id);

	void updateCommentNumById(Integer commentNum, String id);

}
