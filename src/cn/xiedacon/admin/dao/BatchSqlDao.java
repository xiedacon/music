package cn.xiedacon.admin.dao;

import java.util.List;
import java.util.Map;

import cn.xiedacon.model.Album;
import cn.xiedacon.model.Album_SongGL;
import cn.xiedacon.model.Singer;
import cn.xiedacon.model.Song;
import cn.xiedacon.model.SongList_SongGL;

public interface BatchSqlDao {

	void insertSinger(List<Singer> singerList);

	Map<String, Singer> selectSingerByName(List<String> singerNameList);

	Map<String, Album> selectAlbumByName(List<String> albumNameList);

	void updateAlbum(Map<String, Integer> albumMap);

	void insertSong(List<Song> songList);

	void insertAlbum_SongGL(List<Album_SongGL> album_SongGlList);

	Map<String, Song> selectSongByName(List<String> songNames);

	void insertSongList_SongGL(List<SongList_SongGL> songList_SongGLList);

	void deleteSongList_SongGLBySongListId(String id);

	void insertAlbum(List<Album> albumList);

}
