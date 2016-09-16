package cn.xiedacon.controller.write;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.model.Album;
import cn.xiedacon.service.AlbumService;
import cn.xiedacon.util.MessageUtils;

@Controller
@ResponseBody
@RequestMapping("/album")
public class AlbumWriteController {

	@Autowired
	private AlbumService albumService;

	@RequestMapping(value = "/{songListId:\\w+}/playNum", method = RequestMethod.PUT)
	public Map<String, Object> updatePlayNumById(@PathVariable("songListId") String id) {
		Album album = albumService.selectById(id);
		if (album == null) {
			return MessageUtils.createError("album", "专辑不存在");
		}
		albumService.updatePlayNumById(album.getPlayNum() + 1, album.getId());
		return MessageUtils.createSuccess();
	}
}
