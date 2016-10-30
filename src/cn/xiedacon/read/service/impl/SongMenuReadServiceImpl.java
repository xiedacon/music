package cn.xiedacon.read.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.model.SongMenu;
import cn.xiedacon.read.dao.SongMenuReadDao;
import cn.xiedacon.read.service.SongMenuReadService;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.PageBean;

@Service
public class SongMenuReadServiceImpl implements SongMenuReadService {

	@Autowired
	private SongMenuReadDao songMenuDao;

	@Override
	public SongMenu selectById(String id) {
		return songMenuDao.selectById(id);
	}

	@Override
	public PageBean<SongMenu> selectPageBeanOrderByCreateTime(Integer page) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = songMenuDao.selectCount();
		List<SongMenu> beans = songMenuDao.selectListOrderByCreateTimeLimit(limit * (page - 1), limit);
		return new PageBean<>(page, limit, count, beans);
	}

	@Override
	public PageBean<SongMenu> selectPageBeanOrderByCollectionNum(Integer page) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = songMenuDao.selectCount();
		List<SongMenu> beans = songMenuDao.selectListOrderByCollectionNumLimit(limit * (page - 1), limit);
		return new PageBean<>(page, limit, count, beans);
	}

	@Override
	public PageBean<SongMenu> selectPageBeanBySecondTagIdOrderByCreateTime(String secondTagId, Integer page) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = songMenuDao.selectCountBySecondTagId(secondTagId);
		List<SongMenu> beans = songMenuDao.selectListBySecondTagIdOrderByCreateTimeLimit(secondTagId, limit * (page - 1), limit);
		return new PageBean<>(page, limit, count, beans);
	}

	@Override
	public PageBean<SongMenu> selectPageBeanBySecondTagIdOrderByCollectionNum(String secondTagId, Integer page) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = songMenuDao.selectCountBySecondTagId(secondTagId);
		List<SongMenu> beans = songMenuDao.selectListBySecondTagIdOrderByCollectionNumLimit(secondTagId, limit * (page - 1),
				limit);
		return new PageBean<>(page, limit, count, beans);
	}

	@Override
	public List<SongMenu> selectListByCreatorId(String creatorId) {
		return songMenuDao.selectListByCreatorId(creatorId);
	}

	@Override
	public List<SongMenu> selectListByCollectorId(String collectorId) {
		return songMenuDao.selectListByCollectorId(collectorId);
	}

}
