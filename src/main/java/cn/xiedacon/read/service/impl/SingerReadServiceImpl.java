package cn.xiedacon.read.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.model.Singer;
import cn.xiedacon.read.dao.SingerReadDao;
import cn.xiedacon.read.service.SingerReadService;

@Service
public class SingerReadServiceImpl implements SingerReadService {

	@Autowired
	private SingerReadDao singerDao;

	@Override
	public List<Singer> selectListLimit(Integer begin, Integer limit) {
		return singerDao.selectListLimit(begin, limit);
	}

	@Override
	public List<Singer> selectListOrderByCollectionNumLimit(Integer begin, Integer limit) {
		return singerDao.selectListOrderByCollectionNumLimit(begin, limit);
	}

	@Override
	public List<Singer> selectListByClassifyIdOrderByCollectionNumLimit(Integer classifyId, Integer begin,
			Integer limit) {
		return singerDao.selectListByClassifyIdOrderByCollectionNumLimit(classifyId, begin, limit);
	}

	@Override
	public Singer selectById(String id) {
		return singerDao.selectById(id);
	}

	@Override
	public String selectIntroductionById(String id) {
		return singerDao.selectIntroductionById(id);
	}

}
