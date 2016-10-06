package cn.xiedacon.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.xiedacon.model.Singer;

@Repository
public class BatchSqlDaoImpl implements BatchSqlDao {

	@Autowired
	private ComboPooledDataSource dataSource;

	@Override
	public void insertSinger(List<Singer> singerList) {
		String sql = "INSERT INTO singer VALUES(?,?,?,?,?,?,?,?,?)";
		int batchSize = 1000;

		try (Connection conn = dataSource.getConnection();) {
			conn.setAutoCommit(false);

			PreparedStatement statement = conn.prepareStatement(sql);

			try {
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
				conn.rollback();
				throw e;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
