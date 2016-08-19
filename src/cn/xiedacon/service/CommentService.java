package cn.xiedacon.service;

import java.util.List;

import cn.xiedacon.util.PageBean;
import cn.xiedacon.vo.CommentVo;

public interface CommentService {

	List<CommentVo> selectForHotBySongMenuId(String songMenuId);

	PageBean<CommentVo> selectPageBeanBySongMenuId(String songMenuId, Integer page);

	List<CommentVo> selectForHotByAlbumId(String albumId);

	PageBean<CommentVo> selectPageBeanByAlbumId(String albumId, Integer page);

	List<CommentVo> selectHotBySongListId(String songListId);

	PageBean<CommentVo> selectPageBeanBySongListIdLimit(String songListId, Integer page);

	List<CommentVo> selectForHotBySongId(String songId);

	PageBean<CommentVo> selectPageBeanBySongId(String songId, Integer page);


}
