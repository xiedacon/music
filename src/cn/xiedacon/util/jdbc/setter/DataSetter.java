package cn.xiedacon.util.jdbc.setter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DataSetter {

	public void set(PreparedStatement statement, int index, Object data) {
		try {
			this.reallySet(statement, index, data);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	};

	protected abstract void reallySet(PreparedStatement statement, int index, Object data) throws SQLException;

}
