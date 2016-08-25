package cn.xiedacon.service;

import java.util.List;

import cn.xiedacon.model.SongMenuFirstTag;
import cn.xiedacon.model.SongMenuSecondTag;

public interface SongMenuTagService {

	List<SongMenuFirstTag> selectList();

	SongMenuSecondTag selectSecondTagById(String id);


}
