package cn.xiedacon.controller;

import java.io.File;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.factory.Factory;
import cn.xiedacon.model.SongMenu;
import cn.xiedacon.model.User;
import cn.xiedacon.service.SongMenuService;
import cn.xiedacon.service.UserService;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.util.UUIDUtils;
import cn.xiedacon.util.Base64UploadUtils;
import cn.xiedacon.util.ImageUtils;

@Controller
@ResponseBody
@RequestMapping("/songMenu")
public class SongMenuWriterController {

	@Autowired
	private SongMenuService songMenuService;
	@Autowired
	private Factory factory;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/{songMenuId:\\w{32}}", method = RequestMethod.DELETE)
	public Map<String, Object> deleteSongMenu(@PathVariable("songMenuId") String songMenuId) {
		SongMenu songMenu = songMenuService.selectById(songMenuId);
		if (songMenu == null) {
			return MessageUtils.createError("songMenu", "歌单不存在");
		} else {
			songMenuService.deleteById(songMenuId);
			return MessageUtils.createSuccess();
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> insertSongMenu(HttpServletRequest request) {
		String creatorId = request.getParameter("creatorId");
		String name = request.getParameter("name");

		if (creatorId == null || creatorId.matches("\\w{32}")) {
			MessageUtils.createError("name", "歌单名格式错误！");
		}

		User user = userService.selectById(creatorId);
		if (user == null) {
			return null;
		}

		SongMenu songMenu = factory.get(SongMenu.class);
		songMenu.setId(UUIDUtils.randomUUID());
		songMenu.setCreatorId(user.getId());
		songMenu.setCreatorName(user.getName());
		songMenu.setCreatorIcon(user.getIcon());
		Date createTime = new Date();
		songMenu.setCreateTime(createTime);
		songMenu.setName(name);

		songMenuService.insertSongMenu(songMenu);
		return MessageUtils.createSuccess("songMenu", songMenu);
	}

	@RequestMapping(value = "/{songMenuId:\\w{32}}/icon", method = RequestMethod.PUT)
	public Map<String, Object> updateIcon(HttpServletRequest request, @PathVariable String songMenuId) {
		Base64UploadUtils.parseRequest(request);
		Integer x1 = Base64UploadUtils.getInteger("x1");
		Integer y1 = Base64UploadUtils.getInteger("y1");
		Integer x2 = Base64UploadUtils.getInteger("x2");
		Integer y2 = Base64UploadUtils.getInteger("y2");
		Double width = Base64UploadUtils.getDouble("width");
		Double height = Base64UploadUtils.getDouble("height");
		String type = Base64UploadUtils.getString("type");

		String imageName = UUIDUtils.uuid(new Date().getTime()) + "." + type;
		String icon = "image/songMenu/" + imageName;
		File imageFile = Base64UploadUtils.getFile("image",
				request.getServletContext().getRealPath("image/songMenu") + "/" + imageName);

		Double widthRatio = Math.abs(x2 - x1) / width;
		Double heightRatio = Math.abs(y2 - y1) / height;
		Double xRatio = Math.min(x1, x2) / width;
		Double yRatio = Math.min(y1, y2) / height;
		ImageUtils.resize(imageFile, xRatio, yRatio, widthRatio, heightRatio);

		SongMenu songMenu = songMenuService.selectById(songMenuId);
		if (songMenu == null) {
			return MessageUtils.createError("songMenuId", "歌单不存在");
		}
		songMenuService.updateIconById(icon, songMenuId);
		songMenu.setIcon(icon);
		return MessageUtils.createSuccess("songMenu", songMenu);
	}
}
