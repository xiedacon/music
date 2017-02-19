package cn.xiedacon.util.excel;

import java.util.Date;

/**
 * <h1>Cell接口</h1>
 * <h3>功能：</h3>
 * <ul>
 * <li>poi中Cell的数据接口</li>
 * <li>copyTo方法用于复制两个Cell中的数据</li>
 * </ul>
 * <h3>依赖：</h3>
 * <ul>
 * <li>poi</li>
 * </ul>
 * 
 * @author xiedacon
 * @version v0.0.0
 *
 */

public interface Cell {

	public String getString();

	public Integer getInteger();

	public Double getDouble();

	public Date getDate();

	public Boolean getBoolean();

	public void setString(String value);

	public void setInteger(Integer value);

	public void setDouble(Double value);

	public void setDate(Date value);

	public void setBoolean(Boolean value);

	public void copyDataTo(Cell cell);
}
