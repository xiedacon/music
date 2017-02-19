package cn.xiedacon.read.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.Song;

public interface SongReadDao {

	List<Song> selectListBySongMenuIdOrderByTime(String songMenuId);

	List<Song> selectListByAlbumIdOrderByRank(String albumId);

	List<Song> selectListBySongListIdOrderByRank(String songListId);

	Song selectById(String id);

	String selectLyricUriById(String id);

	List<Song> selectListBySingerIdOrderByCollectionNumLimit(@Param("singerId") String singerId,
			@Param("begin") Integer begin, @Param("limit") Integer limit);

	String selectFileUriById(String songId);

	List<Song> selectListBySongListIdOrderByRankLimit(@Param("songListId") String songListId,
			@Param("limit") Integer limit);

}
