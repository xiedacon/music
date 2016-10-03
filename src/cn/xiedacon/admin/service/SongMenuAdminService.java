package cn.xiedacon.admin.service;

import cn.xiedacon.model.SongMenu;
import cn.xiedacon.util.PageBean;

public interface SongMenuAdminService {

	PageBean<SongMenu> selectPageBean(Integer page);

}
