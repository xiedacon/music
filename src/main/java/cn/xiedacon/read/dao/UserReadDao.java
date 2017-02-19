package cn.xiedacon.read.dao;

import cn.xiedacon.model.User;

public interface UserReadDao {

	User selectByPhone(String phone);

	User selectById(String id);

	User selectByGitHubId(String githubId);

}
