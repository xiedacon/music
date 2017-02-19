package cn.xiedacon.admin.service;

import java.util.List;

import cn.xiedacon.model.FirstClassify;

public interface FirstClassifyAdminService {

	List<FirstClassify> selectList();

	FirstClassify selectById(String id);

	void delete(FirstClassify classify);

	void update(FirstClassify classify);

	void insert(FirstClassify classify);

}
