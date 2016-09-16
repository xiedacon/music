package cn.xiedacon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.AlbumTagDao;
import cn.xiedacon.model.AlbumTag;
import cn.xiedacon.service.AlbumTagService;

@Service
@Transactional
public class AlbumTagServiceImpl implements AlbumTagService {

	@Autowired
	private AlbumTagDao ablumTagDao;
	
	@Override
	public List<AlbumTag> selectList() {
		return ablumTagDao.selectList();
	}

}
