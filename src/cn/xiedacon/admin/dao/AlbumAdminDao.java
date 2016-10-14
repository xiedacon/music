package cn.xiedacon.admin.dao;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.Album;

public interface AlbumAdminDao {

	Album selectByName(String name);

	void updateSongNumByIdAndAddNum(@Param("id") String albumId,@Param("addNum") int addNum);

}
