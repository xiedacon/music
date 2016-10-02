package cn.xiedacon.read.dao;

import java.util.List;

import cn.xiedacon.model.SongList;

public interface SongListReadDao {

	SongList selectById(String id);

	List<SongList> selectList();

}
