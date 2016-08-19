package cn.xiedacon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.service.SongMenuService;
import cn.xiedacon.vo.SimpleSongMenuVo;

@Controller
@RequestMapping("/myMusic")
@ResponseBody
public class MyMusicController {

	@Autowired
	private SongMenuService songMenuService;
	
	@RequestMapping("/{creatorId:\\w+}/createdSongMenus")
	public List<SimpleSongMenuVo> getCreatedSongMenus(@PathVariable("creatorId") String creatorId){
		return songMenuService.selectListByCreatorId(creatorId);
	}
	
	@RequestMapping("/{collectorId:\\w+}/collectedSongMenus")
	public List<SimpleSongMenuVo> getCollectedSongMenus(@PathVariable("collectorId") String collectorId){
		return songMenuService.selectListByCollectorId(collectorId);
	}
}
