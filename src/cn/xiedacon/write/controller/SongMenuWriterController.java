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
import cn.xiedacon.model.SongMenu_SongGL;
import cn.xiedacon.model.SongMenu_SongMenuTagGL;
import cn.xiedacon.model.User;
import cn.xiedacon.read.service.SongMenuReadService;
import cn.xiedacon.read.service.SongReadService;
import cn.xiedacon.read.service.UserReadService;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.util.ResourceLoader;
import cn.xiedacon.util.UUIDUtils;
import cn.xiedacon.util.upload.Base64FileItem;
import cn.xiedacon.util.upload.Base64UploadUtils;
import cn.xiedacon.write.service.SongMenu_SongGLService;
import cn.xiedacon.write.service.SecondSongMenuTagReadService;
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

	private SecondSongMenuTagReadService tagService;

	@RequestMapping(value = "/{id:\\w{32}}", method = RequestMethod.DELETE)
	public Map<String, Object> delete(@PathVariable("id") String id) {
		SongMenu songMenu = songMenuReadService.selectById(id);
		if (songMenu != null) {
			songMenuWriteService.delete(songMenu);
		}

		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "/{id:\\w{32}}", method = RequestMethod.PUT)
	public Map<String, Object> update(@PathVariable("id") String id, @RequestParam("name") String name,
			@RequestParam("introduction") String introduction, @RequestParam("tags") String tags) {
		SongMenu songMenu = songMenuReadService.selectById(id);
		if (songMenu == null) {
			return MessageUtils.createError("id", "歌单不存在");
		}
		List<SongMenu_SongMenuTagGL> glList = new ArrayList<>();

		if (name == null || name.trim().isEmpty()) {
			return MessageUtils.createError("name", "歌单名不能为空");
		}
		if (tags != null) {
			List<String> tagIdList = new ArrayList<>();
			for (String tagId : tags.split("-")) {
				tagIdList.add(tagId);
			}
			Map<String, SongMenuSecondTag> tagMap = tagService.batchSelectById(tagIdList);

			if (tagIdList.size() != tagMap.size()) {
				return MessageUtils.createError("tags", "标签错误");
			}
			for (Map.Entry<String, SongMenuSecondTag> entry : tagMap.entrySet()) {
				SongMenuSecondTag tag = entry.getValue();
				glList.add(new SongMenu_SongMenuTagGL(songMenu.getId(), tag.getId()));
			}
		}

		songMenu.setName(name);
		songMenu.setIntroduction(introduction);
		songMenu.setSongMenu_SongMenuTagGLList(glList);

		songMenuWriteService.update(songMenu);
		return MessageUtils.createSuccess(songMenu);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> insert(@RequestParam("name") String name, @RequestParam("creatorId") String creatorId) {
		User creator = userService.selectById(creatorId);
		if (creator == null) {
			return MessageUtils.createError("creatorId", "用户不存在！");
		}
		Date createTime = new Date();

		if (name == null || name.trim().isEmpty()) {
			return MessageUtils.createError("name", "歌单名不能为空");
		}

		SongMenu songMenu = factory.get(SongMenu.class);
		songMenu.setId(UUIDUtils.randomUUID());
		songMenu.setName(name);
		songMenu.setCreatorId(creator.getId());
		songMenu.setCreatorName(creator.getName());
		songMenu.setCreatorIcon(creator.getIcon());
		songMenu.setCreateTime(createTime);

		songMenuWriteService.insert(songMenu);
		return MessageUtils.createSuccess(songMenu);
	}

	@RequestMapping(value = "/{id:\\w{32}}/icon", method = RequestMethod.PUT)
	public Map<String, Object> updateIconById(HttpServletRequest request, @PathVariable("id") String id) {
		SongMenu songMenu = songMenuReadService.selectById(id);
		if (songMenu == null) {
			return MessageUtils.createError("id", "歌单不存在");
		}

		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);
		Integer x1 = fileItems.get("x1").getInteger();
		Integer y1 = fileItems.get("y1").getInteger();
		Integer x2 = fileItems.get("x2").getInteger();
		Integer y2 = fileItems.get("y2").getInteger();
		Double width = fileItems.get("width").getDouble();
		Double height = fileItems.get("height").getDouble();

		Base64FileItem imageFileItem = fileItems.get("image");
		String icon = UUIDUtils.uuid(new Date().getTime()) + "." + imageFileItem.getType();
		File imageFile = imageFileItem.getFile(ResourceLoader.getRealPath("image/songMenu") + "/" + icon);
		icon = "image/songMenu/" + icon;

		Double widthRatio = Math.abs(x2 - x1) / width;
		Double heightRatio = Math.abs(y2 - y1) / height;
		Double xRatio = Math.min(x1, x2) / width;
		Double yRatio = Math.min(y1, y2) / height;
		ImageUtils.resize(imageFile, xRatio, yRatio, widthRatio, heightRatio);

		songMenu.setIcon(icon);
		songMenuWriteService.updateIconById(icon, songMenu.getId());
		return MessageUtils.createSuccess(songMenu);
	}

	@RequestMapping(value = "/{id:\\w{32}}/song", method = RequestMethod.POST)
	public Map<String, Object> addSong(@RequestParam("songId") String songId, @PathVariable("id") String id) {
		SongMenu songMenu = songMenuReadService.selectById(id);
		if (songMenu == null) {
			return MessageUtils.createError("id", "歌单不存在");
		}

		Song song = songReadService.selectById(songId);
		if (song == null) {
			return MessageUtils.createError("songId", "歌曲不存在");
		}

		SongMenu_SongGL gl = songMenu_SongGLService.selectExistBySongIdAndSongMenuId(songId, id);
		if (gl == null) {
			songMenu_SongGLService
					.insert(new SongMenu_SongGL(songMenu.getId(), song.getId(), new Date(), songMenu.getSongNum() + 1));
			return MessageUtils.createSuccess();
		} else {
			return MessageUtils.createError("songId", "歌曲已在歌单中");
		}
	}

	@RequestMapping(value = "/{id:\\w+}/playNum", method = RequestMethod.PUT)
	public Map<String, Object> updatePlayNumById(@PathVariable("id") String id) {
		SongMenu songMenu = songMenuReadService.selectById(id);
		if (songMenu == null) {
			return MessageUtils.createError("id", "歌单不存在");
		}
		songMenuWriteService.updatePlayNumById(songMenu.getPlayNum() + 1, songMenu.getId());
		return MessageUtils.createSuccess();
	}
}
