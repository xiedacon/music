package cn.xiedacon.admin.service;

import java.util.List;
import java.util.Map;

import cn.xiedacon.model.Album;

public interface AlbumAdminService {

	Album selectByName(String name);

	Map<String, Album> batchSelectByName(List<String> albumNameList);

}
