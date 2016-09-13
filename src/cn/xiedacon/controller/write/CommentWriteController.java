package cn.xiedacon.controller.write;

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
import cn.xiedacon.service.AlbumService;
import cn.xiedacon.service.CommentService;
import cn.xiedacon.service.SongListService;
import cn.xiedacon.service.SongMenuService;
import cn.xiedacon.service.SongService;
import cn.xiedacon.service.UserService;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.util.UUIDUtils;

@Controller
@ResponseBody
@RequestMapping("/comment")
public class CommentWriteController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private Factory factory;
	@Autowired
	private UserService userService;
	@Autowired
	private SongMenuService songMenuService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private SongListService songListService;
	@Autowired
	private SongService songService;

	@Transactional
	@RequestMapping(value = "/{type:\\w+}", method = RequestMethod.POST)
	public Map<String, Object> createSongMenuComment(@RequestParam("creatorId") String creatorId,
			@PathVariable("type") String type, @RequestParam("typeId") String typeId,
			@RequestParam("comment") String content) {
		User creator = userService.selectById(creatorId);
		if (creator == null) {
			return MessageUtils.createError("creatorId", "用户不存在");
		}
		Comment comment;
		switch (type) {
		case "songMenu":
			SongMenu songMenu = songMenuService.selectById(typeId);
			if (songMenu == null) {
				return MessageUtils.createError("songMenuId", "歌单不存在");
			}
			comment = getComment(creator, content);
			comment.setSongMenuId(songMenu.getId());

			songMenuService.updateCommentNumById(songMenu.getCommentNum() + 1, songMenu.getId());
			break;
		case "album":
			Album album = albumService.selectById(typeId);
			if (album == null) {
				return MessageUtils.createError("albumId", "专辑不存在");
			}
			comment = getComment(creator, content);
			comment.setAlbumId(album.getId());

			albumService.updateCommentNumById(album.getCommentNum() + 1, album.getId());
			break;
		case "songList":
			SongList songList = songListService.selectById(typeId);
			if (songList == null) {
				return MessageUtils.createError("songListId", "榜单不存在");
			}
			comment = getComment(creator, content);
			comment.setSongListId(songList.getId());

			songListService.updateCommentNumById(songList.getCommentNum() + 1, songList.getId());
			break;
		case "song":
			Song song = songService.selectById(typeId);
			if (song == null) {
				return MessageUtils.createError("songId", "歌曲不存在");
			}
			comment = getComment(creator, content);
			comment.setSongId(song.getId());

			songService.updateCommentNumById(song.getCommentNum() + 1, song.getId());
			break;
		default:
			return MessageUtils.createError("type", "类型不存在");
		}

		commentService.insertSongMenuComment(comment);
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
			return MessageUtils.createError("creatorId", "用户不存在");
		}

		Integer agreeNum;
		Comment comment;
		switch (type) {
		case "songMenu":
			comment = commentService.selectSongMenuCommentById(commentId);
			if (comment == null) {
				return MessageUtils.createError("commentId", "评论不存在");
			}
			agreeNum = getAgreeNum(session, comment);
			commentService.updateSongMenuCommentAgreeNumById(agreeNum, comment.getId());
			break;
		case "album":
			comment = commentService.selectAlbumCommentById(commentId);
			if (comment == null) {
				return MessageUtils.createError("commentId", "评论不存在");
			}
			agreeNum = getAgreeNum(session, comment);
			commentService.updateAlbumCommentAgreeNumById(agreeNum, comment.getId());
			break;
		case "songList":
			comment = commentService.selectSongListCommentById(commentId);
			if (comment == null) {
				return MessageUtils.createError("commentId", "评论不存在");
			}
			agreeNum = getAgreeNum(session, comment);
			commentService.updateSongListCommentAgreeNumById(agreeNum, comment.getId());
			break;
		case "song":
			comment = commentService.selectSongCommentById(commentId);
			if (comment == null) {
				return MessageUtils.createError("commentId", "评论不存在");
			}
			agreeNum = getAgreeNum(session, comment);
			commentService.updateSongCommentAgreeNumById(agreeNum, comment.getId());
			break;
		default:
			return MessageUtils.createError("type", "不存在的类型");
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
