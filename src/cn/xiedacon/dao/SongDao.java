package cn.xiedacon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.Song;

public interface SongDao {

	List<Song> selectListBySongMenuIdOrderByRank(String songMenuId);

	List<Song> selectListByAlbumIdOrderByRank(String albumId);

	List<Song> selectListBySongListIdOrderByRank(String songListId);

	Song selectById(String id);

	String selectLyricUriById(String id);

	List<Song> selectListBySingerIdOrderByCollectionNumLimit(@Param("singerId") String singerId,
			@Param("begin") Integer begin, @Param("limit") Integer limit);

	String selectFileUriById(String id);

	void updatePlayNumById(@Param("playNum") Integer playNum, @Param("id") String id);
	
	void updateCommentNumById(@Param("commentNum") Integer commentNum, @Param("id") String id);

}
