package cn.xiedacon.service;

import java.util.List;

import cn.xiedacon.vo.SongMenuSecondTagVo;

public interface SongMenuSecondTagService {

	List<SongMenuSecondTagVo> selectForIndex();

	List<SongMenuSecondTagVo> selectAll();

}
