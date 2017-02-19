package cn.xiedacon.read.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.model.User;
import cn.xiedacon.read.dao.UserReadDao;
import cn.xiedacon.read.service.UserReadService;

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

}
