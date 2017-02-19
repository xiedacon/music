package cn.xiedacon.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.admin.dao.SongMenuAdminDao;
import cn.xiedacon.admin.service.SongMenuAdminService;
import cn.xiedacon.model.SongMenu;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.PageBean;

@Service
public class SongMenuAdminServiceImpl implements SongMenuAdminService {

	@Autowired
	private SongMenuAdminDao songMenuDao;

	@Override
	public PageBean<SongMenu> selectPageBeanOrderByCreateTime(Integer page) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = songMenuDao.selectCount();
		List<SongMenu> beans = songMenuDao.selectOrderByCreateTimeLimit((page - 1) * limit, limit);
		return new PageBean<>(page, limit, count, beans);
	}

	@Override
	public void delete(SongMenu songMenu) {
		songMenuDao.delete(songMenu);
	}

	@Override
	public SongMenu selectExist(String id) {
		return songMenuDao.selectExist(id);
	}

	@Override
	public PageBean<SongMenu> selectPageBeanByNameLike(Integer page, String name) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = songMenuDao.selectCountByNameLike(name);
		List<SongMenu> beans = songMenuDao.selectByNameLikeOrderByCreateTimeLimit(name, (page - 1) * limit, limit);
		return new PageBean<>(page, limit, count, beans);
	}

}
