package cn.xiedacon.util.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <h1>Setter接口</h1>
 * <h3>功能：</h3>
 * <ul>
 * <li>屏蔽Statement对各类型set方法的区别</li>
 * </ul>
 * 
 * @author xiedacon
 * @version v0.0.0
 *
 */
public interface Setter {

	public void done(PreparedStatement statement, int index, Object data) throws SQLException;

}
