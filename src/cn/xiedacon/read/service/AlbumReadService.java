package cn.xiedacon.read.service;

import cn.xiedacon.model.Album;
import cn.xiedacon.util.PageBean;

public interface AlbumReadService {

	Album selectById(String id);

	PageBean<Album> selectPageBeanOrderByCreateTime(Integer page);

	PageBean<Album> selectPageBeanByTagIdOrderByCreateTime(String tagId, Integer page);

	PageBean<Album> selectPageBeanBySingerIdOrderByCreateTime(String singerId, Integer page);
}
