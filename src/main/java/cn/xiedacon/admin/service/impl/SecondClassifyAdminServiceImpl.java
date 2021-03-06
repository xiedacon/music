package cn.xiedacon.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.xiedacon.admin.dao.BatchSqlDao;
import cn.xiedacon.admin.dao.SecondClassifyAdminDao;
import cn.xiedacon.admin.service.SecondClassifyAdminService;
import cn.xiedacon.model.SecondClassify;

@Service
public class SecondClassifyAdminServiceImpl implements SecondClassifyAdminService {

	@Autowired
	private SecondClassifyAdminDao classifyDao;
	@Autowired
	@Qualifier("batchSqlDaoImpl")
	private BatchSqlDao batchDao;

	@Override
	public List<SecondClassify> selectList() {
		return classifyDao.selectList();
	}

	@Override
	public SecondClassify selectById(String id) {
		return classifyDao.selectById(id);
	}

	@Override
	public void delete(SecondClassify secondClassify) {
		classifyDao.delete(secondClassify);
	}

	@Override
	public void update(SecondClassify secondClassify) {
		classifyDao.update(secondClassify);
	}

	@Override
	public void insert(SecondClassify secondClassify) {
		classifyDao.insert(secondClassify);
	}

	@Override
	public Map<String, SecondClassify> batchSelectById(List<String> classifyIdList) {
		return batchDao.selectSecondClassifyById(classifyIdList);
	}

}
