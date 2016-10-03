package cn.xiedacon.read.service;

import cn.xiedacon.model.User;

public interface UserReadService {

	User selectByPhone(String phone);

	User selectById(String id);

	User selectByGitHubId(String githubId);

}
