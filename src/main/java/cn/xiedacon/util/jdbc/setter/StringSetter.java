package cn.xiedacon.util.jdbc.setter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.xiedacon.util.jdbc.Setter;

/**
 * 
 * @author xiedacon
 * @version v0.0.0
 *
 */
public class StringSetter implements Setter {

	@Override
	public void done(PreparedStatement statement, int index, Object data) throws SQLException {
		statement.setString(index, (String) data);
	}

}
