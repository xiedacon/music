package cn.xiedacon.admin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.xiedacon.admin.dao.BatchSqlDao;
import cn.xiedacon.model.Album;
import cn.xiedacon.model.Album_SongGL;
import cn.xiedacon.model.SecondClassify;
import cn.xiedacon.model.Singer;
import cn.xiedacon.model.Song;
import cn.xiedacon.model.SongList_SongGL;
import cn.xiedacon.model.SongMenuSecondTag;
import cn.xiedacon.model.SongMenu_SongMenuTagGL;
import cn.xiedacon.util.jdbc.BatchSqlUtils;

@Repository
public class BatchSqlDaoImpl implements BatchSqlDao {

	@Autowired
	private ComboPooledDataSource dataSource;
	private int batchSize = 100;
	private int batchSize_select = 10;

	@Override
	public void insertSinger(List<Singer> singerList) {
		String sql = "INSERT INTO singer VALUES(?,?,?,?,?,?,?,?,?)";

		try (Connection conn = dataSource.getConnection();) {
			List<List<Object>> rowDatas = new ArrayList<>();

			for (int i = 0; i < singerList.size(); i++) {
				List<Object> rowData = new ArrayList<>();

				Singer singer = singerList.get(i);
				rowData.add(singer.getId());
				rowData.add(singer.getName());
				rowData.add(singer.getIcon());
				rowData.add(singer.getRemark());
				rowData.add(singer.getIntroduction());
				rowData.add(singer.getUserId());
				rowData.add(singer.getCollectionNum());
				rowData.add(singer.getClassifyId());
				rowData.add(singer.getVisible());

				rowDatas.add(rowData);
			}
			BatchSqlUtils.excuteBatch(conn, sql, rowDatas, batchSize);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Map<String, Singer> selectSingerByName(List<String> singerNameList) {
		String sql = "SELECT s.id, s.name FROM singer s WHERE s.name = ?";
		try (Connection conn = dataSource.getConnection();) {
			List<ResultSet> resultSets = BatchSqlUtils.excuteBatchForSelect(conn, sql, singerNameList,
					batchSize_select);

			Map<String, Singer> result = new HashMap<>();
			for (ResultSet resultSet : resultSets) {
				while (resultSet.next()) {
					result.put(resultSet.getString(2), new Singer(resultSet.getString(1), resultSet.getString(2)));
				}
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Map<String, Album> selectAlbumByName(List<String> albumNameList) {
		String sql = "SELECT ab.id, ab.name FROM album_base ab WHERE ab.name = ?";
		try (Connection conn = dataSource.getConnection();) {
			List<ResultSet> resultSets = BatchSqlUtils.excuteBatchForSelect(conn, sql, albumNameList, batchSize_select);

			Map<String, Album> result = new HashMap<>();
			for (ResultSet resultSet : resultSets) {
				while (resultSet.next()) {
					result.put(resultSet.getString(2), new Album(resultSet.getString(1), resultSet.getString(2)));
				}
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateAlbum(Map<String, Integer> albumMap) {
		String sql = "UPDATE album_base ab SET ab.songNum = ? + ab.songNum WHERE ab.id = ?";

		try (Connection conn = dataSource.getConnection();) {
			List<List<Object>> rowDatas = new ArrayList<>();
			for (Map.Entry<String, Integer> entry : albumMap.entrySet()) {
				List<Object> rowData = new ArrayList<>();
				rowData.add(entry.getValue());
				rowData.add(entry.getKey());
				rowDatas.add(rowData);
			}
			BatchSqlUtils.excuteBatch(conn, sql, rowDatas, batchSize);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insertSong(List<Song> songList) {
		String song_baseSql = "INSERT INTO song_base VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		String song_recordSql = "INSERT INTO song_record VALUES(?,?,?,?)";

		try (Connection conn = dataSource.getConnection();) {
			List<List<Object>> song_baseRowDatas = new ArrayList<>();
			List<List<Object>> song_recordRowDatas = new ArrayList<>();
			for (int i = 0; i < songList.size(); i++) {
				List<Object> song_baseRowData = new ArrayList<>();
				List<Object> song_recordRowData = new ArrayList<>();

				Song song = songList.get(i);
				song_baseRowData.add(song.getId());
				song_baseRowData.add(song.getName());
				song_baseRowData.add(song.getIcon());
				song_baseRowData.add(song.getTime());
				song_baseRowData.add(song.getLrcUri());
				song_baseRowData.add(song.getFileUri());
				song_baseRowData.add(song.getSingerName());
				song_baseRowData.add(song.getAlbumName());
				song_baseRowData.add(song.getRemark());
				song_baseRowData.add(song.getSingerId());
				song_baseRowData.add(song.getAlbumId());
				song_baseRowData.add(song.getVisible());

				song_recordRowData.add(song.getId());
				song_recordRowData.add(song.getCommentNum());
				song_recordRowData.add(song.getPlayNum());
				song_recordRowData.add(song.getCollectionNum());

				song_baseRowDatas.add(song_baseRowData);
				song_recordRowDatas.add(song_recordRowData);
			}
			BatchSqlUtils.excuteBatch(conn, song_baseSql, song_baseRowDatas, batchSize);
			BatchSqlUtils.excuteBatch(conn, song_recordSql, song_recordRowDatas, batchSize);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insertAlbum_SongGL(List<Album_SongGL> album_SongGLList) {
		String song_gl_albumSql = "INSERT INTO song_gl_album VALUES(?,(SELECT COUNT(s.id) FROM song_gl_album s WHERE s.albumId = ?),?)";

		try (Connection conn = dataSource.getConnection();) {
			List<List<Object>> rowDatas = new ArrayList<>();
			for (int i = 0; i < album_SongGLList.size(); i++) {
				List<Object> rowData = new ArrayList<>();

				Album_SongGL album_SongGL = album_SongGLList.get(i);
				rowData.add(album_SongGL.getSongId());
				rowData.add(album_SongGL.getRank());
				rowData.add(album_SongGL.getAlbumId());

				rowDatas.add(rowData);
			}
			BatchSqlUtils.excuteBatch(conn, song_gl_albumSql, rowDatas, batchSize);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Map<String, Song> selectSongByName(List<String> songNames) {
		String sql = "SELECT sb.id, sb.name FROM song_base sb WHERE sb.name = ?";
		try (Connection conn = dataSource.getConnection();) {
			List<ResultSet> resultSets = BatchSqlUtils.excuteBatchForSelect(conn, sql, songNames, batchSize_select);

			Map<String, Song> result = new HashMap<>();
			for (ResultSet resultSet : resultSets) {
				while (resultSet.next()) {
					result.put(resultSet.getString(2), new Song(resultSet.getString(1), resultSet.getString(2)));
				}
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insertSongList_SongGL(List<SongList_SongGL> songList_SongGLList) {
		String song_gl_songlistSql = "INSERT INTO song_gl_songlist VALUES(?,?,?,?)";
		int batchSize = 100;

		try (Connection conn = dataSource.getConnection();) {
			List<List<Object>> rowDatas = new ArrayList<>();
			for (int i = 0; i < songList_SongGLList.size(); i++) {
				List<Object> rowData = new ArrayList<>();

				SongList_SongGL songList_SongGL = songList_SongGLList.get(i);
				rowData.add(songList_SongGL.getSongId());
				rowData.add(songList_SongGL.getRank());
				rowData.add(songList_SongGL.getSongListId());
				rowData.add(songList_SongGL.getRankChange());

				rowDatas.add(rowData);
			}
			BatchSqlUtils.excuteBatch(conn, song_gl_songlistSql, rowDatas, batchSize);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteSongList_SongGLBySongListId(String id) {
		String sql = "DELETE FROM song_gl_songlist WHERE songListId = ?";
		try (Connection conn = dataSource.getConnection();) {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, id);
			statement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insertAlbum(List<Album> albumList) {
		String album_baseSql = "INSERT INTO album_base VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		String album_recordSql = "INSERT INTO album_record VALUES(?,?,?,?)";

		try (Connection conn = dataSource.getConnection();) {
			List<List<Object>> album_baseRowDatas = new ArrayList<>();
			List<List<Object>> album_recordRowDatas = new ArrayList<>();
			for (int i = 0; i < albumList.size(); i++) {
				List<Object> album_baseRowData = new ArrayList<>();
				List<Object> album_recordRowData = new ArrayList<>();
				Album album = albumList.get(i);
				album_baseRowData.add(album.getId());
				album_baseRowData.add(album.getName());
				album_baseRowData.add(album.getIcon());
				album_baseRowData.add(album.getRemark());
				album_baseRowData.add(album.getCreateTime());
				album_baseRowData.add(album.getCreateCompany());
				album_baseRowData.add(album.getIntroduction());
				album_baseRowData.add(album.getSongNum());
				album_baseRowData.add(album.getSingerId());
				album_baseRowData.add(album.getTagId());
				album_baseRowData.add(album.getVisible());

				album_recordRowData.add(album.getId());
				album_recordRowData.add(album.getShareNum());
				album_recordRowData.add(album.getCommentNum());
				album_recordRowData.add(album.getPlayNum());

				album_baseRowDatas.add(album_baseRowData);
				album_recordRowDatas.add(album_recordRowData);
			}
			BatchSqlUtils.excuteBatch(conn, album_baseSql, album_baseRowDatas, batchSize);
			BatchSqlUtils.excuteBatch(conn, album_recordSql, album_recordRowDatas, batchSize);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Map<String, SecondClassify> selectSecondClassifyById(List<String> classifyIdList) {
		String sql = "SELECT sc.id, sc.name FROM classify_second sc WHERE sc.id = ?";
		try (Connection conn = dataSource.getConnection();) {
			List<ResultSet> resultSets = BatchSqlUtils.excuteBatchForSelect(conn, sql, classifyIdList,
					batchSize_select);

			Map<String, SecondClassify> result = new HashMap<>();
			for (ResultSet resultSet : resultSets) {
				while (resultSet.next()) {
					result.put(resultSet.getString(1),
							new SecondClassify(resultSet.getString(1), resultSet.getString(2)));
				}
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insertSongMenu_SongMenuTagGL(List<SongMenu_SongMenuTagGL> songMenu_SongMenuTagGLList) {
		try (Connection conn = dataSource.getConnection()) {
			List<List<Object>> rowDatas = new ArrayList<>();
			for (SongMenu_SongMenuTagGL gl : songMenu_SongMenuTagGLList) {
				List<Object> rowData = new ArrayList<>();
				rowData.add(gl.getSongMenuId());
				rowData.add(gl.getSongMenuTagId());

				rowDatas.add(rowData);
			}
			BatchSqlUtils.excuteBatch(conn, "INSERT INTO smi_gl_rsms VALUES(?,?)", rowDatas, batchSize);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Map<String, SongMenuSecondTag> selectSecondSongMenuTagById(List<String> tagIdList) {
		String sql = "SELECT tss.id, tss.name FROM tag_songmenu_second tss WHERE tss.id = ?";
		try (Connection conn = dataSource.getConnection();) {
			List<ResultSet> resultSets = BatchSqlUtils.excuteBatchForSelect(conn, sql, tagIdList,
					batchSize_select);

			Map<String, SongMenuSecondTag> result = new HashMap<>();
			for (ResultSet resultSet : resultSets) {
				while (resultSet.next()) {
					result.put(resultSet.getString(1),
							new SongMenuSecondTag(resultSet.getString(1), resultSet.getString(2)));
				}
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteSongMenu_SongMenuTagGLBySongMenuId(String id) {
		String sql = "DELETE FROM smi_gl_rsms WHERE songMenuId = ?";
		try (Connection conn = dataSource.getConnection();) {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, id);
			statement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
