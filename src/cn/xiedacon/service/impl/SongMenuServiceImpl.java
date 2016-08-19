package cn.xiedacon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.SongMenuDao;
import cn.xiedacon.dao.WebDao;
import cn.xiedacon.service.SongMenuService;
import cn.xiedacon.util.PageBean;
import cn.xiedacon.vo.SimpleSongMenuVo;
import cn.xiedacon.vo.SongMenuVo;

@Service
@Transactional
public class SongMenuServiceImpl implements SongMenuService {

	@Autowired
	private WebDao webDao;
	@Autowired
	private SongMenuDao songMenuDao;

	@Override
	public List<SimpleSongMenuVo> selectForIndex() {
		String songMenuIdsString = webDao.selectSongMenuIds();
		String[] songMenuIds = songMenuIdsString.split("\\|");

		return songMenuDao.selectSongMenuListByIds(songMenuIds);
	}

	@Override
	public SongMenuVo selectSongMenuById(String id) {
		return songMenuDao.selectSongMenuByid(id);
	}

	@Override
	public PageBean<SimpleSongMenuVo> selectPageBean(Integer page) {
		PageBean<SimpleSongMenuVo> pageBean = new PageBean<>();
		int limit = 10;
		int count = songMenuDao.selectCount();
		int totalPage = count / limit;
		totalPage = count % limit == 0 ? 0 : totalPage + 1;

		int begin = limit * (page - 1);

		List<SimpleSongMenuVo> beans = songMenuDao.selectByLimit(begin,limit);

		pageBean.setPage(page);
		pageBean.setLimit(limit);
		pageBean.setCount(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setBeans(beans);
		return pageBean;
	}

	@Override
	public PageBean<SimpleSongMenuVo> selectPageBeanOrderByCollectionNum(Integer page) {
		PageBean<SimpleSongMenuVo> pageBean = new PageBean<>();
		int limit = 10;
		int count = songMenuDao.selectCount();
		int totalPage = count / limit;
		totalPage = count % limit == 0 ? 0 : totalPage + 1;

		int begin = limit * (page - 1);

		List<SimpleSongMenuVo> beans = songMenuDao.selectByLimitOrderByCollectionNum(begin,limit);

		pageBean.setPage(page);
		pageBean.setLimit(limit);
		pageBean.setCount(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setBeans(beans);
		return pageBean;
	}

	@Override
	public PageBean<SimpleSongMenuVo> selectPageBeanBySecondTagId(String secondTagId, Integer page) {
		PageBean<SimpleSongMenuVo> pageBean = new PageBean<>();
		int limit = 10;
		int count = songMenuDao.selectCountBySecondTagId(secondTagId);
		int totalPage = count / limit;
		totalPage = count % limit == 0 ? 0 : totalPage + 1;

		int begin = limit * (page - 1);

		List<SimpleSongMenuVo> beans = songMenuDao.selectBySecondTagIdLimit(secondTagId,begin,limit);

		pageBean.setPage(page);
		pageBean.setLimit(limit);
		pageBean.setCount(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setBeans(beans);
		return pageBean;
	}

	@Override
	public PageBean<SimpleSongMenuVo> selectPageBeanBySecondTagIdOrderByCollectionNum(String secondTagId,
			Integer page) {
		PageBean<SimpleSongMenuVo> pageBean = new PageBean<>();
		int limit = 10;
		int count = songMenuDao.selectCountBySecondTagId(secondTagId);
		int totalPage = count / limit;
		totalPage = count % limit == 0 ? 0 : totalPage + 1;

		int begin = limit * (page - 1);

		List<SimpleSongMenuVo> beans = songMenuDao.selectBySecondTagIdOrderByCollectionNumLimit(secondTagId,begin,limit);

		pageBean.setPage(page);
		pageBean.setLimit(limit);
		pageBean.setCount(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setBeans(beans);
		return pageBean;
	}

	@Override
	public List<SimpleSongMenuVo> selectListByCreatorId(String creatorId) {
		return songMenuDao.selectListByCreatorId(creatorId);
	}

	@Override
	public List<SimpleSongMenuVo> selectListByCollectorId(String collectorId) {
		return songMenuDao.selectListByCollectorId(collectorId);
	}

}
