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
	public void updateUsername(User dataUser) {
		userDao.updateUsername(dataUser);
		songMenuDao.updateCreatorNameByCreatorId(dataUser.getName(), dataUser.getId());
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

	@Override
	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}

}
