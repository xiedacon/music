package cn.xiedacon.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.User;

public interface UserAdminDao {

	List<User> selectLimit(@Param("begin") int begin, @Param("limit") int limit);

	int selectCount();

	User selectById(String id);

	void delete(User user);

	int selectCountByNameLike(String name);

	List<User> selectByNameLikeLimit(@Param("name") String name, @Param("begin") int begin, @Param("limit") int limit);

	User selectExist(String id);

}
