package cn.xiedacon.util.jdbc.setter;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.xiedacon.util.jdbc.Setter;

/**
 * 
 * @author xiedacon
 * @version v0.0.0
 *
 */
public class DateSetter implements Setter {

	@Override
	public void done(PreparedStatement statement, int index, Object data) throws SQLException {
		statement.setDate(index, new Date(((java.util.Date) data).getTime()));
	}

}
