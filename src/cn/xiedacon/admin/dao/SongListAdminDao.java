package cn.xiedacon.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.SongList;

public interface SongListAdminDao {

	int selectCount();

	List<SongList> selectListLimit(@Param("begin") int begin, @Param("limit") int limit);

	SongList selectExist(String id);

	void delete(SongList songList);

	int selectCountByNameLike(String nameLike);

	List<SongList> selectListByNameLikeLimit(@Param("nameLike") String nameLike, @Param("begin") int begin,
			@Param("limit") int limit);

	SongList selectById(String id);

	void update(SongList songList);

	void insertSonglist_base(SongList songList);

	void insertSonglist_record(SongList songList);

}
