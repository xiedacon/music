package cn.xiedacon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.User;

public interface UserDao {

	User selectByPhone(String phone);

	User selectById(String id);

	User selectByGitHubId(String githubId);

	void insertUser_base(User user);

	void insertUser_record(User user);

	void updateUsername(User dataUser);

	void updatePassword(User dataUser);

	void updateCollectSongMenuNumById(@Param("collectSongMenuNum") Integer collectSongMenuNum, @Param("id") String id);

	int selectCount();

	List<User> selectListLimit(@Param("begin") int begin, @Param("limit") int limit);

	void deleteUser(User user);

}
