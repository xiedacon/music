package cn.xiedacon.write.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.model.User;
import cn.xiedacon.write.dao.SongMenuWriteDao;
import cn.xiedacon.write.dao.UserWriteDao;
import cn.xiedacon.write.service.UserWriteService;

@Service
public class UserWriteServiceImpl implements UserWriteService {

	@Autowired
	private UserWriteDao userDao;
	@Autowired
	private SongMenuWriteDao songMenuDao;

	@Override
	public void updateUsernameById(String username, String id) {
		userDao.updateUsernameById(username, id);
		songMenuDao.updateCreatorNameByCreatorId(username, id);
	}

	@Override
	public void insert(User dataUser) {
		userDao.insertUser_base(dataUser);
		userDao.insertUser_record(dataUser);
	}

	@Override
	public void updatePasswordById(String password, String id) {
		userDao.updatePasswordById(password, id);
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
	}

}
