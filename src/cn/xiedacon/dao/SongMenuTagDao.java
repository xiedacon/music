package cn.xiedacon.dao;

import java.util.List;

import cn.xiedacon.model.SongMenuFirstTag;
import cn.xiedacon.model.SongMenuSecondTag;

public interface SongMenuTagDao {

	List<SongMenuFirstTag> selectList();

	SongMenuSecondTag selectSecondTagById(String id);

}
