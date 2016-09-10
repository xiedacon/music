package cn.xiedacon.controller.write;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
	private SongMenuService songMenuServcie;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private SongListService songListService;
	@Autowired
	private SongService songService;

	@Transactional
	@RequestMapping(value = "/songMenu", method = RequestMethod.POST)
	public Map<String, Object> createSongMenuComment(@RequestParam("creatorId") String creatorId,
			@RequestParam("typeId") String songMenuId, @RequestParam("comment") String content) {
		User creator = userService.selectById(creatorId);
		if (creator == null) {
			return MessageUtils.createError("creatorId", "用户不存在");
		}
		SongMenu songMenu = songMenuServcie.selectById(songMenuId);
		if (songMenu == null) {
			return MessageUtils.createError("songMenuId", "歌单不存在");
		}

		Comment comment = factory.get(Comment.class);
		comment.setId(UUIDUtils.randomUUID());
		comment.setContent(content);
		comment.setCreateTime(new Date());
		comment.setCreatorIcon(creator.getIcon());
		comment.setCreatorId(creator.getId());
		comment.setCreatorName(creator.getName());
		comment.setSongMenuId(songMenu.getId());

		commentService.insertSongMenuComment(comment);
		songMenuServcie.updateCommentNumById(songMenu.getCommentNum() + 1, songMenu.getId());
		return MessageUtils.createSuccess();
	}

	@Transactional
	@RequestMapping(value = "/album", method = RequestMethod.POST)
	public Map<String, Object> createAlbumComment(@RequestParam("creatorId") String creatorId,
			@RequestParam("typeId") String albumId, @RequestParam("comment") String content) {
		User creator = userService.selectById(creatorId);
		if (creator == null) {
			return MessageUtils.createError("creatorId", "用户不存在");
		}
		Album album = albumService.selectById(albumId);
		if (album == null) {
			return MessageUtils.createError("songMenuId", "专辑不存在");
		}

		Comment comment = factory.get(Comment.class);
		comment.setId(UUIDUtils.randomUUID());
		comment.setContent(content);
		comment.setCreateTime(new Date());
		comment.setCreatorIcon(creator.getIcon());
		comment.setCreatorId(creator.getId());
		comment.setCreatorName(creator.getName());
		comment.setAlbumId(album.getId());

		commentService.insertAlbumComment(comment);
		albumService.updateCommentNumById(album.getCommentNum() + 1, album.getId());
		return MessageUtils.createSuccess();
	}

	@Transactional
	@RequestMapping(value = "/songList", method = RequestMethod.POST)
	public Map<String, Object> createSongListComment(@RequestParam("creatorId") String creatorId,
			@RequestParam("typeId") String songListId, @RequestParam("comment") String content) {
		User creator = userService.selectById(creatorId);
		if (creator == null) {
			return MessageUtils.createError("creatorId", "用户不存在");
		}
		SongList songList = songListService.selectById(songListId);
		if (songList == null) {
			return MessageUtils.createError("songMenuId", "榜单不存在");
		}

		Comment comment = factory.get(Comment.class);
		comment.setId(UUIDUtils.randomUUID());
		comment.setContent(content);
		comment.setCreateTime(new Date());
		comment.setCreatorIcon(creator.getIcon());
		comment.setCreatorId(creator.getId());
		comment.setCreatorName(creator.getName());
		comment.setSongListId(songList.getId());

		commentService.insertSongListComment(comment);
		songListService.updateCommentNumById(songList.getCommentNum() + 1, songList.getId());
		return MessageUtils.createSuccess();
	}

	@Transactional
	@RequestMapping(value = "/song", method = RequestMethod.POST)
	public Map<String, Object> createSongComment(@RequestParam("creatorId") String creatorId,
			@RequestParam("typeId") String songId, @RequestParam("comment") String content) {
		User creator = userService.selectById(creatorId);
		if (creator == null) {
			return MessageUtils.createError("creatorId", "用户不存在");
		}
		Song song = songService.selectById(songId);
		if (song == null) {
			return MessageUtils.createError("songMenuId", "歌曲不存在");
		}

		Comment comment = factory.get(Comment.class);
		comment.setId(UUIDUtils.randomUUID());
		comment.setContent(content);
		comment.setCreateTime(new Date());
		comment.setCreatorIcon(creator.getIcon());
		comment.setCreatorId(creator.getId());
		comment.setCreatorName(creator.getName());
		comment.setSongId(song.getId());

		commentService.insertSongComment(comment);
		songService.updateCommentNumById(song.getCommentNum() + 1, song.getId());
		return MessageUtils.createSuccess();
	}

}
