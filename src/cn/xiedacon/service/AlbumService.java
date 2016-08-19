package cn.xiedacon.service;

import java.util.List;

import cn.xiedacon.util.PageBean;
import cn.xiedacon.vo.AlbumVo;
import cn.xiedacon.vo.SimpleAlbumVo;

public interface AlbumService {

	AlbumVo selectById(String id);

	PageBean<SimpleAlbumVo> selectPageBeanByTagIdOrderByCreateTimeLimit(String tagId, Integer page);

	List<SimpleAlbumVo> selectHotList();

	PageBean<SimpleAlbumVo> selectPageBeanBySingerIdOrderByCreateTime(String singerId, Integer page);

}
