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

import cn.xiedacon.admin.service.SecondClassifyAdminService;
import cn.xiedacon.admin.service.SingerAdminService;
import cn.xiedacon.factory.Factory;
import cn.xiedacon.model.SecondClassify;
import cn.xiedacon.model.Singer;
import cn.xiedacon.util.CharsetUtils;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.util.ResourceLoader;
import cn.xiedacon.util.ZipUtils;
import cn.xiedacon.util.excel.XSSFCell;
import cn.xiedacon.util.excel.XSSFUtils;
import cn.xiedacon.util.upload.Base64FileItem;
import cn.xiedacon.util.upload.Base64UploadUtils;
import cn.xiedacon.util.UUIDUtils;

@Controller
@ResponseBody
@RequestMapping("/admin/singer")
public class SingerAdminController {

	@Autowired
	private SingerAdminService singerService;
	@Autowired
	private SecondClassifyAdminService classifyService;
	@Autowired
	private Factory factory;

	@RequestMapping(value = "/page/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public Map<String, Object> selectPageBean(@PathVariable("page") Integer page) {
		return MessageUtils.createSuccess(singerService.selectPageBean(page));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.DELETE)
	public Map<String, Object> delete(@PathVariable("id") String id) {
		Singer singer = singerService.selectExist(id);
		if (singer != null) {
			singerService.delete(singer);
		}
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "/name/{name:.+}/page/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public Map<String, Object> selectPageBeanByNameLike(@PathVariable("name") String name,
			@PathVariable("page") Integer page) {
		String nameLike = "%" + CharsetUtils.change(name, "ISO-8859-1", "UTF-8") + "%";
		return MessageUtils.createSuccess(singerService.selectPageBeanByNameLike(page, nameLike));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.GET)
	public Map<String, Object> selectById(@PathVariable("id") String id) {
		return MessageUtils.createSuccess(singerService.selectById(id));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.PUT)
	public Map<String, Object> update(@PathVariable("id") String id, HttpServletRequest request) {
		Singer singer = singerService.selectById(id);
		if (singer == null) {
			return MessageUtils.createError("id", "歌手错误");
		}

		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);

		String name = fileItems.get("name").getString();
		String icon = singer.getIcon();
		String remark = fileItems.get("remark").getString();
		String classifyId = fileItems.get("classifyId").getString();
		String introduction = fileItems.get("introduction").getString();

		if (name == null || name.trim().isEmpty()) {
			return MessageUtils.createError("name", "歌手名不能为空");
		}
		Base64FileItem fileItem = fileItems.get("icon");
		if (fileItem != null) {
			icon = UUIDUtils.randomUUID() + "." + fileItem.getType();
			fileItem.getFile(ResourceLoader.getRealPath("image/singer") + "/" + icon);
			File oldIconFile = new File(ResourceLoader.getRealPath(singer.getIcon()));
			icon = "image/singer/" + icon;

			// 暂时先这样，以后使用任务队列+定时删除解决
			oldIconFile.delete();
		}
		SecondClassify classify = classifyService.selectById(classifyId);
		if (classify == null) {
			return MessageUtils.createError("classifyId", "分类错误");
		}

		singer.setName(name);
		singer.setIcon(icon);
		singer.setRemark(remark);
		singer.setClassifyId(classify.getId());
		singer.setIntroduction(introduction);

		singerService.update(singer);
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Map<String, Object> insert(HttpServletRequest request) {
		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);

		String name = fileItems.get("name").getString();
		String icon = null;
		String remark = fileItems.get("remark").getString();
		String classifyId = fileItems.get("classifyId").getString();
		String introduction = fileItems.get("introduction").getString();

		if (name == null || name.trim().isEmpty()) {
			return MessageUtils.createError("name", "歌手名不能为空");
		}
		Base64FileItem fileItem = fileItems.get("icon");
		if (fileItem == null) {
			return MessageUtils.createError("icon", "图片不能为空");
		}
		icon = UUIDUtils.uuid(new Date().getTime()) + "." + fileItem.getType();
		fileItem.getFile(ResourceLoader.getRealPath("image/singer") + "/" + icon);
		icon = "image/singer/" + icon;
		SecondClassify classify = classifyService.selectById(classifyId);
		if (classify == null) {
			return MessageUtils.createError("classifyId", "分类错误");
		}

		Singer singer = factory.get(Singer.class);
		singer.setId(UUIDUtils.randomUUID());
		singer.setName(name);
		singer.setIcon(icon);
		singer.setRemark(remark);
		singer.setClassifyId(classify.getId());
		singer.setIntroduction(introduction);

		singerService.insert(singer);
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "/excel", method = RequestMethod.POST)
	public Map<String, Object> batchInsert(HttpServletRequest request) {
		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);

		Base64FileItem excelItem = fileItems.get("excel");
		File excelFile = excelItem
				.getFile(ResourceLoader.getRealPath("temp") + "/" + UUIDUtils.randomUUID() + excelItem.getType());
		List<List<XSSFCell>> cellData = XSSFUtils.parse(excelFile, Constant.EXCEL_BEGINNUM);
		// !!!!!!!
		excelFile.delete();
		Base64FileItem zipitem = fileItems.get("zip");
		File zipFile = zipitem
				.getFile(ResourceLoader.getRealPath("temp") + "/" + UUIDUtils.randomUUID() + zipitem.getType());
		ZipUtils.upZip(zipFile, ResourceLoader.getRealPath("image/singer"));
		// !!!!!!!
		zipFile.delete();

		List<String> classifyIdList = new ArrayList<>();
		for (List<XSSFCell> cells : cellData) {
			classifyIdList.add(cells.get(5).getString());
		}
		Map<String, SecondClassify> classifyMap = classifyService.batchSelectById(classifyIdList);

		List<Singer> singerList = new ArrayList<>();
		for (List<XSSFCell> cells : cellData) {
			String name = cells.get(1).getString();
			String icon = "image/singer/" + cells.get(2).getString();
			String remark = cells.get(3).getString();
			String introduction = cells.get(4).getString();
			SecondClassify classify = classifyMap.get(cells.get(5).getString());

			if (name == null || name.trim().isEmpty()) {
				return MessageUtils.createError("name", "歌手名不能为空");
			}
			if (icon == null || icon.trim().isEmpty()) {
				return MessageUtils.createError("icon", "图片不能为空");
			}
			if (classify == null) {
				return MessageUtils.createError("classifyId", "分类错误");
			}

			Singer singer = factory.get(Singer.class);
			singer.setId(UUIDUtils.randomUUID());
			singer.setName(name);
			singer.setIcon(icon);
			singer.setRemark(remark);
			singer.setIntroduction(introduction);
			singer.setClassifyId(classify.getId());
			singerList.add(singer);
		}

		singerService.batchInsert(singerList);
		return MessageUtils.createSuccess();
	}
}
