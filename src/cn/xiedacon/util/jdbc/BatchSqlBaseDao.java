package cn.xiedacon.util.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public abstract class BatchSqlBaseDao {

	private DataSource dataSource;
	private int batchSize = 100;

	public BatchSqlBaseDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	protected void insert(String sql, List<List<String>> paramsList) {
		excuteWithoutReturn(sql, paramsList);
	}

	private void excuteWithoutReturn(String sql, List<List<String>> paramsList) {
		try (Connection conn = dataSource.getConnection();) {
			conn.setAutoCommit(false);

			PreparedStatement statement = conn.prepareStatement(sql);
			for (int i = 0; i < paramsList.size(); i++) {
				List<String> params = paramsList.get(i);
				for (int j = 0; j < params.size(); j++) {
					statement.setString(j + 1, params.get(j));
				}
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

	protected void update(String sql, List<List<String>> paramsList) {
		excuteWithoutReturn(sql, paramsList);
	}

	protected void delete(String sql, List<List<String>> paramsList) {
		excuteWithoutReturn(sql, paramsList);
	}

	protected List<ResultSet> select(Map<Integer, String> sqls, List<String> paramList) {
		int begin = 0;
		int residue = paramList.size();
		List<ResultSet> resultSetList = new ArrayList<>();
		try (Connection conn = dataSource.getConnection();) {
			while (residue > 0) {
				for (Map.Entry<Integer, String> numAndSql : sqls.entrySet()) {
					int num = numAndSql.getKey();
					String sql = numAndSql.getValue();
					if (residue % num == 0) {
						PreparedStatement statement = conn.prepareStatement(sql);
						for (int i = begin; i < begin + num; i++) {
							String param = paramList.get(i);
							statement.setString(i - begin + 1, param);
						}
						resultSetList.add(statement.executeQuery());
						begin += num;
						residue -= num;
						break;
					}
				}
			}

			return resultSetList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
