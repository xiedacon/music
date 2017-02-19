package cn.xiedacon.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.xiedacon.admin.dao.AlbumAdminDao;
import cn.xiedacon.admin.dao.BatchSqlDao;
import cn.xiedacon.admin.service.AlbumAdminService;
import cn.xiedacon.model.Album;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.PageBean;

@Service
public class AlbumAdminServiceImpl implements AlbumAdminService {

	@Autowired
	private AlbumAdminDao albumDao;
	@Qualifier("batchSqlDaoImpl")
	@Autowired
	private BatchSqlDao batchDao;

	@Override
	public Album selectByName(String name) {
		return albumDao.selectByName(name);
	}

	@Override
	public Map<String, Album> batchSelectByName(List<String> albumNameList) {
		return batchDao.selectAlbumByName(albumNameList);
	}

	@Override
	public PageBean<Album> selectPageBean(Integer page) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = albumDao.selectCount();
		List<Album> beans = albumDao.selectListLimit((page - 1) * limit, limit);
		return new PageBean<>(page, limit, count, beans);
	}

	@Override
	public Album selectExist(String id) {
		return albumDao.selectExist(id);
	}

	@Override
	public void delete(Album album) {
		albumDao.delete(album);
	}

	@Override
	public PageBean<Album> selectPageBeanByNameLike(Integer page, String nameLike) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = albumDao.selectCountByNameLike(nameLike);
		List<Album> beans = albumDao.selectListByNameLikeLimit(nameLike, (page - 1) * limit, limit);
		return new PageBean<>(page, limit, count, beans);
	}

	@Override
	public Album selectById(String id) {
		return albumDao.selectById(id);
	}

	@Override
	public void update(Album album) {
		albumDao.update(album);
	}

	@Override
	public void insert(Album album) {
		albumDao.insertAlbum_base(album);
		albumDao.insertAlbum_record(album);
	}

	@Override
	public void batchInsert(List<Album> albumList) {
		batchDao.insertAlbum(albumList);
	}

}
