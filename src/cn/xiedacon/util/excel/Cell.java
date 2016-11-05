package cn.xiedacon.util.excel;

import java.util.Date;

public class Cell {

	private org.apache.poi.ss.usermodel.Cell cell;

	public Cell(org.apache.poi.ss.usermodel.Cell cell) {
		super();
		if (cell == null) {
			cell = new DataCell(null);
		}
		this.cell = cell;
	}

	public Cell(Object data) {
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

	public void copyTo(org.apache.poi.ss.usermodel.Cell cell) {
		if (this.cell instanceof DataCell) {
			((DataCell) (this.cell)).copyTo(cell);
		} else {
			throw new RuntimeException("Cell真实类型不是DataCell");
		}
	}
}
