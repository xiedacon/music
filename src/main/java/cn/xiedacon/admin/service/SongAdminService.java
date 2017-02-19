package cn.xiedacon.admin.service;

import java.util.List;
import java.util.Map;

import cn.xiedacon.model.Song;
import cn.xiedacon.util.PageBean;

public interface SongAdminService {

	PageBean<Song> selectPageBean(Integer page);

	Song selectExist(String id);

	void delete(Song song);

	PageBean<Song> selectPageBeanByNameLike(Integer page, String name);

	Song selectById(String id);

	void update(Song song);

	void insert(Song song);

	void batchInsert(List<Song> songList);

	Map<String, Song> batchSelectByName(List<String> songNames);

}
