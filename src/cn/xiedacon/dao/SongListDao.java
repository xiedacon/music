package cn.xiedacon.dao;

import java.util.List;

import cn.xiedacon.model.SongList;

public interface SongListDao {

	SongList selectById(String id);

	List<SongList> selectList();

}
