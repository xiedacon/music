package cn.xiedacon.service;

import java.util.List;

import cn.xiedacon.model.SongMenu;
import cn.xiedacon.util.PageBean;

public interface SongMenuService {

	SongMenu selectById(String id);

	PageBean<SongMenu> selectPageBean(Integer page);

	PageBean<SongMenu> selectPageBeanOrderByCollectionNum(Integer page);

	PageBean<SongMenu> selectPageBeanBySecondTagId(String secondTagId, Integer page);

	PageBean<SongMenu> selectPageBeanBySecondTagIdOrderByCollectionNum(String secondTagId, Integer page);

	List<SongMenu> selectListByCreatorId(String userId);

	List<SongMenu> selectListByCollectorId(String collectorId);

	void insert(SongMenu songMenu);

	void delete(SongMenu songMenu);

	void update(SongMenu songMenu);

	void updateIconById(String icon, String songMenuId);

	void updatePlayNumById(Integer playNum, String songMenuId);

	void updateCommentNumById(Integer commentNum, String id);

}
