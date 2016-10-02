package cn.xiedacon.read.dao;

import java.util.List;

import cn.xiedacon.model.SongMenu;

public interface SongMenuReadDao {

	SongMenu selectById(String id);

	int selectCount();

	List<SongMenu> selectLimit(int begin, int limit);

	List<SongMenu> selectOrderByCollectionNumLimit(int begin, int limit);

	int selectCountBySecondTagId(String secondTagId);

	List<SongMenu> selectBySecondTagIdLimit(String secondTagId, int begin, int limit);

	List<SongMenu> selectBySecondTagIdOrderByCollectionNumLimit(String secondTagId, int i, int limit);

	List<SongMenu> selectListByCreatorId(String creatorId);

	List<SongMenu> selectListByCollectorId(String collectorId);

}
