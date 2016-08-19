package cn.xiedacon.service;

import java.util.List;

import cn.xiedacon.vo.SimpleSongListVo;
import cn.xiedacon.vo.SongListVo;

public interface SongListService {

	List<SimpleSongListVo> selectForIndex();

	SongListVo selectById(String id);

	List<SongListVo> selectList();

}
