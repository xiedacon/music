package cn.xiedacon.admin.dao;

import java.util.List;

import cn.xiedacon.model.Singer;

public interface BatchSqlDao {

	void insertSinger(List<Singer> singerList);

}
