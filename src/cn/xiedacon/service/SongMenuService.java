package cn.xiedacon.service;

import java.util.List;

import cn.xiedacon.util.PageBean;
import cn.xiedacon.vo.SimpleSongMenuVo;
import cn.xiedacon.vo.SongMenuVo;


public interface SongMenuService {

	List<SimpleSongMenuVo> selectForIndex();

	SongMenuVo selectSongMenuById(String id);

	PageBean<SimpleSongMenuVo> selectPageBean(Integer page);

	PageBean<SimpleSongMenuVo> selectPageBeanOrderByCollectionNum(Integer page);

	PageBean<SimpleSongMenuVo> selectPageBeanBySecondTagId(String secondTagId, Integer page);

	PageBean<SimpleSongMenuVo> selectPageBeanBySecondTagIdOrderByCollectionNum(String secondTagId, Integer page);

	List<SimpleSongMenuVo> selectListByCreatorId(String userId);

	List<SimpleSongMenuVo> selectListByCollectorId(String collectorId);

}
