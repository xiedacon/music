package cn.xiedacon.read.service;

import cn.xiedacon.model.Album;
import cn.xiedacon.util.PageBean;

public interface AlbumReadService {

	Album selectById(String id);
	
	PageBean<Album> selectPageBeanByTagIdOrderByCreateTimeLimit(String tagId, Integer page);

	PageBean<Album> selectPageBeanBySingerIdOrderByCreateTimeLimit(String singerId, Integer page);
}
