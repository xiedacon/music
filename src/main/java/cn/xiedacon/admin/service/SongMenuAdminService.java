package cn.xiedacon.admin.service;

import cn.xiedacon.model.SongMenu;
import cn.xiedacon.util.PageBean;

public interface SongMenuAdminService {

	PageBean<SongMenu> selectPageBeanOrderByCreateTime(Integer page);

	void delete(SongMenu songMenu);

	SongMenu selectExist(String id);

	PageBean<SongMenu> selectPageBeanByNameLike(Integer page, String name);

}
