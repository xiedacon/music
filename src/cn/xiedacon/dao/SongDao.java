package cn.xiedacon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.vo.SimpleSongVo;
import cn.xiedacon.vo.SongVo;

public interface SongDao {

	List<SimpleSongVo> selectListBySongMenuIdOrderByRank(String songMenuId);

	List<SimpleSongVo> selectListByAlbumIdOrderByRank(String albumId);

	List<SimpleSongVo> selectListBySongListIdOrderByRank(String songListId);

	SongVo selectById(String id);

	String selectLyricUriById(String id);

	List<SongVo> selectListBySingerIdOrderByCollectionNumLimit(@Param("singerId") String singerId, @Param("begin") Integer begin,
			@Param("limit") Integer limit);

	String selectFileUriById(String songId);

}
