package cn.xiedacon.admin.dao;

import java.util.List;

import cn.xiedacon.model.SongList_SongGL;

public interface SongList_SongGLDao {

	List<SongList_SongGL> selectListBySongListIdOrderByRank(String songListId);

}
