package cn.xiedacon.read.dao;

import java.util.List;

import cn.xiedacon.model.User;

public interface UserReadDao {

	User selectByPhone(String phone);

	User selectById(String id);

	User selectByGitHubId(String githubId);

	int selectCount();

	List<User> selectListLimit(int i, int limit);

}
