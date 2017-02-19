package cn.xiedacon.admin.service;

import java.util.List;

import cn.xiedacon.model.SongMenuSecondTag;

public interface SecondSongMenuTagAdminService {

	void delete(SongMenuSecondTag secondTag);

	SongMenuSecondTag selectById(String id);

	void update(SongMenuSecondTag secondTag);

	void insert(SongMenuSecondTag secondTag);

	List<SongMenuSecondTag> selectList();

}
