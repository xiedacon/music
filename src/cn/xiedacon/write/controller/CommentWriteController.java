package cn.xiedacon.write.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
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
	public Map<String, Object> createSongMenuComment(@RequestParam("creatorId") String creatorId,
			@PathVariable("type") String type, @RequestParam("typeId") String typeId,
			@RequestParam("comment") String content) {
		User creator = userService.selectById(creatorId);
		if (creator == null) {
			return MessageUtils.createError("用户不存在");
		}
		Comment comment;
		switch (type) {
		case "songMenu":
			SongMenu songMenu = songMenuReadService.selectById(typeId);
			if (songMenu == null) {
				return MessageUtils.createError("歌单不存在");
			}
			comment = getComment(creator, content);
			comment.setSongMenu(songMenu);

			commentWriteService.insertSongMenuComment(comment);
			break;
		case "album":
			Album album = albumReadService.selectById(typeId);
			if (album == null) {
				return MessageUtils.createError("专辑不存在");
			}
			comment = getComment(creator, content);
			comment.setAlbum(album);

			commentWriteService.insertAlbumComment(comment);
			break;
		case "songList":
			SongList songList = songListReadService.selectById(typeId);
			if (songList == null) {
				return MessageUtils.createError("榜单不存在");
			}
			comment = getComment(creator, content);
			comment.setSongList(songList);

			commentWriteService.insertAlbumComment(comment);
			break;
		case "song":
			Song song = songReadService.selectById(typeId);
			if (song == null) {
				return MessageUtils.createError("歌曲不存在");
			}
			comment = getComment(creator, content);
			comment.setSong(song);

			commentWriteService.insertSongComment(comment);
			break;
		default:
			return MessageUtils.createError("类型不存在");
		}
		return MessageUtils.createSuccess();
	}

	private Comment getComment(User creator, String content) {
		Comment comment = factory.get(Comment.class);
		comment.setId(UUIDUtils.randomUUID());
		comment.setContent(content);
		comment.setCreateTime(new Date());
		comment.setCreatorIcon(creator.getIcon());
		comment.setCreatorId(creator.getId());
		comment.setCreatorName(creator.getName());
		return comment;
	}

	@RequestMapping(value = "/{commentId:\\w+}/{type:\\w+}", method = RequestMethod.PUT)
	public Map<String, Object> agreeComment(String creatorId, @PathVariable("commentId") String commentId,
			@PathVariable("type") String type, HttpSession session, HttpServletRequest request) throws IOException {
		creatorId = IOUtils.toString(request.getInputStream()).split("=")[1];
		User creator = userService.selectById(creatorId);
		if (creator == null) {
			return MessageUtils.createError("用户不存在");
		}

		Integer agreeNum;
		Comment comment;
		switch (type) {
		case "songMenu":
			comment = commentReadService.selectSongMenuCommentById(commentId);
			if (comment == null) {
				return MessageUtils.createError("评论不存在");
			}
			agreeNum = getAgreeNum(session, comment);
			commentWriteService.updateSongMenuCommentAgreeNumById(agreeNum, comment.getId());
			break;
		case "album":
			comment = commentReadService.selectAlbumCommentById(commentId);
			if (comment == null) {
				return MessageUtils.createError("评论不存在");
			}
			agreeNum = getAgreeNum(session, comment);
			commentWriteService.updateAlbumCommentAgreeNumById(agreeNum, comment.getId());
			break;
		case "songList":
			comment = commentReadService.selectSongListCommentById(commentId);
			if (comment == null) {
				return MessageUtils.createError("评论不存在");
			}
			agreeNum = getAgreeNum(session, comment);
			commentWriteService.updateSongListCommentAgreeNumById(agreeNum, comment.getId());
			break;
		case "song":
			comment = commentReadService.selectSongCommentById(commentId);
			if (comment == null) {
				return MessageUtils.createError("评论不存在");
			}
			agreeNum = getAgreeNum(session, comment);
			commentWriteService.updateSongCommentAgreeNumById(agreeNum, comment.getId());
			break;
		default:
			return MessageUtils.createError("不存在的类型");
		}

		return MessageUtils.createSuccess();
	}

	private Integer getAgreeNum(HttpSession session, Comment comment) {
		Integer agreeNum;
		Boolean flag = (Boolean) session.getAttribute("flag_agreed_" + comment.getId());
		if (flag == null) {
			agreeNum = comment.getAgreeNum() + 1;
			session.setAttribute("flag_agreed_" + comment.getId(), true);
		} else {
			agreeNum = comment.getAgreeNum() - 1;
			session.removeAttribute("flag_agreed_" + comment.getId());
		}
		return agreeNum;
	}
}
