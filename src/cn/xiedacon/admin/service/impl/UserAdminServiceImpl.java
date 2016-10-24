package cn.xiedacon.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.admin.dao.UserAdminDao;
import cn.xiedacon.admin.service.UserAdminService;
import cn.xiedacon.model.User;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.PageBean;

@Service
public class UserAdminServiceImpl implements UserAdminService {

	@Autowired
	private UserAdminDao userDao;

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

	@Override
	public Object selectPageBeanByNameLike(Integer page, String name) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = userDao.selectCountByNameLike(name);
		List<User> beans = userDao.selectByNameLikeLimit(name, (page - 1) * limit, limit);
		return new PageBean<>(page, limit, count, beans);
	}

}
