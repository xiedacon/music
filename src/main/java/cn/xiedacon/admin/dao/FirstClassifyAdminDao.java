package cn.xiedacon.admin.dao;

import java.util.List;

import cn.xiedacon.model.FirstClassify;

public interface FirstClassifyAdminDao {

	List<FirstClassify> selectList();

	FirstClassify selectById(String id);

	void delete(FirstClassify classify);

	void update(FirstClassify classify);

	void insert(FirstClassify classify);

}
