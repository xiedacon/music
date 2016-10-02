package cn.xiedacon.read.service;

import java.util.List;

import cn.xiedacon.model.SongList;

public interface SongListReadService {

	SongList selectById(String id);

	List<SongList> selectList();
}
