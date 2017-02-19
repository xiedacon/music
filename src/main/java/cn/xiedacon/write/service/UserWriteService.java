package cn.xiedacon.write.service;

import cn.xiedacon.model.User;

public interface UserWriteService {

	void updateUsernameById(String username, String id);

	void insert(User dataUser);

	void updatePasswordById(String password, String id);

	void delete(User user);

}
