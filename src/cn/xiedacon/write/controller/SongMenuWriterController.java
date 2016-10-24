package cn.xiedacon.write.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.factory.Factory;
import cn.xiedacon.model.Song;
import cn.xiedacon.model.SongMenu;
import cn.xiedacon.model.SongMenuSecondTag;
import cn.xiedacon.model.User;
import cn.xiedacon.read.service.SongMenuReadService;
import cn.xiedacon.read.service.SongReadService;
import cn.xiedacon.read.service.UserReadService;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.util.UUIDUtils;
import cn.xiedacon.util.upload.Base64FileItem;
import cn.xiedacon.util.upload.Base64UploadUtils;
import cn.xiedacon.write.service.SongMenu_SongGLService;
import cn.xiedacon.write.service.SongMenuWriteService;
import cn.xiedacon.util.ImageUtils;

@Controller
@ResponseBody
@RequestMapping("/songMenu")
public class SongMenuWriterController {

	@Autowired
	private SongMenuWriteService songMenuWriteService;
	@Autowired
	private SongMenuReadService songMenuReadService;
	@Autowired
	private SongReadService songReadService;
	@Autowired
	private Factory factory;
	@Autowired
	private UserReadService userService;
	@Autowired
	private SongMenu_SongGLService songMenu_SongGLService;

	@RequestMapping(value = "/{songMenuId:\\w{32}}", method = RequestMethod.DELETE)
	public Map<String, Object> deleteSongMenu(@PathVariable("songMenuId") String songMenuId) {
		SongMenu songMenu = songMenuReadService.selectById(songMenuId);
		if (songMenu == null) {
			return MessageUtils.createError("songMenu", "歌单不存在");
		} else {
			songMenuWriteService.delete(songMenu);
			return MessageUtils.createSuccess();
		}
	}

	@RequestMapping(value = "/{songMenuId:\\w{32}}", method = RequestMethod.PUT)
	public Map<String, Object> updateSongMenu(HttpServletRequest request,
			@PathVariable("songMenuId") String songMenuId) {
		SongMenu songMenu = songMenuReadService.selectById(songMenuId);
		if (songMenu == null) {
			return MessageUtils.createError("songMenuId", "歌单不存在");
		}

		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);

		String tags = fileItems.get("tags").getString();
		List<SongMenuSecondTag> songMenuSecondTagList = new ArrayList<>();
		for (String tagId : tags.split("-")) {
			songMenuSecondTagList.add(new SongMenuSecondTag(tagId));
		}
		songMenu.setName(fileItems.get("name").getString());
		songMenu.setIntroduction(fileItems.get("introduction").getString());
		songMenu.setSongMenuSecondTagList(songMenuSecondTagList);
		songMenuWriteService.update(songMenu);
		return MessageUtils.createSuccess("songMenu", songMenu);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> insertSongMenu(HttpServletRequest request) {
		String creatorId = request.getParameter("creatorId");
		String name = request.getParameter("name");

		if (creatorId == null || creatorId.matches("\\w{32}")) {
			return MessageUtils.createError("name", "歌单名格式错误！");
		}

		User user = userService.selectById(creatorId);
		if (user == null) {
			return MessageUtils.createError("creatorId", "用户不存在！");
		}

		SongMenu songMenu = factory.get(SongMenu.class);
		songMenu.setId(UUIDUtils.randomUUID());
		songMenu.setCreatorId(user.getId());
		songMenu.setCreatorName(user.getName());
		songMenu.setCreatorIcon(user.getIcon());
		Date createTime = new Date();
		songMenu.setCreateTime(createTime);
		songMenu.setName(name);

		songMenuWriteService.insert(songMenu);
		return MessageUtils.createSuccess("songMenu", songMenu);
	}

	@RequestMapping(value = "/{songMenuId:\\w{32}}/icon", method = RequestMethod.PUT)
	public Map<String, Object> updateIcon(HttpServletRequest request, @PathVariable String songMenuId) {
		SongMenu songMenu = songMenuReadService.selectById(songMenuId);
		if (songMenu == null) {
			return MessageUtils.createError("songMenuId", "歌单不存在");
		}

		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);
		Integer x1 = fileItems.get("x1").getInteger();
		Integer y1 = fileItems.get("y1").getInteger();
		Integer x2 = fileItems.get("x2").getInteger();
		Integer y2 = fileItems.get("y2").getInteger();
		Double width = fileItems.get("width").getDouble();
		Double height = fileItems.get("height").getDouble();

		Base64FileItem imageFileItem = fileItems.get("image");
		String imageName = UUIDUtils.uuid(new Date().getTime()) + "." + imageFileItem.getType();
		String icon = "image/songMenu/" + imageName;
		File imageFile = imageFileItem
				.getFile(request.getServletContext().getRealPath("image/songMenu") + "/" + imageName);

		Double widthRatio = Math.abs(x2 - x1) / width;
		Double heightRatio = Math.abs(y2 - y1) / height;
		Double xRatio = Math.min(x1, x2) / width;
		Double yRatio = Math.min(y1, y2) / height;
		ImageUtils.resize(imageFile, xRatio, yRatio, widthRatio, heightRatio);

		songMenu.setIcon(icon);
		songMenuWriteService.updateIconById(icon, songMenuId);
		return MessageUtils.createSuccess("songMenu", songMenu);
	}

	@RequestMapping(value = "/{songMenuId:\\w{32}}/song", method = RequestMethod.POST)
	public Map<String, Object> addSong(@RequestParam("songId") String songId,
			@PathVariable("songMenuId") String songMenuId) {
		SongMenu songMenu = songMenuReadService.selectById(songMenuId);
		if (songMenu == null) {
			return MessageUtils.createError("songMenuId", "歌单不存在");
		}
		Song song = songReadService.selectById(songId);
		if (song == null) {
			return MessageUtils.createError("songMenuId", "歌曲不存在");
		}
		String id = songMenu_SongGLService.selectIdBySongIdAndSongMenuId(songId, songMenuId);
		if (id == null) {
			songMenu.setSongNum(songMenu.getSongNum() + 1);
			songMenu_SongGLService.insert(songMenu, song);
			return MessageUtils.createSuccess();
		} else {
			return MessageUtils.createError("songId", "歌曲已存在");
		}
	}

	@RequestMapping(value = "/{songMenuId:\\w+}/playNum", method = RequestMethod.PUT)
	public Map<String, Object> updatePlayNumById(@PathVariable("songMenuId") String songMenuId) {
		SongMenu songMenu = songMenuReadService.selectById(songMenuId);
		if (songMenu == null) {
			return MessageUtils.createError("songMenuId", "歌单不存在");
		}
		songMenuWriteService.updatePlayNumById(songMenu.getPlayNum() + 1, songMenu.getId());
		return MessageUtils.createSuccess();
	}
}
