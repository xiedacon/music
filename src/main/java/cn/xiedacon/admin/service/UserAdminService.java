package cn.xiedacon.admin.service;

import cn.xiedacon.model.User;
import cn.xiedacon.util.PageBean;

public interface UserAdminService {

	PageBean<User> selectPageBean(Integer page);

	User selectById(String id);

	void delete(User user);

	Object selectPageBeanByNameLike(Integer page, String name);

	User selectExist(String id);

}
