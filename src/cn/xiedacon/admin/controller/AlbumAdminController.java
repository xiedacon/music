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
import cn.xiedacon.admin.service.AlbumTagAdminService;
import cn.xiedacon.admin.service.SingerAdminService;
import cn.xiedacon.factory.Factory;
import cn.xiedacon.model.Album;
import cn.xiedacon.model.AlbumTag;
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
	private AlbumTagAdminService tagService;
	@Autowired
	private Factory factory;

	@RequestMapping(value = "/page/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public Map<String, Object> selectPageBean(@PathVariable("page") Integer page) {
		return MessageUtils.createSuccess(albumService.selectPageBean(page));
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
		return MessageUtils.createSuccess(albumService.selectPageBeanByNameLike(page, nameLike));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.GET)
	public Map<String, Object> selectById(@PathVariable("id") String id) {
		return MessageUtils.createSuccess(albumService.selectById(id));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.PUT)
	public Map<String, Object> update(@PathVariable("id") String id, HttpServletRequest request) {
		Album album = albumService.selectById(id);
		if (album == null) {
			return MessageUtils.createError(id, "未知错误");
		}

		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);

		String name = fileItems.get("name").getString();
		String icon = album.getIcon();
		Date createTime = fileItems.get("createTime").getDate("yyyy-MM-dd");
		String createCompany = fileItems.get("createCompany").getString();
		String tagId = fileItems.get("tagId").getString();
		String remark = fileItems.get("remark").getString();
		String introduction = fileItems.get("introduction").getString();

		if (name == null || name.isEmpty()) {
			return MessageUtils.createError("name", "专辑名错误");
		}
		Base64FileItem iconItem = fileItems.get("icon");
		if (iconItem != null) {
			icon = UUIDUtils.randomUUID() + "." + iconItem.getType();
			iconItem.getFile(ResourceLoader.getRealPath("image/album") + "/" + icon);
			icon = "image/album/" + icon;
		}
		if (createTime == null) {
			return MessageUtils.createError("createTime", "日期错误");
		}
		AlbumTag tag = tagService.selectById(tagId);
		if (tag == null) {
			return MessageUtils.createError("tagId", "分类错误");
		}

		album.setName(name);
		album.setIcon(icon);
		album.setCreateTime(createTime);
		album.setCreateCompany(createCompany);
		album.setTagId(tag.getId());
		album.setRemark(remark);
		album.setIntroduction(introduction);

		albumService.update(album);
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Map<String, Object> insert(HttpServletRequest request) {
		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);

		String name = fileItems.get("name").getString();
		String icon = null;
		String singerName = fileItems.get("singerName").getString();
		Date createTime = fileItems.get("createTime").getDate("yyyy-MM-dd");
		String createCompany = fileItems.get("createCompany").getString();
		String tagId = fileItems.get("tagId").getString();
		String remark = fileItems.get("remark").getString();
		String introduction = fileItems.get("introduction").getString();

		if (name == null || name.trim().isEmpty()) {
			return MessageUtils.createError("name", "专辑名错误");
		}
		Base64FileItem iconItem = fileItems.get("icon");
		if (iconItem == null) {
			return MessageUtils.createError("icon", "图片错误");
		}
		icon = UUIDUtils.randomUUID() + "." + iconItem.getType();
		iconItem.getFile(ResourceLoader.getRealPath("image/album") + "/" + icon);
		icon = "image/album/" + icon;
		Singer singer = singerService.selectByName(singerName);
		if (createTime == null) {
			return MessageUtils.createError("createTime", "日期错误");
		}
		AlbumTag tag = tagService.selectById(tagId);
		if (tag == null) {
			return MessageUtils.createError("tagId", "分类错误");
		}

		Album album = factory.get(Album.class);
		album.setId(UUIDUtils.randomUUID());
		album.setName(name);
		album.setIcon(icon);
		album.setCreateTime(createTime);
		album.setCreateCompany(createCompany);
		album.setTagId(tag.getId());
		album.setRemark(remark);
		album.setIntroduction(introduction);
		if (singer != null) {
			album.setSingerId(singer.getId());
			album.setSingerName(singer.getName());
		}

		albumService.insert(album);
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "/excel", method = RequestMethod.POST)
	public Map<String, Object> batchInsert(HttpServletRequest request) {
		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);

		File excelFile = fileItems.get("excel")
				.getFile(ResourceLoader.getRealPath("temp") + "/" + UUIDUtils.randomUUID());
		List<List<Cell>> cellData = XSSFUtils.parse(excelFile, Constant.EXCEL_BEGINNUM, 8);

		File zipFile = fileItems.get("iconZip").getFile(ResourceLoader.getRealPath("temp") + UUIDUtils.randomUUID());
		ZipUtils.upZip(zipFile, ResourceLoader.getRealPath("image/album"));

		List<String> singerNameList = new ArrayList<>();
		for (List<Cell> cells : cellData) {
			singerNameList.add(cells.get(3).getString());
		}

		Map<String, Singer> singerMap = singerService.batchSelectByName(singerNameList);
		List<Album> albumList = new ArrayList<>();
		for (List<Cell> cells : cellData) {
			String name = cells.get(1).getString();
			String icon = "image/album/" + cells.get(2).getString();
			Singer singer = singerMap.get(cells.get(3).getString());
			Date createTime = cells.get(4).getDate();
			String createCompany = cells.get(5).getString();
			String tagId = cells.get(6).getInteger().toString();
			String remark = cells.get(7).getString();
			String introduction = cells.get(8).getString();

			if (name == null || name.trim().isEmpty()) {
				return MessageUtils.createError("name", "专辑名错误");
			}
			if (icon == null || icon.trim().isEmpty()) {
				return MessageUtils.createError("icon", "图片错误");
			}
			if (createTime == null) {
				return MessageUtils.createError("createTime", "日期错误");
			}
			AlbumTag tag = tagService.selectById(tagId);
			if (tag == null) {
				return MessageUtils.createError("tagId", "分类错误");
			}

			Album album = factory.get(Album.class);
			album.setId(UUIDUtils.randomUUID());
			album.setName(name);
			album.setIcon(icon);
			if (singer != null) {
				album.setSingerId(singer.getId());
				album.setSingerName(singer.getName());
			}
			album.setCreateTime(createTime);
			album.setCreateCompany(createCompany);
			album.setTagId(tag.getId());
			album.setRemark(remark);
			album.setIntroduction(introduction);

			albumList.add(album);
		}

		albumService.batchInsert(albumList);
		return MessageUtils.createSuccess();
	}
}
