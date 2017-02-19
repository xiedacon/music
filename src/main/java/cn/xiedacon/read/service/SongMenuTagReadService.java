package cn.xiedacon.read.service;

import java.util.List;

import cn.xiedacon.model.SongMenuFirstTag;
import cn.xiedacon.model.SongMenuSecondTag;

public interface SongMenuTagReadService {

	List<SongMenuFirstTag> selectList();

	SongMenuSecondTag selectSecondTagById(String id);

}
