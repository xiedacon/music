package cn.xiedacon.service;

import java.util.List;

import cn.xiedacon.model.Comment;
import cn.xiedacon.util.PageBean;

public interface CommentService {

	List<Comment> selectForHotBySongMenuId(String songMenuId);

	PageBean<Comment> selectPageBeanBySongMenuId(String songMenuId, Integer page);

	List<Comment> selectForHotByAlbumId(String albumId);

	PageBean<Comment> selectPageBeanByAlbumId(String albumId, Integer page);

	List<Comment> selectHotBySongListId(String songListId);

	PageBean<Comment> selectPageBeanBySongListIdLimit(String songListId, Integer page);

	List<Comment> selectForHotBySongId(String songId);

	PageBean<Comment> selectPageBeanBySongId(String songId, Integer page);

	void insertSongMenuComment(Comment comment);

	void insertAlbumComment(Comment comment);

	void insertSongComment(Comment comment);

	void insertSongListComment(Comment comment);


}
