package cn.xiedacon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.vo.SimpleSongListVo;
import cn.xiedacon.vo.SongListVo;

public interface SongListDao {

	List<SimpleSongListVo> selectSongListsByIds(@Param("songListIds") String[] songListIds);

	SongListVo selectById(String id);

	List<SongListVo> selectList();

}
