package cn.xiedacon.write.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.model.SongList;
import cn.xiedacon.read.service.SongListReadService;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.write.service.SongListWriteService;

@Controller
@ResponseBody
@RequestMapping("/songList")
public class SongListWriteController {

	@Autowired
	private SongListReadService songListReadService;
	@Autowired
	private SongListWriteService songListWriteService;

	@RequestMapping(value = "/{songListId:\\w+}/playNum", method = RequestMethod.PUT)
	public Map<String, Object> updatePlayNumById(@PathVariable("songListId") String id) {
		SongList songList = songListReadService.selectById(id);
		if (songList == null) {
			return MessageUtils.createError("songListId", "榜单不存在");
		}
		songListWriteService.updatePlayNumById(songList.getPlayNum() + 1, songList.getId());
		return MessageUtils.createSuccess();
	}
}
