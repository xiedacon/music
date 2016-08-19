package cn.xiedacon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.AlbumDao;
import cn.xiedacon.dao.WebDao;
import cn.xiedacon.service.AlbumService;
import cn.xiedacon.util.PageBean;
import cn.xiedacon.vo.AlbumVo;
import cn.xiedacon.vo.SimpleAlbumVo;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private AlbumDao albumDao;
	@Autowired
	private WebDao webDao;

	@Override
	public List<SimpleAlbumVo> selectHotList() {
		String ablumIdsString = webDao.selectAblumIds();
		String[] ablumIds = ablumIdsString.split("\\|");

		return albumDao.selectAblumListByIds(ablumIds);
	}

	@Override
	public AlbumVo selectById(String id) {
		return albumDao.selectById(id);
	}

	@Override
	public PageBean<SimpleAlbumVo> selectPageBeanByTagIdOrderByCreateTimeLimit(String tagId, Integer page) {
		PageBean<SimpleAlbumVo> pageBean = new PageBean<>();

		int limit = 10;
		int count = albumDao.selectCountByTagId(tagId);
		int totalPage = count / limit;
		totalPage = count % limit == 0 ? 0 : ++totalPage;

		int begin = limit * (page - 1);

		List<SimpleAlbumVo> beans = albumDao.selectListByTagIdOrderByCreateTimeLimit(tagId, begin, limit);

		pageBean.setPage(page);
		pageBean.setLimit(limit);
		pageBean.setCount(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setBeans(beans);
		return pageBean;
	}

	@Override
	public PageBean<SimpleAlbumVo> selectPageBeanBySingerIdOrderByCreateTime(String singerId, Integer page) {
		PageBean<SimpleAlbumVo> pageBean = new PageBean<>();

		int limit = 10;
		int count = albumDao.selectCountBySingerId(singerId);
		int totalPage = count / limit;
		totalPage = count % limit == 0 ? 0 : ++totalPage;

		int begin = limit * (page - 1);

		List<SimpleAlbumVo> beans = albumDao.selectListBySingerIdOrderByCreateTimeLimit(singerId, begin, limit);

		pageBean.setPage(page);
		pageBean.setLimit(limit);
		pageBean.setCount(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setBeans(beans);
		return pageBean;
	}

}
