package cn.xiedacon.admin.service;

import cn.xiedacon.model.User;
import cn.xiedacon.util.PageBean;

public interface AdminUserService {

	PageBean<User> selectPageBean(Integer page);

	User selectById(String id);

	void deleteUser(User user);

}
