package cn.xiedacon.util.jdbc.setter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StringSetter extends DataSetter {

	@Override
	protected void reallySet(PreparedStatement statement, int index, Object data) throws SQLException {
		statement.setString(index, (String) data);
	}

}
