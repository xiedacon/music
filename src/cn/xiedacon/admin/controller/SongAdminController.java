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
import cn.xiedacon.util.CharsetUtils;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.util.ResourceLoader;
import cn.xiedacon.util.UUIDUtils;
import cn.xiedacon.util.ZipUtils;
import cn.xiedacon.util.excel.Cell;
import cn.xiedacon.util.excel.XSSFUtils;
import cn.xiedacon.util.upload.Base64FileItem;
import cn.xiedacon.util.upload.Base64UploadUtils;

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
		return MessageUtils.createSuccess(songService.selectPageBean(page));
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
		String nameLike = "%" + CharsetUtils.change(name, "ISO-8859-1", "UTF-8") + "%";
		return MessageUtils.createSuccess(songService.selectPageBeanByNameLike(page, nameLike));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.GET)
	public Map<String, Object> selectById(@PathVariable("id") String id) {
		return MessageUtils.createSuccess(songService.selectById(id));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.PUT)
	public Map<String, Object> update(@PathVariable("id") String id, HttpServletRequest request) {
		Song song = songService.selectById(id);
		if (song == null) {
			return MessageUtils.createError("id", "歌曲错误");
		}

		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);

		String name = fileItems.get("name").getString();
		String icon = song.getIcon();
		String time = null;
		String timeM = fileItems.get("timeM").getString();
		String timeS = fileItems.get("timeS").getString();
		String fileUri = song.getFileUri();
		String lrcUri = song.getLrcUri();
		String remark = fileItems.get("remark").getString();

		if (name == null || name.trim().isEmpty()) {
			return MessageUtils.createError("name", "歌曲名不能为空");
		}
		Base64FileItem iconItem = fileItems.get("icon");
		if (iconItem != null) {
			icon = UUIDUtils.uuid(new Date().getTime()) + "." + iconItem.getType();
			iconItem.getFile(ResourceLoader.getRealPath("image/song") + "/" + icon);
			icon = "image/song/" + icon;
			File oldIconFile = new File(ResourceLoader.getRealPath(song.getIcon()));
			// !!!!!!!
			oldIconFile.delete();
		}
		if (timeM != null && timeS != null && timeM.matches("[0-5]\\d") && timeS.matches("[0-5]\\d")) {
			time = timeM + ":" + timeS;
		} else {
			return MessageUtils.createError("time", "时间格式错误");
		}
		Base64FileItem songItem = fileItems.get("songFile");
		if (songItem != null) {
			fileUri = UUIDUtils.uuid(new Date().getTime()) + "." + songItem.getType();
			songItem.getFile(ResourceLoader.getRealPath("music") + "/" + fileUri);
			fileUri = "music/" + fileUri;
			File oldSongFile = new File(ResourceLoader.getRealPath(song.getFileUri()));
			// !!!!!!!
			oldSongFile.delete();
		}
		Base64FileItem lrcItem = fileItems.get("lrcFile");
		if (lrcItem != null) {
			lrcUri = UUIDUtils.uuid(new Date().getTime()) + "." + lrcItem.getType();
			lrcItem.getFile(ResourceLoader.getRealPath("lyrics") + "/" + lrcUri);
			lrcUri = "lyrics/" + lrcUri;
			File oldLrcFile = new File(ResourceLoader.getRealPath(song.getLrcUri()));
			// !!!!!!!
			oldLrcFile.delete();
		}
		Singer singer = singerService.selectByName(fileItems.get("singerName").getString());
		Album album = albumService.selectByName(fileItems.get("albumName").getString());

		song.setName(name);
		song.setIcon(icon);
		song.setTime(time);
		song.setFileUri(fileUri);
		song.setLrcUri(lrcUri);
		if (singer != null) {
			song.setSingerId(singer.getId());
			song.setSingerName(singer.getName());
		}
		if (album != null) {
			song.setAlbumId(album.getId());
			song.setAlbumName(album.getName());
		}
		song.setRemark(remark);

		songService.update(song);
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Map<String, Object> insert(HttpServletRequest request) {
		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);

		String name = fileItems.get("name").getString();
		String icon = null;
		String time = null;
		String timeM = fileItems.get("timeM").getString();
		String timeS = fileItems.get("timeS").getString();
		String fileUri = null;
		String lrcUri = null;
		String remark = fileItems.get("remark").getString();
		Singer singer = singerService.selectByName(fileItems.get("singerName").getString());
		Album album = albumService.selectByName(fileItems.get("albumName").getString());

		Base64FileItem iconItem = fileItems.get("icon");
		if (iconItem == null) {
			return MessageUtils.createError("icon", "不能为空");
		}
		icon = UUIDUtils.uuid(new Date().getTime()) + "." + iconItem.getType();
		iconItem.getFile(ResourceLoader.getRealPath("image/song") + "/" + icon);
		icon = "image/song/" + icon;
		if (timeM != null && timeS != null && timeM.matches("[0-5]\\d") && timeS.matches("[0-5]\\d")) {
			time = timeM + ":" + timeS;
		} else {
			return MessageUtils.createError("time", "时间格式错误");
		}
		Base64FileItem songItem = fileItems.get("songFile");
		if (songItem == null) {
			return MessageUtils.createError("songFile", "不能为空");
		}
		fileUri = UUIDUtils.uuid(new Date().getTime()) + "." + songItem.getType();
		songItem.getFile(ResourceLoader.getRealPath("music") + "/" + fileUri);
		fileUri = "music/" + fileUri;
		Base64FileItem lrcItem = fileItems.get("lrcFile");
		if (lrcItem != null) {
			lrcUri = UUIDUtils.uuid(new Date().getTime()) + "." + lrcItem.getType();
			lrcItem.getFile(ResourceLoader.getRealPath("lyrics") + "/" + lrcUri);
			lrcUri = "lyrics/" + lrcUri;
		}

		Song song = factory.get(Song.class);
		song.setId(UUIDUtils.randomUUID());
		song.setName(name);
		song.setIcon(icon);
		song.setTime(time);
		song.setFileUri(fileUri);
		song.setLrcUri(lrcUri);
		if (singer != null) {
			song.setSingerId(singer.getId());
			song.setSingerName(singer.getName());
		}
		if (album != null) {
			song.setAlbumId(album.getId());
			song.setAlbumName(album.getName());
		}
		song.setRemark(remark);

		songService.insert(song);
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "/excel", method = RequestMethod.POST)
	public Map<String, Object> batchInsert(HttpServletRequest request) {
		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);

		Base64FileItem excelItem = fileItems.get("excel");
		File excelFile = excelItem
				.getFile(ResourceLoader.getRealPath("temp") + "/" + UUIDUtils.randomUUID() + "." + excelItem.getType());
		List<List<Cell>> cellData = XSSFUtils.parse(excelFile, Constant.EXCEL_BEGINNUM, 8);
		// !!!!!!!
		excelFile.delete();
		Base64FileItem iconZipItem = fileItems.get("iconZip");
		File iconZipFile = iconZipItem.getFile(
				ResourceLoader.getRealPath("temp") + "/" + UUIDUtils.randomUUID() + "." + iconZipItem.getType());
		ZipUtils.upZip(iconZipFile, ResourceLoader.getRealPath("image/song"));
		// !!!!!!!
		iconZipFile.delete();
		Base64FileItem songZipItem = fileItems.get("songZip");
		File songZipFile = songZipItem.getFile(
				ResourceLoader.getRealPath("temp") + "/" + UUIDUtils.randomUUID() + "." + songZipItem.getType());
		ZipUtils.upZip(songZipFile, ResourceLoader.getRealPath("music"));
		// !!!!!!!
		songZipFile.delete();
		Base64FileItem lrcZipItem = fileItems.get("lrcZip");
		File lrcZipFile = lrcZipItem.getFile(
				ResourceLoader.getRealPath("temp") + "/" + UUIDUtils.randomUUID() + "." + lrcZipItem.getType());
		ZipUtils.upZip(lrcZipFile, ResourceLoader.getRealPath("lyrics"));
		// !!!!!!!
		lrcZipFile.delete();

		List<String> singerNameList = new ArrayList<>();
		List<String> albumNameList = new ArrayList<>();
		for (List<Cell> cells : cellData) {
			singerNameList.add(cells.get(6).getString());
			albumNameList.add(cells.get(7).getString());
		}

		Map<String, Singer> singerMap = singerService.batchSelectByName(singerNameList);
		Map<String, Album> albumMap = albumService.batchSelectByName(albumNameList);
		List<Song> songList = new ArrayList<>();
		for (List<Cell> cells : cellData) {
			String name = cells.get(1).getString();
			String icon = cells.get(2).getString();
			String time = cells.get(3).getString();
			String lrcUri = cells.get(4).getString();
			String fileUri = cells.get(5).getString();
			Singer singer = singerMap.get(cells.get(6).getString());
			Album album = albumMap.get(cells.get(7).getString());
			String remark = cells.get(8).getString();

			if (name == null || name.trim().isEmpty()) {
				return MessageUtils.createError("name", "歌曲名不能为空");
			}
			if (icon == null || icon.trim().isEmpty()) {
				return MessageUtils.createError("icon", "图片错误");
			}
			icon = "image/song/" + icon;
			if (!time.matches("[0-5]\\d:[0-5]\\d")) {
				return MessageUtils.createError("time", "时间格式错误");
			}
			if (lrcUri != null && !lrcUri.trim().isEmpty()) {
				lrcUri = "lyrics/" + lrcUri;
			}
			if (fileUri == null || fileUri.trim().isEmpty()) {
				return MessageUtils.createError("fileUri", "歌曲文件错误");
			}
			fileUri = "music/" + fileUri;

			Song song = factory.get(Song.class);
			song.setId(UUIDUtils.randomUUID());
			song.setName(name);
			song.setIcon(icon);
			song.setTime(time);
			song.setLrcUri(lrcUri);
			song.setFileUri(fileUri);
			if (singer != null) {
				song.setSingerId(singer.getId());
				song.setSingerName(singer.getName());
			}
			if (album != null) {
				song.setAlbumId(album.getId());
				song.setAlbumName(album.getName());
			}
			song.setRemark(remark);

			songList.add(song);
		}

		songService.batchInsert(songList);
		return MessageUtils.createSuccess();
	}
}
