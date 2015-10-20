package com.wxweven.poi;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * 
 * @author wxweven
 * @date 2014年9月7日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description: 读取Excel文件
 */
public class ExcelReader {

	public static void main(String[] args) {
		HSSFWorkbook wb = null;
		POIFSFileSystem fs = null;
		try {
			// 设置要读取的文件路径
			fs = new POIFSFileSystem(new FileInputStream("d:\\book1.xls"));
			// HSSFWorkbook相当于一个excel文件，HSSFWorkbook是解析excel2007之前的版本（xls）
			// 之后版本使用XSSFWorkbook（xlsx）
			wb = new HSSFWorkbook(fs);
			// 获得sheet工作簿
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = sheet.getRow(3);// 获得行，下标从0开始
			HSSFCell cell = row.getCell(0);// 获得行中的列，即单元格，列下标也是从0开始

			// 获得单元格中的值，这里该单元格的值为数字，所以使用getNumericCellValue，如为字符串则会报错
			// 如何取别的值，见print2方法
			double msg = cell.getNumericCellValue();
			// System.out.println("第4行，第1列的值："+msg);
//			printExcelTotalAsText();
			printExcelContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用来获得整个excel文件的内容，表示为字符串
	 * 
	 * @throws Exception
	 */
	public static void printExcelTotalAsText() throws Exception {
		InputStream is = new FileInputStream("d:\\book1.xls");// Excel文件path
		HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(is));
		// A text extractor for Excel files.
		// Returns the textual content of the file, suitable for indexing by
		// something like Lucene,
		// but not really intended for display to the user.

		ExcelExtractor extractor = new ExcelExtractor(wb);
		// 字符串所包含的类型，详见api
		extractor.setIncludeSheetNames(true);
		extractor.setFormulasNotResults(false);
		extractor.setIncludeCellComments(true);
		// 获得字符串形式
		String text = extractor.getText();
		System.out.println("整个Excel的字符串为：\n" + text);
	}

	/**
	 * 
	 * @throws Exception
	 */
	public static void printExcelContent() throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream("d:\\book1.xls"));
		HSSFSheet sheet = wb.getSheetAt(0);
		Iterator<Row> rowItr = (Iterator<Row>) sheet.rowIterator();// 行迭代器
		Row row = null;// 行对象
		Iterator<Cell> cellItr = null;// 列迭代器
		Cell cell = null;// 列对象

		// 迭代行
		while (rowItr.hasNext()) {
			row = rowItr.next();
			// 迭代列
			cellItr = (Iterator<Cell>) row.cellIterator();
			while (cellItr.hasNext()) {
				cell = cellItr.next();
				// 用于测试的文件就2列，第一列为数字，第二列为字符串
				// 对于数字cell.getCellType的值为HSSFCell.CELL_TYPE_NUMERIC，为0
				// 对于字符串cell.getCellType的值为HSSFCell.CELL_TYPE_STRING，为1
				// 完整的类型列表请查看api
				String content = cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ? cell.getNumericCellValue() + ""
						: cell.getStringCellValue();
				System.out.println(content);
			}
		}
	}
}