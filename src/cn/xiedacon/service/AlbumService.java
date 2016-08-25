package cn.xiedacon.service;


import cn.xiedacon.model.Album;
import cn.xiedacon.util.PageBean;

public interface AlbumService {

	Album selectById(String id);

	PageBean<Album> selectPageBeanByTagIdOrderByCreateTimeLimit(String tagId, Integer page);

	PageBean<Album> selectPageBeanBySingerIdOrderByCreateTime(String singerId, Integer page);

}
