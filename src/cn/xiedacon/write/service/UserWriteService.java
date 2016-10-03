package cn.xiedacon.write.service;

import cn.xiedacon.model.User;

public interface UserWriteService {

	void updateUsername(User dataUser);

	void insertUser(User dataUser);

	void updatePassword(User dataUser);

	void deleteUser(User user);
}
