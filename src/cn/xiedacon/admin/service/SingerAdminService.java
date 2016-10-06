package cn.xiedacon.admin.service;

import java.util.List;

import cn.xiedacon.model.Singer;
import cn.xiedacon.util.PageBean;

public interface SingerAdminService {

	PageBean<Singer> selectPageBean(Integer page);

	Singer selectExist(String id);

	void delete(Singer singer);

	PageBean<Singer> selectPageBeanByNameLike(Integer page, String name);

	Singer selectById(String id);

	void update(Singer singer);

	void insert(Singer singer);

	void batchInsert(List<Singer> singerList);

}
