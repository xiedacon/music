package cn.xiedacon.write.dao;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.User;

public interface UserWriteDao {

	void updateUsername(User dataUser);

	void insertUser_base(User dataUser);

	void insertUser_record(User dataUser);

	void updatePassword(User dataUser);

	void deleteUser(User user);

	void updateCollectSongMenuNumById(@Param("collectSongMenuNum") Integer collectSongMenuNum, @Param("id") String id);

}
