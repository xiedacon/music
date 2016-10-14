package cn.xiedacon.admin.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.admin.service.AlbumAdminService;
import cn.xiedacon.admin.service.SingerAdminService;
import cn.xiedacon.admin.service.SongAdminService;
import cn.xiedacon.factory.Factory;
import cn.xiedacon.model.Album;
import cn.xiedacon.model.Singer;
import cn.xiedacon.model.Song;
import cn.xiedacon.util.Base64FileItem;
import cn.xiedacon.util.Base64UploadUtils;
import cn.xiedacon.util.CharsetUtils;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.util.UUIDUtils;
import cn.xiedacon.util.XSSFUtils;
import cn.xiedacon.util.ZipUtils;

@Controller
@ResponseBody
@RequestMapping("/admin/song")
public class SongAdminController {

	@Autowired
	private SongAdminService songService;
	@Autowired
	private SingerAdminService singerService;
	@Autowired
	private AlbumAdminService albumService;
	@Autowired
	private Factory factory;

	@RequestMapping(value = "/page/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public Map<String, Object> selectPageBean(@PathVariable("page") Integer page) {
		return MessageUtils.createSuccess(Constant.SUCCESS_RETURNNAME, songService.selectPageBean(page));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.DELETE)
	public Map<String, Object> delete(@PathVariable("id") String id) {
		Song song = songService.selectExist(id);
		if (song != null) {
			songService.delete(song);
		}
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "/name/{name:.+}/page/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public Map<String, Object> selectPageBeanByNameLike(@PathVariable("name") String name,
			@PathVariable("page") Integer page) {
		return MessageUtils.createSuccess(Constant.SUCCESS_RETURNNAME, songService.selectPageBeanByNameLike(page,
				"%" + CharsetUtils.decode(name, "ISO-8859-1", "UTF-8") + "%"));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.GET)
	public Map<String, Object> selectById(@PathVariable("id") String id) {
		return MessageUtils.createSuccess(Constant.SUCCESS_RETURNNAME, songService.selectById(id));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.PUT)
	public Map<String, Object> update(@PathVariable("id") String id, HttpServletRequest request) {
		Song song = songService.selectById(id);
		if (song == null) {
			return MessageUtils.createError("未知错误");
		}

		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);

		song.setName(fileItems.get("name").getString());

		Base64FileItem iconItem = fileItems.get("icon");
		if (iconItem != null) {
			String iconName = UUIDUtils.uuid(new Date().getTime()) + "." + iconItem.getType();
			iconItem.getFile(request.getServletContext().getRealPath("image/song") + "/" + iconName);
			File oldIconFile = new File(request.getServletContext().getRealPath(song.getIcon()));
			oldIconFile.delete();
			song.setIcon("image/song/" + iconName);
		}

		String timeM = fileItems.get("timeM").getString();
		String timeS = fileItems.get("timeS").getString();
		song.setTime(timeM + ":" + timeS);

		Base64FileItem songItem = fileItems.get("songFile");
		if (songItem != null) {
			String songName = UUIDUtils.uuid(new Date().getTime()) + "." + songItem.getType();
			songItem.getFile(request.getServletContext().getRealPath("music") + "/" + songName);
			File oldSongFile = new File(request.getServletContext().getRealPath(song.getFileUri()));
			oldSongFile.delete();
			song.setFileUri("music/" + songName);
		}

		Base64FileItem lrcItem = fileItems.get("lrcFile");
		if (lrcItem != null) {
			String lrcName = UUIDUtils.uuid(new Date().getTime()) + "." + lrcItem.getType();
			lrcItem.getFile(request.getServletContext().getRealPath("lyrics") + "/" + lrcName);
			File oldLrcFile = new File(request.getServletContext().getRealPath(song.getLrcUri()));
			oldLrcFile.delete();
			song.setLrcUri("lyrics/" + lrcName);
		}

		Singer singer = singerService.selectByName(fileItems.get("singerName").getString());
		if (singer != null) {
			song.setSingerId(singer.getId());
			song.setSingerName(singer.getName());
		}

		Album album = albumService.selectByName(fileItems.get("albumName").getString());
		if (album != null) {
			song.setAlbumId(album.getId());
			song.setAlbumName(album.getName());
		}

		song.setRemark(fileItems.get("remark").getString());

		songService.update(song);
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Map<String, Object> insert(HttpServletRequest request) {
		Song song = factory.get(Song.class);
		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);

		Base64FileItem iconItem = fileItems.get("icon");
		if (iconItem == null) {
			return MessageUtils.createError("icon", "不能为空");
		}
		Base64FileItem songItem = fileItems.get("songFile");
		if (songItem == null) {
			return MessageUtils.createError("songFile", "不能为空");
		}
		Base64FileItem lrcItem = fileItems.get("lrcFile");

		String iconName = UUIDUtils.uuid(new Date().getTime()) + "." + iconItem.getType();
		iconItem.getFile(request.getServletContext().getRealPath("image/song") + "/" + iconName);

		String timeM = fileItems.get("timeM").getString();
		String timeS = fileItems.get("timeS").getString();

		String songName = UUIDUtils.uuid(new Date().getTime()) + "." + songItem.getType();
		songItem.getFile(request.getServletContext().getRealPath("music") + "/" + songName);

		if(lrcItem != null){
			String lrcName = UUIDUtils.uuid(new Date().getTime()) + "." + lrcItem.getType();
			lrcItem.getFile(request.getServletContext().getRealPath("lyrics") + "/" + lrcName);
			song.setLrcUri("lyrics/" + lrcName);
		}

		Singer singer = singerService.selectByName(fileItems.get("singerName").getString());
		Album album = albumService.selectByName(fileItems.get("albumName").getString());

		song.setId(UUIDUtils.randomUUID());
		song.setName(fileItems.get("name").getString());
		song.setIcon("image/song/" + iconName);
		song.setTime(timeM + ":" + timeS);
		song.setFileUri("music/" + songName);
		if (singer != null) {
			song.setSingerId(singer.getId());
			song.setSingerName(singer.getName());
		}
		if (album != null) {
			song.setAlbumId(album.getId());
			song.setAlbumName(album.getName());
		}
		song.setRemark(fileItems.get("remark").getString());

		songService.insert(song);

		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "/excel", method = RequestMethod.POST)
	public Map<String, Object> batchInsert(HttpServletRequest request) {
		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);

		Base64FileItem excelItem = fileItems.get("excel");
		File excelFile = excelItem.getFile(request.getServletContext().getRealPath("temp") + "/"
				+ UUIDUtils.randomUUID() + "." + excelItem.getType());
		List<List<String>> excelSongList = XSSFUtils.parse(excelFile, Constant.EXCEL_BEGINNUM, 8);
		excelFile.delete();

		Base64FileItem iconZipItem = fileItems.get("iconZip");
		File iconZipFile = iconZipItem.getFile(request.getServletContext().getRealPath("temp") + "/"
				+ UUIDUtils.randomUUID() + "." + iconZipItem.getType());
		ZipUtils.upZip(iconZipFile, request.getServletContext().getRealPath("image/song"));
		iconZipFile.delete();

		Base64FileItem songZipItem = fileItems.get("songZip");
		File songZipFile = songZipItem.getFile(request.getServletContext().getRealPath("temp") + "/"
				+ UUIDUtils.randomUUID() + "." + songZipItem.getType());
		ZipUtils.upZip(songZipFile, request.getServletContext().getRealPath("music"));
		songZipFile.delete();

		Base64FileItem lrcZipItem = fileItems.get("lrcZip");
		File lrcZipFile = lrcZipItem.getFile(request.getServletContext().getRealPath("temp") + "/"
				+ UUIDUtils.randomUUID() + "." + lrcZipItem.getType());
		ZipUtils.upZip(lrcZipFile, request.getServletContext().getRealPath("lyrics"));
		lrcZipFile.delete();

		List<Song> songList = new ArrayList<>();
		List<String> singerNameList = new ArrayList<>();
		List<String> albumNameList = new ArrayList<>();

		for (List<String> excelSong : excelSongList) {
			singerNameList.add(excelSong.get(6));
			albumNameList.add(excelSong.get(7));
		}

		Map<String, Singer> singerMap = singerService.batchSelectByName(singerNameList);
		Map<String, Album> albumMap = albumService.batchSelectByName(albumNameList);

		for (int i = 0; i < excelSongList.size(); i++) {
			List<String> excelSong = excelSongList.get(i);

			Song song = factory.get(Song.class);
			song.setId(UUIDUtils.randomUUID());
			song.setName(excelSong.get(1));
			song.setIcon("image/song/" + excelSong.get(2));
			song.setTime(excelSong.get(3));
			String lrcUri = excelSong.get(4);
			if (lrcUri != null) {
				song.setLrcUri("lyric/" + lrcUri);
			}
			song.setFileUri("music/" + excelSong.get(5));
			Singer singer = singerMap.get(singerNameList.get(i));
			if (singer != null) {
				song.setSingerId(singer.getId());
				song.setSingerName(singer.getName());
			}
			Album album = albumMap.get(albumNameList.get(i));
			if (album != null) {
				song.setAlbumId(album.getId());
				song.setAlbumName(album.getName());
			}
			song.setRemark(excelSong.get(8));
			songList.add(song);
		}

		songService.batchInsert(songList);
		return MessageUtils.createSuccess();
	}
}
