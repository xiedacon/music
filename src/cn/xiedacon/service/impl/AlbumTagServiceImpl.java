package cn.xiedacon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.AlbumTagDao;
import cn.xiedacon.service.AlbumTagService;
import cn.xiedacon.vo.AlbumTagVo;

@Service
@Transactional
public class AlbumTagServiceImpl implements AlbumTagService {

	@Autowired
	private AlbumTagDao ablumTagDao;
	
	@Override
	public List<AlbumTagVo> selectList() {
		return ablumTagDao.selectList();
	}

}
