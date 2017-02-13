package cn.xiedacon.util.excel;

import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * <h1>org.apache.poi.ss.usermodel.Cell包装类</h1>
 * <h3>依赖：</h3>
 * <ul>
 * <li>poi</li>
 * </ul>
 * 
 * @author xiedacon
 * @version v0.0.0
 *
 */
public class XSSFCell implements Cell {

	private org.apache.poi.ss.usermodel.Cell cell;

	public XSSFCell(org.apache.poi.ss.usermodel.Cell cell) {
		if (cell == null) {
			cell = new DataCell(null);
		}
		this.cell = cell;
	}

	public XSSFCell(Object data) {
		this.cell = new DataCell(data);
	}

	public String getString() {
		return cell.getStringCellValue();
	}

	public Integer getInteger() {
		Double value = getDouble();
		if (value == null) {
			return null;
		} else {
			return value.intValue();
		}
	}

	public Double getDouble() {
		return cell.getNumericCellValue();
	}

	public Date getDate() {
		return cell.getDateCellValue();
	}

	public Boolean getBoolean() {
		return cell.getBooleanCellValue();
	}

	public void setString(String value) {
		cell.setCellValue(value);
	}

	public void setInteger(Integer value) {
		cell.setCellValue(value.doubleValue());
	}

	public void setDouble(Double value) {
		cell.setCellValue(value);
	}

	public void setDate(Date value) {
		cell.setCellValue(value);
	}

	public void setBoolean(Boolean value) {
		cell.setCellValue(value);
	}

	/**
	 * 向Cell中复制数据
	 * 
	 * @param cell
	 */
	public void copyDataTo(Cell cell) {
		if (this.cell instanceof DataCell) {
			((DataCell) (this.cell)).copyDataTo(cell);
		} else {
			throw new RuntimeException("Cell真实类型不是DataCell，无法调用该方法");
		}
	}

	private class DataCell implements org.apache.poi.ss.usermodel.Cell {
		private Object data = null;

		public DataCell(Object data) {
			this.data = data;
		}

		@Override
		public int getColumnIndex() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public int getRowIndex() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public Sheet getSheet() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public Row getRow() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public void setCellType(int arg0) {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public void setCellType(CellType arg0) {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public int getCellType() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public CellType getCellTypeEnum() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public int getCachedFormulaResultType() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public CellType getCachedFormulaResultTypeEnum() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public void setCellValue(double value) {
			data = value;
		}

		@Override
		public void setCellValue(Date value) {
			data = value;
		}

		@Override
		public void setCellValue(Calendar value) {
			data = value;
		}

		@Override
		public void setCellValue(RichTextString value) {
			data = value;
		}

		@Override
		public void setCellValue(String value) {
			data = value;
		}

		@Override
		public void setCellFormula(String arg0) throws FormulaParseException {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public String getCellFormula() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public double getNumericCellValue() {
			return (Double) data;
		}

		@Override
		public Date getDateCellValue() {
			return (Date) data;
		}

		@Override
		public RichTextString getRichStringCellValue() {
			return (RichTextString) data;
		}

		@Override
		public String getStringCellValue() {
			return (String) data;
		}

		@Override
		public void setCellValue(boolean value) {
			data = value;
		}

		@Override
		public void setCellErrorValue(byte arg0) {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public boolean getBooleanCellValue() {
			return (Boolean) data;
		}

		@Override
		public byte getErrorCellValue() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public void setCellStyle(CellStyle arg0) {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public CellStyle getCellStyle() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public void setAsActiveCell() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public CellAddress getAddress() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public void setCellComment(Comment arg0) {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public Comment getCellComment() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public void removeCellComment() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public Hyperlink getHyperlink() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public void setHyperlink(Hyperlink arg0) {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public void removeHyperlink() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public CellRangeAddress getArrayFormulaRange() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public boolean isPartOfArrayFormulaGroup() {
			throw new java.lang.UnsupportedOperationException();
		}

		public void copyDataTo(Cell cell) {
			if (data == null) {
				return;
			}

			if (data instanceof String) {
				cell.setString((String) data);
			} else if (data instanceof Double) {
				cell.setDouble((Double) data);
			} else if (data instanceof Integer) {
				cell.setInteger(((Integer) data));
			} else if (data instanceof Date) {
				cell.setDate((Date) data);
			} else if (data instanceof Boolean) {
				cell.setBoolean((Boolean) data);
			} else {
				throw new RuntimeException("不支持该类型：" + data.getClass().getName());
			}
		}
	}
}
