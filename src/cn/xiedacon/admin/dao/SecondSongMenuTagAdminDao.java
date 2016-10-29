package cn.xiedacon.admin.dao;

import java.util.List;

import cn.xiedacon.model.SongMenuSecondTag;

public interface SecondSongMenuTagAdminDao {

	List<SongMenuSecondTag> selectList();

	SongMenuSecondTag selectById(String id);

	void delete(SongMenuSecondTag tag);

	void update(SongMenuSecondTag tag);

	void insert(SongMenuSecondTag tag);

}
