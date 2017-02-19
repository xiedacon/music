package cn.xiedacon.admin.dao;

import java.util.List;

import cn.xiedacon.model.AlbumTag;

public interface AlbumTagAdminDao {

	List<AlbumTag> selectList();

	AlbumTag selectById(String id);

	void delete(AlbumTag classify);

	void update(AlbumTag classify);

	void insert(AlbumTag classify);

}
