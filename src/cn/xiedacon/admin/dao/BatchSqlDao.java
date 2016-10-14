package cn.xiedacon.admin.dao;

import java.util.List;
import java.util.Map;

import cn.xiedacon.model.Album;
import cn.xiedacon.model.Album_SongGL;
import cn.xiedacon.model.Singer;
import cn.xiedacon.model.Song;

public interface BatchSqlDao {

	void insertSinger(List<Singer> singerList);

	Map<String, Singer> selectSingerByName(List<String> singerNameList);

	Map<String, Album> selectAlbumByName(List<String> albumNameList);

	void updateAlbum(Map<String, Integer> albumMap);

	void insertSong(List<Song> songList);

	void insertAlbum_SongGL(List<Album_SongGL> album_SongGlList);

}
