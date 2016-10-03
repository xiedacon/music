package cn.xiedacon.read.service;

import cn.xiedacon.model.User;
import cn.xiedacon.util.PageBean;

public interface UserReadService {

	User selectByPhone(String phone);

	User selectById(String id);

	User selectByGitHubId(String githubId);

	PageBean<User> selectPageBean(Integer page);
}
