package cn.xiedacon.util.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.xiedacon.util.jdbc.setter.BooleanSetter;
import cn.xiedacon.util.jdbc.setter.DateSetter;
import cn.xiedacon.util.jdbc.setter.DoubleSetter;
import cn.xiedacon.util.jdbc.setter.IntegerSetter;
import cn.xiedacon.util.jdbc.setter.LongSetter;
import cn.xiedacon.util.jdbc.setter.StringSetter;

/**
 * <h1>SetExecutor工具类</h1>
 * <h3>功能：</h3>
 * <ul>
 * <li>屏蔽Statement对各类型set方法的区别</li>
 * </ul>
 * 
 * @author xiedacon
 * @version v0.0.0
 *
 */
public class SetExecuter {

	@SuppressWarnings("rawtypes")
	private static Map<Class, Setter> setters;

	/**
	 * 这样属于硬编码不好 预计修改成hibernate validator框架一样的xml形式 不过并不觉得那是最优解
	 */
	static {
		setters = new HashMap<>();

		setters.put(Integer.class, new IntegerSetter());
		setters.put(Boolean.class, new BooleanSetter());
		setters.put(Date.class, new DateSetter());
		setters.put(Double.class, new DoubleSetter());
		setters.put(Long.class, new LongSetter());
		setters.put(String.class, new StringSetter());
	}

	public static void execute(PreparedStatement statement, int index, Object data) throws SQLException {
		setters.get(data.getClass()).done(statement, index, data);
	}
}
