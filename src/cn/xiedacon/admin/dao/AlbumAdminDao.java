package cn.xiedacon.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.Album;

public interface AlbumAdminDao {

	Album selectByName(String name);

	void updateSongNumByIdAndAddNum(@Param("id") String albumId, @Param("addNum") int addNum);

	int selectCount();

	List<Album> selectListLimit(@Param("begin") int begin, @Param("limit") int limit);

	Album selectExist(String id);

	void delete(Album album);

	int selectCountByNameLike(String nameLike);

	List<Album> selectListByNameLikeLimit(@Param("nameLike") String nameLike, @Param("begin") int begin,
			@Param("limit") int limit);

	Album selectById(String id);

	void update(Album album);

	void insertAlbum_base(Album album);

	void insertAlbum_record(Album album);

}
