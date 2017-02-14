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
public class LongSetter implements Setter {

	@Override
	public void done(PreparedStatement statement, int index, Object data) throws SQLException {
		statement.setLong(index, (Long) data);
	}

}
