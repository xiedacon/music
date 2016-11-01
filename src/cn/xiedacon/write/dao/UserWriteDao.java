package cn.xiedacon.write.dao;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.User;

public interface UserWriteDao {

	void insertUser_base(User dataUser);

	void insertUser_record(User dataUser);

	void delete(User user);

	void updateCollectSongMenuNumById(@Param("collectSongMenuNum") Integer collectSongMenuNum, @Param("id") String id);

	void updatePasswordById(@Param("password") String password, @Param("id") String id);

	void updateUsernameById(@Param("username") String username, @Param("id") String id);

}
