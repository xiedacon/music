package cn.xiedacon.admin.service;

import java.util.List;
import java.util.Map;

import cn.xiedacon.model.Album;
import cn.xiedacon.util.PageBean;

public interface AlbumAdminService {

	Album selectByName(String name);

	Map<String, Album> batchSelectByName(List<String> albumNameList);

	PageBean<Album> selectPageBean(Integer page);

	Album selectExist(String id);

	void delete(Album album);

	PageBean<Album> selectPageBeanByNameLike(Integer page, String nameLike);

	Album selectById(String id);

	void update(Album album);

	void insert(Album album);

	void batchInsert(List<Album> albumList);

}
