package cn.xiedacon.admin.dao;

import java.util.List;

import cn.xiedacon.model.SecondClassify;

public interface SecondClassifyAdminDao {

	void insert(SecondClassify secondClassify);

	void update(SecondClassify secondClassify);

	void delete(SecondClassify secondClassify);

	SecondClassify selectById(String id);

	List<SecondClassify> selectList();

}
