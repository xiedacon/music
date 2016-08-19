package cn.xiedacon.service;

import java.util.List;

import cn.xiedacon.model.User;
import cn.xiedacon.vo.SimpleUserVo;

public interface UserService {

	List<SimpleUserVo> selectForIndex();

	User selectByPhone(String phone);

	User selectById(String id);

	void updateUsername(User dataUser);

	User selectByGitHubId(String githubId);

	void insertUser(User dataUser);

	void updatePassword(User dataUser);

}
