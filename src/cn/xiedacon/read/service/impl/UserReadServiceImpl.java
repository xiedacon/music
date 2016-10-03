package cn.xiedacon.read.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.model.User;
import cn.xiedacon.read.dao.UserReadDao;
import cn.xiedacon.read.service.UserReadService;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.PageBean;

@Service
public class UserReadServiceImpl implements UserReadService {

	@Autowired
	private UserReadDao userDao;

	@Override
	public User selectByPhone(String phone) {
		return userDao.selectByPhone(phone);
	}

	@Override
	public User selectById(String id) {
		return userDao.selectById(id);
	}

	@Override
	public User selectByGitHubId(String githubId) {
		return userDao.selectByGitHubId(githubId);
	}

	@Override
	public PageBean<User> selectPageBean(Integer page) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = userDao.selectCount();
		List<User> beans = userDao.selectListLimit(limit * (page - 1), limit);
		return new PageBean<>(page, limit, count, beans);
	}

}
