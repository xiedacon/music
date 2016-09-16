package cn.xiedacon.controller.read;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.model.SongList;
import cn.xiedacon.service.SongListService;

@Controller
@ResponseBody
@RequestMapping(value = "/songList", method = RequestMethod.GET)
public class SongListReadController {

	@Autowired
	private SongListService songListService;

	@RequestMapping("/{id:\\w+}")
	public SongList getById(@PathVariable("id") String id) {
		return songListService.selectById(id);
	}

	@RequestMapping("/s")
	public List<SongList> getList() {
		return songListService.selectList();
	}

}
