package cn.xiedacon.admin.service;

import java.util.List;
import java.util.Map;

import cn.xiedacon.model.SecondClassify;

public interface SecondClassifyAdminService {

	List<SecondClassify> selectList();

	SecondClassify selectById(String id);
	
	void delete(SecondClassify secondClassify);

	void update(SecondClassify secondClassify);

	void insert(SecondClassify secondClassify);

	Map<String, SecondClassify> batchSelectById(List<String> classifyIdList);

}
