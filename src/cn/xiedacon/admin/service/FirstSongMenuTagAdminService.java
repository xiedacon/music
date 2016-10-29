package cn.xiedacon.admin.service;

import java.util.List;

import cn.xiedacon.model.SongMenuFirstTag;

public interface FirstSongMenuTagAdminService {

	void insert(SongMenuFirstTag classify);

	void update(SongMenuFirstTag tag);

	SongMenuFirstTag selectById(String id);

	void delete(SongMenuFirstTag tag);

	List<SongMenuFirstTag> selectList();

}
