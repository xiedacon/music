package cn.xiedacon.util.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>批处理工具类</h1>
 * 
 * @author xieda
 * @version v0.0.0
 *
 */
public class BatchSqlUtils {

	private static int defaultBatchSize = 100;

	public static void excuteBatch(Connection conn, String sql, List<List<Object>> rowDatas) throws SQLException {
		excuteBatch(conn, sql, rowDatas, defaultBatchSize);
	}

	/**
	 * 批处理
	 * 
	 * 如需批量查询请选择excuteBatchForSelect方法
	 * 
	 * @param conn{数据库连接（不会自动关闭）}
	 * @param sql{sql语句}
	 * @param rowDatas{执行参数}
	 * @param batchSize{批处理大小}
	 * @throws SQLException
	 */
	public static void excuteBatch(Connection conn, String sql, List<List<Object>> rowDatas, int batchSize)
			throws SQLException {
		if (batchSize <= 0) {
			return;
		}

		// 执行批处理必须关闭自动提交
		Boolean autoCommit = conn.getAutoCommit();
		conn.setAutoCommit(false);

		Statement statement = new Statement(conn.prepareStatement(sql));
		for (int i = 0; i < rowDatas.size(); i++) {
			List<Object> rowData = rowDatas.get(i);
			statement.setRowData(rowData);
			statement.addBatch();

			if ((i + 1) % batchSize == 0) {
				statement.executeBatch();
			}
		}
		statement.executeBatch();

		conn.commit();
		conn.setAutoCommit(autoCommit);
	}

	public static List<ResultSet> excuteBatchForSelect(Connection conn, String sql, List<String> params)
			throws SQLException {
		return excuteBatchForSelect(conn, sql, params, defaultBatchSize);
	}

	/**
	 * 批量查询
	 * 
	 * @param conn{数据库连接（不会自动关闭）}
	 * @param sql{查询语句（单个）}
	 * @param params{查询条件}
	 * @param batchSize{批量大小}
	 * @return
	 * @throws SQLException
	 */
	public static List<ResultSet> excuteBatchForSelect(Connection conn, String sql, List<String> params, int batchSize)
			throws SQLException {
		// 解决?在末尾的情况
		sql += " ";

		String[] fragments = sql.split("\\?");
		if (fragments.length != 2) {
			throw new RuntimeException("暂不支持该sql语句");
		}

		// 前处理
		// sql：SELECT * FROM table t WHERE t.id = ? AND t.name = "aaa"
		// fragments[0]：SELECT * FROM table t WHERE t.id
		// fragments[1]：AND t.name = "aaa"
		fragments[0] = fragments[0].substring(0, fragments[0].lastIndexOf("="));

		// 计算循环的圈数
		int cycle = Math.floorDiv(params.size(), batchSize);
		String cycleSql = BatchSqlUtils.createSql(batchSize, fragments);

		// 计算剩余的数量
		int residue = params.size() % batchSize;
		String residueSql = BatchSqlUtils.createSql(residue, fragments);

		// 查询开始
		List<ResultSet> resultSets = new ArrayList<>();

		int begin = 0;
		int end = 0;
		// cycleParams：begin,end,size
		int[] cycleParams = new int[] { begin, end, batchSize };

		for (int i = 0; i < cycle; i++) {
			resultSets.add(BatchSqlUtils.executeQuery(conn, cycleSql, params, cycleParams));
		}

		cycleParams[1] += residue;
		cycleParams[2] = residue;
		resultSets.add(BatchSqlUtils.executeQuery(conn, residueSql, params, cycleParams));

		return resultSets;

	}

	/**
	 * 根据输入信息执行sql
	 * 
	 * @param conn
	 * @param sql
	 * @param params{待输入数据}
	 * @param cycleParams{内部循环参数}
	 * @return
	 * @throws SQLException
	 */
	private static ResultSet executeQuery(Connection conn, String sql, List<String> params, int[] cycleParams)
			throws SQLException {
		// cycleParams：begin,end,size
		cycleParams[1] += cycleParams[2];
		PreparedStatement statement = conn.prepareStatement(sql);

		for (int j = 0; j < cycleParams[2]; j++) {
			statement.setString(j + 1, params.get(cycleParams[0] + j));
		}
		cycleParams[0] = cycleParams[1];

		return statement.executeQuery();
	}

	/**
	 * 根据输入的num与fragments创建sql语句
	 * 
	 * @param num{查询参数个数}
	 * @param fragments{sql片段}
	 * 
	 * @return
	 */
	private static String createSql(int num, String[] fragments) {
		StringBuilder sql = new StringBuilder(fragments[0]);
		sql.append("IN(");
		for (int i = 0; i < num - 1; i++) {
			sql.append("?,");
		}
		sql.append("?)");
		sql.append(fragments[1]);
		return sql.toString();
	}
}
