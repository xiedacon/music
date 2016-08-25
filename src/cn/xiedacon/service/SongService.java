package cn.xiedacon.service;

import java.util.List;

import cn.xiedacon.model.Song;

public interface SongService {

	List<Song> selectListBySongMenuIdOrderByRank(String songMenuId);

	List<Song> selectListByAlbumIdOrderByRank(String albumId);

	List<Song> selectListBySongListIdOrderByRank(String songListId);

	Song selectById(String id);

	String selectLyricUriById(String songId);

	List<Song> selectListBySingerIdOrderByCollectionNumLimit(String singerId, Integer begin, Integer limit);

	String selectFileUriById(String songId);

}
