package com.wxweven.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * 
 * @author wxweven
 * @date 2014年11月17日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:Excel 读取，支持03和07版本
 */
public class ExcelReaderADV {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File("d:/book1.xls");
		if(!file.exists()){
			System.out.println("文件不存在");
			return;
		}
		ExcelReaderADV rf = new ExcelReaderADV();
		rf.readExcel(file);
	}
	
	/**
	 * 读取Excel数据
	 * @param file
	 */
	public void readExcel(File file){
		try {
			InputStream inputStream = new FileInputStream(file);
			String fileName = file.getName();
			Workbook wb = null;
			if(fileName.endsWith("xls")){
				wb = new HSSFWorkbook(inputStream);//解析xls格式
			}else if(fileName.endsWith("xlsx")){
				wb = new XSSFWorkbook(inputStream);//解析xlsx格式
			}
			Sheet sheet = wb.getSheetAt(0);//第一个工作表
			
			int firstRowIndex = sheet.getFirstRowNum();//第一个数据行
			int lastRowIndex = sheet.getLastRowNum();//最后一个数据行
			/**遍历数据行*/
			for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex ++){
				Row row = sheet.getRow(rIndex);
				if(row != null){
					int firstCellIndex = row.getFirstCellNum();
					int lastCellIndex = row.getLastCellNum();
					/**遍历当前行的列*/
					for(int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex ++){
						Cell cell = row.getCell(cIndex);
						String value = "";
						if(cell != null){
							value = cell.toString();
							System.out.print(value+"\t");
						}
					}
					System.out.println();
					System.out.println("行结束");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
