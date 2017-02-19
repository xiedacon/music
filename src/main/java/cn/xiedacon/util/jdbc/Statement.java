package cn.xiedacon.util.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * <h1>Statement包装类</h1>
 * 
 * @author xieda
 * @version v0.0.0
 *
 */
public class Statement {

	private PreparedStatement statement;

	public Statement(PreparedStatement statement) {
		super();
		this.statement = statement;
	}

	public void setRowData(List<Object> rowData) {
		for (int i = 0; i < rowData.size(); i++) {
			this.setData(i + 1, rowData.get(i));
		}
	}

	public void setData(int index, Object data) {
		try {
			SetExecuter.execute(statement, index, data);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void addBatch() throws SQLException {
		statement.addBatch();
	}

	public void executeBatch() throws SQLException {
		statement.executeBatch();
	}
}
