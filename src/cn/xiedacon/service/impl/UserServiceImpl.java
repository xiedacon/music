package cn.xiedacon.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.SongMenuDao;
import cn.xiedacon.dao.UserDao;
import cn.xiedacon.dao.WebDao;
import cn.xiedacon.factory.Factory;
import cn.xiedacon.factory.SongMenuEnum;
import cn.xiedacon.model.SongMenu;
import cn.xiedacon.model.User;
import cn.xiedacon.service.UserService;
import cn.xiedacon.util.UUIDUtils;
import cn.xiedacon.vo.SimpleUserVo;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private WebDao webDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private SongMenuDao songMenuDao;
	@Autowired
	private Factory factory;

	@Override
	public List<SimpleUserVo> selectForIndex() {
		String userIdsString = webDao.selectUserIds();
		String[] userIds = userIdsString.split("\\|");
		return userDao.selectUserListByIds(userIds);
	}

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
		songMenuDao.updateCreatorName(dataUser);
	}

	@Override
	public User selectByGitHubId(String githubId) {
		return userDao.selectByGitHubId(githubId);
	}

	@Override
	public void insertUser(User dataUser) {
		dataUser.setCreateSongMenuNum(1);
		
		userDao.insertUser_base(dataUser);
		userDao.insertUser_record(dataUser);

		SongMenu songMenu = factory.get(SongMenu.class);
		String id = UUIDUtils.randomUUID();
		songMenu.setId(id);
		songMenu.setName(SongMenuEnum.LOVE.name);
		songMenu.setCreatorId(dataUser.getId());
		songMenu.setCreatorName(dataUser.getName());
		songMenu.setIcon(SongMenuEnum.LOVE.icon);
		songMenu.setCreateTime(new Date());
		songMenu.setUserId(dataUser.getId());

		songMenuDao.insertSongMenu_base(songMenu);
		songMenuDao.insertSongMenu_record(songMenu);
	}

	@Override
	public void updatePassword(User dataUser) {
		userDao.updatePassword(dataUser);
	}

}
