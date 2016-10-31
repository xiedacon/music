package cn.xiedacon.write.service;

import java.util.List;
import java.util.Map;

import cn.xiedacon.model.SongMenuSecondTag;

public interface SecondSongMenuTagReadService {

	Map<String, SongMenuSecondTag> batchSelectById(List<String> tagIdList);

}
