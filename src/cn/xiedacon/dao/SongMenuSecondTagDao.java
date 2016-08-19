package cn.xiedacon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.vo.SongMenuSecondTagVo;

public interface SongMenuSecondTagDao {

	List<SongMenuSecondTagVo> selectSongMenuSecondTagListByIds(@Param("songMenuSecondTagIds") String[] songMenuSecondTagIds);

	List<SongMenuSecondTagVo> selectAll();

}
