package cn.xiedacon.admin.service;

import java.util.List;

import cn.xiedacon.model.SongList_SongGL;

public interface SongList_SongGLService {

	void batchDeleteThenInsert(String id, List<SongList_SongGL> songList_SongGLList);

	List<SongList_SongGL> selectListBySongListIdOrderByRank(String id);

}
