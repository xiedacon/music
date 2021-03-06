package cn.xiedacon.read.service;

import java.util.List;

import cn.xiedacon.model.Song;

public interface SongReadService {

	List<Song> selectListBySongMenuIdOrderByTime(String songMenuId);

	List<Song> selectListByAlbumIdOrderByRank(String albumId);

	List<Song> selectListBySongListIdOrderByRank(String songListId);

	Song selectById(String id);

	String selectLyricUriById(String songId);

	List<Song> selectListBySingerIdOrderByCollectionNumLimit(String singerId, Integer begin, Integer limit);

	String selectFileUriById(String songId);

	List<Song> selectListBySongListIdOrderByRankLimit(String songListId, Integer limit);

}
