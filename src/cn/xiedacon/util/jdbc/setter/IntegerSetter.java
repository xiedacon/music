package cn.xiedacon.util.jdbc.setter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IntegerSetter extends DataSetter {

	@Override
	public void reallySet(PreparedStatement statement, int index, Object data) throws SQLException {
		statement.setInt(index, (Integer) data);
	}

}
