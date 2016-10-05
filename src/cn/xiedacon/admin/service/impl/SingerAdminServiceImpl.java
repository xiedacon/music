package cn.xiedacon.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.admin.dao.SingerAdminDao;
import cn.xiedacon.admin.service.SingerAdminService;
import cn.xiedacon.model.Singer;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.PageBean;

@Service
public class SingerAdminServiceImpl implements SingerAdminService {

	@Autowired
	private SingerAdminDao singerDao;

	@Override
	public PageBean<Singer> selectPageBean(Integer page) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = singerDao.selectCount();
		List<Singer> beans = singerDao.selectListLimit((page - 1) * limit, limit);
		return new PageBean<>(page, limit, count, beans);
	}

	@Override
	public Singer selectExist(String id) {
		return singerDao.selectExist(id);
	}

	@Override
	public void delete(Singer singer) {
		singerDao.delete(singer);
	}

	@Override
	public PageBean<Singer> selectPageBeanByNameLike(Integer page, String name) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = singerDao.selectCountByNameLike(name);
		List<Singer> beans = singerDao.selectListByNameLikeLimit(name, (page - 1) * limit, limit);
		return new PageBean<>(page, limit, count, beans);
	}

	@Override
	public Singer selectById(String id) {
		return singerDao.selectById(id);
	}

	@Override
	public void update(Singer singer) {
		singerDao.update(singer);
	}

	@Override
	public void insert(Singer singer) {
		singerDao.insert(singer);
	}

}
