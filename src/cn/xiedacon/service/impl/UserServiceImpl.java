package cn.xiedacon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.SongMenuDao;
import cn.xiedacon.dao.UserDao;
import cn.xiedacon.model.User;
import cn.xiedacon.service.UserService;
import cn.xiedacon.util.PageBean;

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

	@Override
	public PageBean<User> selectPageBean(Integer page) {
		PageBean<User> pageBean = new PageBean<User>();
		int limit = 10;
		pageBean.setLimit(limit);
		pageBean.setPage(page);
		int count = userDao.selectCount();
		pageBean.setCount(count);
		int totalPage = count / limit + (count % limit == 0 ? 0 : 1);
		pageBean.setTotalPage(totalPage);
		int begin = limit * (page - 1);
		pageBean.setBeans(userDao.selectListLimit(begin, limit));
		return pageBean;
	}

}
