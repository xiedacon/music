package cn.xiedacon.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.admin.dao.AdminUserDao;
import cn.xiedacon.admin.service.AdminUserService;
import cn.xiedacon.model.User;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.PageBean;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserDao userDao;

	@Override
	public PageBean<User> selectPageBean(Integer page) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = userDao.selectCount();
		List<User> beans = userDao.selectLimit((page - 1) * limit, limit);
		return new PageBean<>(page, limit, count, beans);
	}

	@Override
	public User selectById(String id) {
		return userDao.selectById(id);
	}

	@Override
	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}

}
