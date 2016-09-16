package cn.xiedacon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.SingerDao;
import cn.xiedacon.model.Singer;
import cn.xiedacon.service.SingerService;

@Service
@Transactional
public class SingerServiceImpl implements SingerService {

	@Autowired
	private SingerDao singerDao;
	
	@Override
	public List<Singer> selectListLimit(Integer begin, Integer limit) {
		return singerDao.selectListLimit(begin, limit);
	}

	@Override
	public List<Singer> selectListOrderByCollectionNumLimit(Integer begin, Integer limit) {
		return singerDao.selectListOrderByCollectionNumLimit(begin,limit);
	}

	@Override
	public List<Singer> selectListByClassifyIdOrderByCollectionNumLimit(Integer classifyId, Integer begin,
			Integer limit) {
		return singerDao.selectListByClassifyIdOrderByCollectionNumLimit(classifyId,begin,limit);
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
