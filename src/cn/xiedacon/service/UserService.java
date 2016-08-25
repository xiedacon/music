package cn.xiedacon.service;


import cn.xiedacon.model.User;

public interface UserService {

	User selectByPhone(String phone);

	User selectById(String id);

	void updateUsername(User dataUser);

	User selectByGitHubId(String githubId);

	void insertUser(User dataUser);

	void updatePassword(User dataUser);

}
