package cn.xiedacon.admin.service;

import cn.xiedacon.model.SongList;
import cn.xiedacon.util.PageBean;

public interface SongListAdminService {

	PageBean<SongList> selectPageBean(Integer page);

	SongList selectExist(String id);

	void delete(SongList songList);

	PageBean<SongList> selectPageBeanByNameLike(Integer page, String nameLike);

	SongList selectById(String id);

	void update(SongList songList);

	void insert(SongList songList);

}
