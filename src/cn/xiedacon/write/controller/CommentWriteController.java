package cn.xiedacon.write.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.factory.Factory;
import cn.xiedacon.model.Album;
import cn.xiedacon.model.Comment;
import cn.xiedacon.model.Song;
import cn.xiedacon.model.SongList;
import cn.xiedacon.model.SongMenu;
import cn.xiedacon.model.User;
import cn.xiedacon.read.service.AlbumReadService;
import cn.xiedacon.read.service.CommentReadService;
import cn.xiedacon.read.service.SongListReadService;
import cn.xiedacon.read.service.SongMenuReadService;
import cn.xiedacon.read.service.SongReadService;
import cn.xiedacon.read.service.UserReadService;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.util.UUIDUtils;
import cn.xiedacon.write.service.CommentWriteService;

@Controller
@ResponseBody
@RequestMapping("/comment")
public class CommentWriteController {

	@Autowired
	private CommentReadService commentReadService;
	@Autowired
	private CommentWriteService commentWriteService;
	@Autowired
	private Factory factory;
	@Autowired
	private UserReadService userService;
	@Autowired
	private SongMenuReadService songMenuReadService;
	@Autowired
	private AlbumReadService albumReadService;
	@Autowired
	private SongListReadService songListReadService;
	@Autowired
	private SongReadService songReadService;

	@Transactional
	@RequestMapping(value = "/{type:\\w+}", method = RequestMethod.POST)
	public Map<String, Object> insertComment(@RequestParam("creatorId") String creatorId,
			@PathVariable("type") String type, @RequestParam("typeId") String typeId,
			@RequestParam("comment") String content) {
		User creator = userService.selectById(creatorId);
		if (creator == null) {
			return MessageUtils.createError("creatorId", "用户不存在");
		}

		Comment comment = factory.get(Comment.class);
		comment.setId(UUIDUtils.randomUUID());
		comment.setContent(content);
		comment.setCreateTime(new Date());
		comment.setCreatorIcon(creator.getIcon());
		comment.setCreatorId(creator.getId());
		comment.setCreatorName(creator.getName());

		switch (type) {
		case "songMenu":
			SongMenu songMenu = songMenuReadService.selectById(typeId);
			if (songMenu == null) {
				return MessageUtils.createError("typeId", "歌单不存在");
			}

			comment.setSongMenuId(songMenu.getId());
			comment.setTotal(songMenu.getCommentNum() + 1);
			commentWriteService.insertSongMenuComment(comment);
			break;
		case "album":
			Album album = albumReadService.selectById(typeId);
			if (album == null) {
				return MessageUtils.createError("typeId", "专辑不存在");
			}

			comment.setAlbumId(album.getId());
			comment.setTotal(album.getCommentNum() + 1);
			commentWriteService.insertAlbumComment(comment);
			break;
		case "songList":
			SongList songList = songListReadService.selectById(typeId);
			if (songList == null) {
				return MessageUtils.createError("typeId", "榜单不存在");
			}

			comment.setSongListId(songList.getId());
			comment.setTotal(songList.getCommentNum() + 1);
			commentWriteService.insertAlbumComment(comment);
			break;
		case "song":
			Song song = songReadService.selectById(typeId);
			if (song == null) {
				return MessageUtils.createError("typeId", "歌曲不存在");
			}

			comment.setSongId(song.getId());
			comment.setTotal(song.getCommentNum() + 1);
			commentWriteService.insertSongComment(comment);
			break;
		default:
			return MessageUtils.createError("类型不存在");
		}
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "/{id:\\w+}/{type:\\w+}", method = RequestMethod.PUT)
	public Map<String, Object> agreeComment(@RequestParam("creatorId") String creatorId, @PathVariable("id") String id,
			@PathVariable("type") String type) throws IOException {
		User creator = userService.selectById(creatorId);
		if (creator == null) {
			return MessageUtils.createError("creatorId", "用户不存在");
		}

		Comment comment;
		switch (type) {
		case "songMenu":
			comment = commentReadService.selectSongMenuCommentById(id);
			if (comment == null) {
				return MessageUtils.createError("id", "评论不存在");
			}

			commentWriteService.updateSongMenuCommentAgreeNumById(comment.getAgreeNum() + 1, comment.getId());
			break;
		case "album":
			comment = commentReadService.selectAlbumCommentById(id);
			if (comment == null) {
				return MessageUtils.createError("id", "评论不存在");
			}

			commentWriteService.updateAlbumCommentAgreeNumById(comment.getAgreeNum() + 1, comment.getId());
			break;
		case "songList":
			comment = commentReadService.selectSongListCommentById(id);
			if (comment == null) {
				return MessageUtils.createError("id", "评论不存在");
			}

			commentWriteService.updateSongListCommentAgreeNumById(comment.getAgreeNum() + 1, comment.getId());
			break;
		case "song":
			comment = commentReadService.selectSongCommentById(id);
			if (comment == null) {
				return MessageUtils.createError("id", "评论不存在");
			}

			commentWriteService.updateSongCommentAgreeNumById(comment.getAgreeNum() + 1, comment.getId());
			break;
		default:
			return MessageUtils.createError("type", "不存在的类型");
		}

		return MessageUtils.createSuccess();
	}
}
