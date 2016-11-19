package cn.xiedacon.util.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * 
 * @author xieda
 * @version 1.0
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
		SetDataExcuter.execute(statement, index, data);
	}

	public void addBatch() throws SQLException {
		statement.addBatch();
	}

	public void executeBatch() throws SQLException {
		statement.executeBatch();
	}
}
