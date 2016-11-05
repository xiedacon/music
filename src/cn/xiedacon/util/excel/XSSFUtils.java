package cn.xiedacon.util.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XSSFUtils {

	public static List<List<cn.xiedacon.util.excel.Cell>> parse(File file) {
		return parse(file, 0, null, 0, null, 0, null);
	}

	/**
	 * 适用于有头部
	 * 
	 * @param file
	 * @param beginRowNum
	 * @return
	 */
	public static List<List<cn.xiedacon.util.excel.Cell>> parse(File file, Integer beginRowNum) {
		return parse(file, 0, null, beginRowNum, null, 0, null);
	}

	/**
	 * 头部和行限制
	 * 
	 * @param file
	 * @param beginRowNum
	 * @param cellLimit
	 * @return
	 */
	public static List<List<cn.xiedacon.util.excel.Cell>> parse(File file, Integer beginRowNum, Integer cellLimit) {
		return parse(file, 0, null, beginRowNum, null, 0, cellLimit);
	}

	/**
	 * 基础方法
	 * 
	 * @param file
	 * @param beginSheetNum
	 * @param sheetLimit
	 * @param beginRowNum
	 * @param rowLmit
	 * @param beginCellNum
	 * @param cellLimit
	 * @return
	 */
	public static List<List<cn.xiedacon.util.excel.Cell>> parse(File file, Integer beginSheetNum, Integer sheetLimit,
			Integer beginRowNum, Integer rowLmit, Integer beginCellNum, Integer cellLimit) {
		List<List<cn.xiedacon.util.excel.Cell>> result = new ArrayList<>();
		if (file == null) {
			return result;
		}
		try (XSSFWorkbook workbook = new XSSFWorkbook(file);) {
			if (beginSheetNum == null || beginSheetNum < 0) {
				beginSheetNum = 0;
			}
			int firstSheetNum = 0 + beginSheetNum;
			int lastSheetNum = workbook.getNumberOfSheets() - 1;
			if (sheetLimit != null && sheetLimit > 0) {
				lastSheetNum = firstSheetNum + sheetLimit;
			}

			for (int i = firstSheetNum; i < lastSheetNum + 1; i++) {
				result.addAll(parseSheet(workbook.getSheetAt(i), beginRowNum, rowLmit, beginCellNum, cellLimit));
			}

			return result;
		} catch (InvalidFormatException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 解析表
	 * 
	 * @param sheet
	 * @param beginRowNum
	 * @param rowLmit
	 * @param beginCellNum
	 * @param cellLimit
	 * @return
	 */
	private static List<List<cn.xiedacon.util.excel.Cell>> parseSheet(Sheet sheet, Integer beginRowNum, Integer rowLmit,
			Integer beginCellNum, Integer cellLimit) {
		List<List<cn.xiedacon.util.excel.Cell>> rowList = new ArrayList<>();
		if (sheet == null) {
			return rowList;
		}

		if (beginRowNum == null || beginRowNum < 0) {
			beginRowNum = 0;
		}
		int firstRowNum = sheet.getFirstRowNum() + beginRowNum;
		int lastRowNum = sheet.getPhysicalNumberOfRows();
		if (rowLmit != null && rowLmit > 0) {
			lastRowNum = firstRowNum + rowLmit;
		}

		for (int i = firstRowNum; i < lastRowNum + 1; i++) {
			List<cn.xiedacon.util.excel.Cell> row = parseRow(sheet.getRow(i), beginCellNum, cellLimit);
			if (row.isEmpty()) {
				break;
			} else {
				rowList.add(row);
			}
		}

		return rowList;
	}

	/**
	 * 解析行
	 * 
	 * @param row
	 * @param beginCellNum
	 * @param cellLimit
	 * @return
	 */
	private static List<Cell> parseRow(Row row, Integer beginCellNum, Integer cellLimit) {
		List<Cell> cellList = new ArrayList<>();
		if (row == null) {
			return cellList;
		}

		if (beginCellNum == null || beginCellNum < 0) {
			beginCellNum = 0;
		}
		int firstCellNum = row.getFirstCellNum() + beginCellNum;
		int lastCellNum = row.getLastCellNum();
		if (cellLimit != null && cellLimit > 0) {
			lastCellNum = firstCellNum + cellLimit;
		}

		for (int i = firstCellNum; i < lastCellNum + 1; i++) {
			cellList.add(new Cell(row.getCell(i)));
		}

		return cellList;
	}

	public static void write(File xssfFile, File templateFile, List<List<Cell>> dataCellsList, Integer beginRowNum) {
		XSSFUtils.write(xssfFile, templateFile, dataCellsList, beginRowNum, 0);
	}

	public static void write(File xssfFile, File templateFile, List<List<Cell>> dataCellsList, Integer beginRowNum,
			Integer beginCellNum) {
		try (XSSFWorkbook workbook = new XSSFWorkbook(templateFile);
				FileOutputStream out = new FileOutputStream(xssfFile)) {
			Sheet sheet = workbook.getSheetAt(0);
			createSheet(dataCellsList, sheet, beginRowNum, beginCellNum);
			workbook.write(out);
		} catch (InvalidFormatException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void createSheet(List<List<Cell>> dataCellsList, Sheet sheet, Integer beginRowNum,
			Integer beginCellNum) {
		int endRowNum = beginRowNum + dataCellsList.size();
		for (int i = beginRowNum; i < endRowNum; i++) {
			Row row = sheet.createRow(i);
			List<Cell> dataCells = dataCellsList.get(i - beginRowNum);
			createRow(dataCells, row, beginCellNum);
		}
	}

	private static void createRow(List<Cell> dataCells, Row row, Integer beginCellNum) {
		int endCellNum = beginCellNum + dataCells.size();
		for (int j = beginCellNum; j < endCellNum; j++) {
			org.apache.poi.ss.usermodel.Cell cell = row.createCell(j);
			Cell dataCell = dataCells.get(j);
			dataCell.copyTo(cell);
		}
	}
}
