package cn.xiedacon.write.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.model.Album;
import cn.xiedacon.read.service.AlbumReadService;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.write.service.AlbumWriteService;

@Controller
@ResponseBody
@RequestMapping("/album")
public class AlbumWriteController {

	@Autowired
	private AlbumWriteService albumWriteService;
	@Autowired
	private AlbumReadService albumReadService;

	@RequestMapping(value = "/{id:\\w+}/playNum", method = RequestMethod.PUT)
	public Map<String, Object> updatePlayNumById(@PathVariable("id") String id) {
		Album album = albumReadService.selectById(id);
		if (album == null) {
			return MessageUtils.createError("id", "专辑不存在");
		}
		albumWriteService.updatePlayNumById(album.getPlayNum() + 1, album.getId());
		return MessageUtils.createSuccess();
	}
}
