package cn.xiedacon.admin.controller;

import java.io.File;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.admin.service.SingerAdminService;
import cn.xiedacon.factory.Factory;
import cn.xiedacon.model.Singer;
import cn.xiedacon.util.Base64FileItem;
import cn.xiedacon.util.Base64UploadUtils;
import cn.xiedacon.util.CharsetUtils;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.util.UUIDUtils;

@Controller
@ResponseBody
@RequestMapping("/admin/singer")
public class SingerAdminController {

	@Autowired
	private SingerAdminService singerService;
	@Autowired
	private Factory factory;

	@RequestMapping(value = "/page/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public Map<String, Object> selectPageBean(@PathVariable("page") Integer page) {
		return MessageUtils.createSuccess(Constant.SUCCESS_RETURNNAME, singerService.selectPageBean(page));
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
		return MessageUtils.createSuccess(Constant.SUCCESS_RETURNNAME, singerService.selectPageBeanByNameLike(page,
				"%" + CharsetUtils.decode(name, "ISO-8859-1", "UTF-8") + "%"));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.GET)
	public Map<String, Object> selectById(@PathVariable("id") String id) {
		return MessageUtils.createSuccess(Constant.SUCCESS_RETURNNAME, singerService.selectById(id));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.PUT)
	public Map<String, Object> update(@PathVariable("id") String id, HttpServletRequest request) {
		Singer singer = singerService.selectById(id);
		if (singer == null) {
			return MessageUtils.createError("未知错误");
		}

		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);

		singer.setName(fileItems.get("name").getString());
		singer.setRemark(fileItems.get("remark").getString());
		singer.setClassifyId(fileItems.get("classifyId").getString());
		singer.setIntroduction(fileItems.get("introduction").getString());

		Base64FileItem fileItem = fileItems.get("icon");
		if (fileItem != null) {
			String fileName = UUIDUtils.uuid(new Date().getTime()) + "." + fileItem.getType();
			fileItem.getFile(request.getServletContext().getRealPath("image/singer") + "/" + fileName);

			File oldIconFile = new File(request.getServletContext().getRealPath(singer.getIcon()));
			oldIconFile.delete();

			singer.setIcon("image/singer/" + fileName);
		}

		singerService.update(singer);
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Map<String, Object> insert(HttpServletRequest request) {
		Map<String, Base64FileItem> fileItems = Base64UploadUtils.parseRequest(request);
		Base64FileItem fileItem = fileItems.get("icon");
		if (fileItem == null) {
			return MessageUtils.createError("头像不存在");
		}
		String fileName = UUIDUtils.uuid(new Date().getTime()) + "." + fileItem.getType();
		fileItem.getFile(request.getServletContext().getRealPath("image/singer") + "/" + fileName);

		Singer singer = factory.get(Singer.class);

		singer.setId(UUIDUtils.randomUUID());
		singer.setName(fileItems.get("name").getString());
		singer.setIcon("image/singer/" + fileName);
		singer.setRemark(fileItems.get("remark").getString());
		singer.setClassifyId(fileItems.get("classifyId").getString());
		singer.setIntroduction(fileItems.get("introduction").getString());

		singerService.insert(singer);
		return MessageUtils.createSuccess();
	}
}
