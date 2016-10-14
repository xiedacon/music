package cn.xiedacon.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.xiedacon.model.Album;
import cn.xiedacon.model.Album_SongGL;
import cn.xiedacon.model.Singer;
import cn.xiedacon.model.Song;

@Repository
public class BatchSqlDaoImpl implements BatchSqlDao {

	@Autowired
	private ComboPooledDataSource dataSource;
	private int batchSize = 100;

	@Override
	public void insertSinger(List<Singer> singerList) {
		String sql = "INSERT INTO singer VALUES(?,?,?,?,?,?,?,?,?)";

		try (Connection conn = dataSource.getConnection();) {
			conn.setAutoCommit(false);

			PreparedStatement statement = conn.prepareStatement(sql);

			for (int i = 0; i < singerList.size(); i++) {
				Singer singer = singerList.get(i);
				statement.setString(1, singer.getId());
				statement.setString(2, singer.getName());
				statement.setString(3, singer.getIcon());
				statement.setString(4, singer.getRemark());
				statement.setString(5, singer.getIntroduction());
				statement.setString(6, singer.getUserId());
				statement.setInt(7, singer.getCollectionNum());
				statement.setString(8, singer.getClassifyId());
				statement.setBoolean(9, singer.getVisible());

				statement.addBatch();

				if ((i + 1) % batchSize == 0) {
					statement.executeBatch();
				}
			}
			statement.executeBatch();
			conn.commit();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Map<String, Singer> selectSingerByName(List<String> singerNameList) {
		String For1 = "SELECT s.id, s.name FROM singer s WHERE s.name = ?";
		String For3 = "SELECT s.id, s.name FROM singer s WHERE s.name IN(?,?,?)";
		String For10 = "SELECT s.id, s.name FROM singer s WHERE s.name IN(?,?,?,?,?,?,?,?,?,?)";
		int num = singerNameList.size();
		int begin = 0;
		List<ResultSet> resultSetlist = new ArrayList<>();
		try (Connection conn = dataSource.getConnection();) {
			while (num > 0) {
				if (num % 10 == 0) {
					resultSetlist.add(queryForNameList(conn, begin, 10, For10, singerNameList));
					begin += 10;
					num -= 10;
				} else if (num % 3 == 0) {
					resultSetlist.add(queryForNameList(conn, begin, 3, For3, singerNameList));
					begin += 3;
					num -= 3;
				} else {
					resultSetlist.add(queryForNameList(conn, begin, 1, For1, singerNameList));
					begin += 1;
					num -= 1;
				}
			}

			Map<String, Singer> result = new HashMap<>();
			for (ResultSet resultSet : resultSetlist) {
				while (resultSet.next()) {
					result.put(resultSet.getString(2), new Singer(resultSet.getString(1), resultSet.getString(2)));
				}
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private ResultSet queryForNameList(Connection conn, Integer begin, Integer num, String sql, List<String> nameList)
			throws SQLException {
		PreparedStatement statement = conn.prepareStatement(sql);
		for (int i = begin; i < begin + num; i++) {
			statement.setString(i - begin + 1, nameList.get(i));
		}
		return statement.executeQuery();
	}

	@Override
	public Map<String, Album> selectAlbumByName(List<String> albumNameList) {
		String For1 = "SELECT ab.id, ab.name FROM album_base ab WHERE ab.name = ?";
		String For3 = "SELECT ab.id, ab.name FROM album_base ab WHERE ab.name IN(?,?,?)";
		String For10 = "SELECT ab.id, ab.name FROM album_base ab WHERE ab.name IN(?,?,?,?,?,?,?,?,?,?)";
		int num = albumNameList.size();
		int begin = 0;
		List<ResultSet> resultSetlist = new ArrayList<>();
		try (Connection conn = dataSource.getConnection();) {
			while (num > 0) {
				if (num % 10 == 0) {
					resultSetlist.add(queryForNameList(conn, begin, 10, For10, albumNameList));
					begin += 10;
					num -= 10;
				} else if (num % 3 == 0) {
					resultSetlist.add(queryForNameList(conn, begin, 3, For3, albumNameList));
					begin += 3;
					num -= 3;
				} else {
					resultSetlist.add(queryForNameList(conn, begin, 1, For1, albumNameList));
					begin += 1;
					num -= 1;
				}
			}

			Map<String, Album> result = new HashMap<>();
			for (ResultSet resultSet : resultSetlist) {
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
			conn.setAutoCommit(false);

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 0;
			for (Map.Entry<String, Integer> entry : albumMap.entrySet()) {
				statement.setInt(1, entry.getValue());
				statement.setString(2, entry.getKey());
				statement.addBatch();

				if ((i + 1) % batchSize == 0) {
					statement.executeBatch();
				}
				i++;
			}
			statement.executeBatch();
			conn.commit();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insertSong(List<Song> songList) {
		String song_baseSql = "INSERT INTO song_base VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		String song_recordSql = "INSERT INTO song_record VALUES(?,?,?,?)";
		int batchSize = 100;

		try (Connection conn = dataSource.getConnection();) {
			conn.setAutoCommit(false);

			PreparedStatement song_baseStatement = conn.prepareStatement(song_baseSql);
			PreparedStatement song_recordStatement = conn.prepareStatement(song_recordSql);
			for (int i = 0; i < songList.size(); i++) {
				Song song = songList.get(i);
				song_baseStatement.setString(1, song.getId());
				song_baseStatement.setString(2, song.getName());
				song_baseStatement.setString(3, song.getIcon());
				song_baseStatement.setString(4, song.getTime());
				song_baseStatement.setString(5, song.getLrcUri());
				song_baseStatement.setString(6, song.getFileUri());
				song_baseStatement.setString(7, song.getSingerName());
				song_baseStatement.setString(8, song.getAlbumName());
				song_baseStatement.setString(9, song.getRemark());
				song_baseStatement.setString(10, song.getSingerId());
				song_baseStatement.setString(11, song.getAlbumId());
				song_baseStatement.setBoolean(12, song.getVisible());
				song_baseStatement.addBatch();

				song_recordStatement.setString(1, song.getId());
				song_recordStatement.setInt(2, song.getCommentNum());
				song_recordStatement.setInt(3, song.getPlayNum());
				song_recordStatement.setInt(4, song.getCollectionNum());
				song_recordStatement.addBatch();

				if ((i + 1) % batchSize == 0) {
					song_baseStatement.executeBatch();
					song_recordStatement.executeBatch();
				}
			}
			song_baseStatement.executeBatch();
			song_recordStatement.executeBatch();
			conn.commit();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insertAlbum_SongGL(List<Album_SongGL> album_SongGLList) {
		String song_gl_albumSql = "INSERT INTO song_gl_album VALUES(?,?,(SELECT COUNT(s.id) FROM song_gl_album s WHERE s.albumId = ?),?)";
		int batchSize = 100;

		try (Connection conn = dataSource.getConnection();) {
			conn.setAutoCommit(false);

			PreparedStatement song_gl_albumStatement = conn.prepareStatement(song_gl_albumSql);
			for (int i = 0; i < album_SongGLList.size(); i++) {
				Album_SongGL album_SongGL = album_SongGLList.get(i);
				song_gl_albumStatement.setString(1, album_SongGL.getId());
				song_gl_albumStatement.setString(2, album_SongGL.getSongId());
				song_gl_albumStatement.setString(3, album_SongGL.getAlbumId());
				song_gl_albumStatement.setString(4, album_SongGL.getAlbumId());
				song_gl_albumStatement.addBatch();

				if ((i + 1) % batchSize == 0) {
					song_gl_albumStatement.executeBatch();
				}
			}
			song_gl_albumStatement.executeBatch();
			conn.commit();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
