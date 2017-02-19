package cn.xiedacon.admin.service;

import java.util.List;

import cn.xiedacon.model.AlbumTag;

public interface AlbumTagAdminService {

	void insert(AlbumTag albumTag);

	void update(AlbumTag albumTag);

	AlbumTag selectById(String id);

	void delete(AlbumTag albumTag);

	List<AlbumTag> selectList();

}
