package cn.xiedacon.read.service;

import java.util.List;

import cn.xiedacon.model.SongMenu;
import cn.xiedacon.util.PageBean;

public interface SongMenuReadService {

	SongMenu selectById(String id);

	PageBean<SongMenu> selectPageBeanOrderByCreateTime(Integer page);

	PageBean<SongMenu> selectPageBeanOrderByCollectionNum(Integer page);

	PageBean<SongMenu> selectPageBeanBySecondTagIdOrderByCreateTime(String secondTagId, Integer page);

	PageBean<SongMenu> selectPageBeanBySecondTagIdOrderByCollectionNum(String secondTagId, Integer page);

	List<SongMenu> selectListByCreatorId(String creatorId);

	List<SongMenu> selectListByCollectorId(String collectorId);

}
