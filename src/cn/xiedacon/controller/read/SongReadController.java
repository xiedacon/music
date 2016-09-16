package cn.xiedacon.controller.read;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.model.Song;
import cn.xiedacon.service.SongService;
import cn.xiedacon.util.Constant;

@Controller
@ResponseBody
@RequestMapping(value = "/song", method = RequestMethod.GET)
public class SongReadController {

	@Autowired
	private SongService songService;

	@RequestMapping("/{id:\\w+}")
	public Song getById(@PathVariable("id") String id) {
		return songService.selectById(id);
	}

	@RequestMapping("/s/albumId_{albumId:\\w+}")
	public List<Song> getListByAlbumId(@PathVariable("albumId") String albumId) {
		return songService.selectListByAlbumIdOrderByRank(albumId);
	}

	@RequestMapping("/s/songMenuId_{songMenuId:\\w+}")
	public List<Song> getListBySongMenuId(@PathVariable("songMenuId") String songMenuId) {
		return songService.selectListBySongMenuIdOrderByRank(songMenuId);
	}

	@RequestMapping("/s/songListId_{songListId:\\w+}")
	public List<Song> getListBySongListId(@PathVariable("songListId") String songListId) {
		return songService.selectListBySongListIdOrderByRank(songListId);
	}

	@RequestMapping("/s/singerId_{singerId:\\w+}")
	public List<Song> getListBySingerId(@PathVariable("singerId") String singerId) {
		return songService.selectListBySingerIdOrderByCollectionNumLimit(singerId, Constant.BEGIN_DEFAULT,
				Constant.SINGER_SHOW_SONGNUM);
	}
}
