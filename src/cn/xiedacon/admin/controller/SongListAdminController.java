package cn.xiedacon.admin.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.admin.service.SongAdminService;
import cn.xiedacon.admin.service.SongListAdminService;
import cn.xiedacon.admin.service.SongList_SongGLService;
import cn.xiedacon.factory.Factory;
import cn.xiedacon.model.Song;
import cn.xiedacon.model.SongList;
import cn.xiedacon.model.SongList_SongGL;
import cn.xiedacon.util.CharsetUtils;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.util.ResourceLoader;
import cn.xiedacon.util.UUIDUtils;
import cn.xiedacon.util.download.DownLoadUtlils;
import cn.xiedacon.util.excel.Cell;
import cn.xiedacon.util.excel.XSSFUtils;
import cn.xiedacon.util.file.FileUtils;
import cn.xiedacon.util.upload.Base64FileItem;
import cn.xiedacon.util.upload.Base64UploadUtils;

@Controller
@ResponseBody
@RequestMapping("/admin/songList")
public class SongListAdminController {

	@Autowired
	private SongListAdminService songListService;
	@Autowired
	private SongList_SongGLService songList_SongGLService;
	@Autowired
	private SongAdminService songService;
	@Autowired
	private Factory factory;

	@RequestMapping(value = "/page/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public Map<String, Object> selectPageBean(@PathVariable("page") Integer page) {
		return MessageUtils.createSuccess(Constant.SUCCESS_RETURNNAME, songListService.selectPageBean(page));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.DELETE)
	public Map<String, Object> delete(@PathVariable("id") String id) {
		SongList songList = songListService.selectExist(id);
		if (songList != null) {
			songListService.delete(songList);
		}
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "/name/{name:.+}/page/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public Map<String, Object> selectPageBeanByNameLike(@PathVariable("name") String name,
			@PathVariable("page") Integer page) {
		String nameLike = "%" + CharsetUtils.change(name, "ISO-8859-1", "UTF-8") + "%";
		return MessageUtils.createSuccess(Constant.SUCCESS_RETURNNAME,
				songListService.selectPageBeanByNameLike(page, nameLike));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.GET)
	public Map<String, Object> selectById(@PathVariable("id") String id) {
		return MessageUtils.createSuccess(Constant.SUCCESS_RETURNNAME, songListService.selectById(id));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.PUT)
	public Map<String, Object> update(@PathVariable("id") String id, HttpServletRequest request) {
		SongList songList = songListService.selectById(id);
		if (songList == null) {
			return MessageUtils.createError("未知错误");
		}

		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);
		songList.setName(fileItems.get("name").getString());
		Base64FileItem iconItem = fileItems.get("icon");
		if (iconItem != null) {
			String iconName = UUIDUtils.randomUUID() + "." + iconItem.getType();
			iconItem.getFile(request.getServletContext().getRealPath("image/songList") + "/" + iconName);
			songList.setIcon("image/songList/" + iconName);
		}
		songList.setRefreshRate(fileItems.get("refreshRate").getString());
		songList.setRefreshDate(new Date());
		songList.setGlobe(fileItems.get("globe").getBoolean());

		songListService.update(songList);
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "/{id:\\w+}/excel", method = RequestMethod.PUT)
	public Map<String, Object> updateSongs(@PathVariable("id") String id, HttpServletRequest request) {
		SongList songList = songListService.selectById(id);
		if (songList == null) {
			return MessageUtils.createError("未知错误");
		}

		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);
		Base64FileItem excelItem = fileItems.get("songsExcel");
		File songExcel = excelItem
				.getFile(ResourceLoader.getRealPath("temp") + "/" + UUIDUtils.randomUUID() + excelItem.getType());
		List<List<Cell>> cellData = XSSFUtils.parse(songExcel, Constant.EXCEL_BEGINNUM, 3);
		songExcel.delete();

		List<String> songNames = new ArrayList<>();
		for (List<Cell> songCells : cellData) {
			songNames.add(songCells.get(1).getString());
		}
		Map<String, Song> songMap = songService.batchSelectByName(songNames);
		if (songNames.size() != songMap.size()) {
			return MessageUtils.createError("存在未知歌曲");
		}

		List<SongList_SongGL> songList_SongGLList = new ArrayList<>();
		for (List<Cell> songCells : cellData) {
			Song song = songMap.get(songCells.get(1).getString());
			Integer rank = songCells.get(0).getInteger();
			Integer rankChange = songCells.get(2).getInteger();
			songList_SongGLList.add(new SongList_SongGL(UUIDUtils.randomUUID(), song.getId(), rank, id, rankChange));
		}

		songList_SongGLService.batchDeleteThenInsert(id, songList_SongGLList);
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "/{id:\\w+}/excel", method = RequestMethod.GET)
	public void getSongsExcel(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
		SongList songList = songListService.selectById(id);
		if (songList == null) {
			return;
		}

		List<SongList_SongGL> songList_SongGLList = songList_SongGLService
				.selectListBySongListIdOrderByRank(songList.getId());

		List<List<Cell>> dataCellsList = new ArrayList<>();
		for (SongList_SongGL gl : songList_SongGLList) {
			List<Cell> dataCells = new ArrayList<>();
			dataCells.add(new Cell(gl.getRank()));
			dataCells.add(new Cell(gl.getSongName()));
			dataCells.add(new Cell(gl.getRankChange()));
			dataCellsList.add(dataCells);
		}

		String fileName = songList.getName() + "-歌曲列表.xlsx";

		File result = ResourceLoader.loadAsFile("temp", "/" + fileName);
		File initialTemplate = ResourceLoader.loadAsFile(Constant.EXCEL_SONGLIST_TEMPLATE, null);
		File template = ResourceLoader.loadAsFile("temp", "/" + UUIDUtils.randomUUID() + ".xlsx");

		FileUtils.copy(initialTemplate, template);

		XSSFUtils.write(result, template, dataCellsList, Constant.EXCEL_BEGINNUM);
		String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		DownLoadUtlils.write(response, result, fileName, contentType);

		result.delete();
		template.delete();
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Map<String, Object> insert(HttpServletRequest request) {
		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);
		String name = fileItems.get("name").getString();
		Base64FileItem iconItem = fileItems.get("icon");
		String icon = UUIDUtils.randomUUID() + "." + iconItem.getType();
		iconItem.getFile(ResourceLoader.getRealPath("image/songList") + "/" + icon);
		String refreshRate = fileItems.get("refreshRate").getString();
		Boolean globe = fileItems.get("globe").getBoolean();

		SongList songList = factory.get(SongList.class);
		songList.setId(UUIDUtils.randomUUID());
		songList.setName(name);
		songList.setIcon("image/songList/"+icon);
		songList.setRefreshDate(new Date());
		songList.setRefreshRate(refreshRate);
		songList.setGlobe(globe);
		
		songListService.insert(songList);
		return MessageUtils.createSuccess();
	}
}
