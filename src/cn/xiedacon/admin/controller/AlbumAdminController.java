package cn.xiedacon.admin.controller;

import java.io.File;
import java.util.ArrayList;
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
import cn.xiedacon.factory.Factory;
import cn.xiedacon.model.Album;
import cn.xiedacon.model.Singer;
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
@RequestMapping("/admin/album")
public class AlbumAdminController {

	@Autowired
	private AlbumAdminService albumService;
	@Autowired
	private SingerAdminService singerService;
	@Autowired
	private Factory factory;

	@RequestMapping(value = "/page/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public Map<String, Object> selectPageBean(@PathVariable("page") Integer page) {
		return MessageUtils.createSuccess(Constant.SUCCESS_RETURNNAME, albumService.selectPageBean(page));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.DELETE)
	public Map<String, Object> delete(@PathVariable("id") String id) {
		Album album = albumService.selectExist(id);
		if (album != null) {
			albumService.delete(album);
		}
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "/name/{name:.+}/page/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public Map<String, Object> selectPageBeanByNameLike(@PathVariable("name") String name,
			@PathVariable("page") Integer page) {
		String nameLike = "%" + CharsetUtils.change(name, "ISO-8859-1", "UTF-8") + "%";
		return MessageUtils.createSuccess(Constant.SUCCESS_RETURNNAME,
				albumService.selectPageBeanByNameLike(page, nameLike));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.GET)
	public Map<String, Object> selectById(@PathVariable("id") String id) {
		return MessageUtils.createSuccess(Constant.SUCCESS_RETURNNAME, albumService.selectById(id));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.PUT)
	public Map<String, Object> update(@PathVariable("id") String id, HttpServletRequest request) {
		Album album = albumService.selectById(id);
		if (album == null) {
			return MessageUtils.createError("未知错误");
		}

		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);

		album.setName(fileItems.get("name").getString());

		Base64FileItem iconItem = fileItems.get("icon");
		if (iconItem != null) {
			String icon = UUIDUtils.randomUUID() + "." + iconItem.getType();
			iconItem.getFile(ResourceLoader.getRealPath("image/album") + "/" + icon);
			album.setIcon("image/album/" + icon);
		}

		album.setCreateTime(fileItems.get("createTime").getDate("yyyy-MM-dd"));
		album.setCreateCompany(fileItems.get("createCompany").getString());
		album.setTagId(fileItems.get("tagId").getString());
		album.setRemark(fileItems.get("remark").getString());
		album.setIntroduction(fileItems.get("introduction").getString());

		albumService.update(album);
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Map<String, Object> insert(HttpServletRequest request) {
		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);

		Base64FileItem singerNameItem = fileItems.get("singerName");
		if (singerNameItem == null) {
			return MessageUtils.createError("未知错误");
		}
		Singer singer = singerService.selectByName(singerNameItem.getString());
		if (singer == null) {
			return MessageUtils.createError("未知错误");
		}

		Base64FileItem iconItem = fileItems.get("icon");
		if (iconItem == null) {
			return MessageUtils.createError("未知错误");
		}
		String icon = UUIDUtils.randomUUID() + "." + iconItem.getType();
		iconItem.getFile(ResourceLoader.getRealPath("image/album") + "/" + icon);

		Album album = factory.get(Album.class);
		album.setId(UUIDUtils.randomUUID());
		album.setName(fileItems.get("name").getString());
		album.setIcon("image/album/" + icon);
		album.setCreateTime(fileItems.get("createTime").getDate("yyyy-MM-dd"));
		album.setCreateCompany(fileItems.get("createCompany").getString());
		album.setTagId(fileItems.get("tagId").getString());
		album.setRemark(fileItems.get("remark").getString());
		album.setIntroduction(fileItems.get("introduction").getString());
		album.setSingerId(singer.getId());
		album.setSingerName(singer.getName());

		albumService.insert(album);
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "/excel", method = RequestMethod.POST)
	public Map<String, Object> batchInsert(HttpServletRequest request) {
		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);

		File excelFile = fileItems.get("excel")
				.getFile(ResourceLoader.getRealPath("temp") + "/" + UUIDUtils.randomUUID());
		List<List<Cell>> cellsList = XSSFUtils.parse(excelFile, Constant.EXCEL_BEGINNUM, 8);
		excelFile.delete();

		File zipFile = fileItems.get("iconZip").getFile(ResourceLoader.getRealPath("temp") + UUIDUtils.randomUUID());
		ZipUtils.upZip(zipFile, ResourceLoader.getRealPath("image/album"));
		zipFile.delete();

		List<String> singerNameList = new ArrayList<>();
		
		for(List<Cell> cells:cellsList){
			singerNameList.add(cells.get(3).getString());
		}
		
		Map<String, Singer> singerMap = singerService.batchSelectByName(singerNameList);
		List<Album> albumList = new ArrayList<>();
		
		for(List<Cell> cells:cellsList){
			Singer singer = singerMap.get(cells.get(3).getString());
			
			Album album = factory.get(Album.class);
			album.setId(UUIDUtils.randomUUID());
			album.setName(cells.get(1).getString());
			album.setIcon("image/album/"+cells.get(2).getString());
			if(singer!=null){
				album.setSingerId(singer.getId());
				album.setSingerName(singer.getName());
			}
			album.setCreateTime(cells.get(4).getDate());
			album.setCreateCompany(cells.get(5).getString());
			album.setTagId(cells.get(6).getInteger().toString());
			album.setRemark(cells.get(7).getString());
			album.setIntroduction(cells.get(8).getString());
			
			albumList.add(album);
		}
		
		albumService.batchInsert(albumList);
		return MessageUtils.createSuccess();
	}
}
