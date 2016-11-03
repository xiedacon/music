package cn.xiedacon.read.dao;

import java.util.List;

import cn.xiedacon.model.SongMenuFirstTag;
import cn.xiedacon.model.SongMenuSecondTag;

public interface SongMenuTagReadDao {

	List<SongMenuFirstTag> selectFirstTagList();

	SongMenuSecondTag selectSecondTagById(String id);

}
