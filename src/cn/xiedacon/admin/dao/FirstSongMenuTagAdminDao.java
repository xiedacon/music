package cn.xiedacon.admin.dao;

import java.util.List;

import cn.xiedacon.model.SongMenuFirstTag;

public interface FirstSongMenuTagAdminDao {

	List<SongMenuFirstTag> selectList();

	SongMenuFirstTag selectById(String id);

	void delete(SongMenuFirstTag tag);

	void update(SongMenuFirstTag tag);

	void insert(SongMenuFirstTag tag);

}
