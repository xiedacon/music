package cn.xiedacon.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.admin.dao.FirstClassifyAdminDao;
import cn.xiedacon.admin.service.FirstClassifyAdminService;
import cn.xiedacon.model.FirstClassify;

@Service
public class FirstClassifyAdminServiceImpl implements FirstClassifyAdminService {

	@Autowired
	private FirstClassifyAdminDao classifyDao;

	@Override
	public List<FirstClassify> selectList() {
		return classifyDao.selectList();
	}

	@Override
	public FirstClassify selectById(String id) {
		return classifyDao.selectById(id);
	}

	@Override
	public void delete(FirstClassify classify) {
		classifyDao.delete(classify);
	}

	@Override
	public void update(FirstClassify classify) {
		classifyDao.update(classify);
	}

	@Override
	public void insert(FirstClassify classify) {
		classifyDao.insert(classify);
	}

}
