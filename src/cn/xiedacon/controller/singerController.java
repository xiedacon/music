package cn.xiedacon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.service.AlbumService;
import cn.xiedacon.service.SingerService;
import cn.xiedacon.service.SongService;
import cn.xiedacon.util.PageBean;
import cn.xiedacon.vo.SimpleAlbumVo;
import cn.xiedacon.vo.SimpleSingerVo;
import cn.xiedacon.vo.SingerVo;
import cn.xiedacon.vo.SongVo;

@Controller
@ResponseBody
public class singerController {

	@Autowired
	private SingerService singerService;
	@Autowired
	private SongService songService;
	@Autowired
	private AlbumService albumService;

	@RequestMapping(value = "/singers/all", method = RequestMethod.GET)
	public List<SimpleSingerVo> getSingerList() {
		return singerService.selectListLimit(0, 30);
	}

	@RequestMapping(value = "/singers/hot", method = RequestMethod.GET)
	public List<SimpleSingerVo> getHotSingerList() {
		return singerService.selectListOrderByCollectionNumLimit(0, 100);
	}

	@RequestMapping(value = "/singers/classify_{classifyId:\\d{4}}", method = RequestMethod.GET)
	public List<SimpleSingerVo> getSingerListByClassifyId(@PathVariable("classifyId") Integer classifyId) {
		return singerService.selectListByClassifyIdOrderByCollectionNumLimit(classifyId, 0, 100);
	}

	@RequestMapping(value = "/singer/{id:\\w+}", method = RequestMethod.GET)
	public SingerVo getSingerById(@PathVariable("id") String id) {
		return singerService.selectById(id);
	}

	@RequestMapping(value = "/singer/{singerId:\\w+}/songs", method = RequestMethod.GET)
	public List<SongVo> getSongListBySingerId(@PathVariable("singerId") String singerId) {
		return songService.selectListBySingerIdOrderByCollectionNumLimit(singerId, 0, 50);
	}

	@RequestMapping(value = "/singer/{sinerId:\\w+}/albums/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public PageBean<SimpleAlbumVo> getAlbumPageBeanBySingerId(@PathVariable("sinerId") String singerId,
			@PathVariable("page") Integer page) {
		return albumService.selectPageBeanBySingerIdOrderByCreateTime(singerId, page);
	}

	@RequestMapping(value = "/singer/{id:\\w+}/introduction", method = RequestMethod.GET)
	public Map<String, Object> getIntroductionById(@PathVariable("id") String id) {
		Map<String, Object> result = new HashMap<>();
		String introduction = singerService.selectIntroductionById(id);

		// 可能扩展为富文本
		result.put("code", 200);
		result.put("introduction", introduction);

		return result;
	}
}
