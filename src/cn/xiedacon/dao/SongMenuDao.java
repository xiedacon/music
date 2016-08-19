package cn.xiedacon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.SongMenu;
import cn.xiedacon.model.User;
import cn.xiedacon.vo.SimpleSongMenuVo;
import cn.xiedacon.vo.SongMenuVo;

public interface SongMenuDao {

	List<SimpleSongMenuVo> selectSongMenuListByIds(@Param("songMenuIds") String[] songMenuIds);

	SongMenuVo selectSongMenuByid(String id);

	int selectCount();

	int selectCountBySecondTagId(String secondTagId);

	List<SimpleSongMenuVo> selectBySecondTagIdLimit(@Param("secondTagId")String secondTagId,@Param("begin") int begin,@Param("limit") int limit);

	List<SimpleSongMenuVo> selectBySecondTagIdOrderByCollectionNumLimit(@Param("secondTagId")String secondTagId,@Param("begin") int begin,@Param("limit") int limit);

	List<SimpleSongMenuVo> selectByLimit(@Param("begin")int begin,@Param("limit") int limit);

	List<SimpleSongMenuVo> selectByLimitOrderByCollectionNum(@Param("begin")int begin,@Param("limit") int limit);

	List<SimpleSongMenuVo> selectListByCreatorId(String creatorId);

	List<SimpleSongMenuVo> selectListByCollectorId(String collectorId);

	void insertSongMenu_base(SongMenu songMenu);

	void insertSongMenu_record(SongMenu songMenu);

	void updateCreatorName(User dataUser);

}
