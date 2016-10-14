package cn.xiedacon.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.Song;

public interface SongAdminDao {

	int selectCount();

	List<Song> selectListLimit(@Param("begin") int begin, @Param("limit") int limit);

	Song selectExist(String id);

	void delete(Song song);

	int selectCountByNameLike(String name);

	List<Song> selectListByNameLikeLimit(@Param("name") String name, @Param("begin") int begin,
			@Param("limit") int limit);

	Song selectById(String id);

	void update(Song song);

	void insertSong_base(Song song);

	void insertSong_record(Song song);

}
