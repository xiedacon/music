package cn.xiedacon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.User;
import cn.xiedacon.vo.SimpleUserVo;

public interface UserDao {

	List<SimpleUserVo> selectUserListByIds(@Param("userIds") String[] userIds);

	User selectByPhone(String phone);

	User selectById(String id);
	
	User selectByGitHubId(String githubId);
	
	void insertUser_base(User user);

	void insertUser_record(User user);

	void updateUsername(User dataUser);

	void updatePassword(User dataUser);

}
