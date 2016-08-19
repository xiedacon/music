package cn.xiedacon.service;

import java.util.List;

import cn.xiedacon.vo.SimpleSongVo;
import cn.xiedacon.vo.SongVo;

public interface SongService {

	List<SimpleSongVo> selectListBySongMenuIdOrderByRank(String songMenuId);

	List<SimpleSongVo> selectListByAlbumIdOrderByRank(String albumId);

	List<SimpleSongVo> selectListBySongListIdOrderByRank(String songListId);

	SongVo selectById(String id);

	String selectLyricUriById(String songId);

	List<SongVo> selectListBySingerIdOrderByCollectionNumLimit(String singerId, Integer begin, Integer limit);

	String selectFileUriById(String songId);

}
