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

}
