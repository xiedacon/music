package cn.xiedacon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.SongMenuDao;
import cn.xiedacon.dao.UserDao;
import cn.xiedacon.model.User;
import cn.xiedacon.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private SongMenuDao songMenuDao;

	@Override
	public User selectByPhone(String phone) {
		return userDao.selectByPhone(phone);
	}

	@Override
	public User selectById(String id) {
		return userDao.selectById(id);
	}

	@Override
	public void updateUsername(User dataUser) {
		userDao.updateUsername(dataUser);
		songMenuDao.updateCreatorNameByCreatorId(dataUser.getName(), dataUser.getIcon());
	}

	@Override
	public User selectByGitHubId(String githubId) {
		return userDao.selectByGitHubId(githubId);
	}

	@Override
	public void insertUser(User dataUser) {
		userDao.insertUser_base(dataUser);
		userDao.insertUser_record(dataUser);
	}

	@Override
	public void updatePassword(User dataUser) {
		userDao.updatePassword(dataUser);
	}

}
