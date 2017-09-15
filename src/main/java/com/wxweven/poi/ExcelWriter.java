package com.wxweven.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author wxweven
 * @version 1.0
 * @date 2014年9月7日
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:生成Excel文件
 */
public class ExcelWriter {
    public static InputStream getExcelStream(List<String> dataHead, List<String> key,
                                             List<Map<String, Object>> dataList) throws Exception {
        int dataHeadSize = dataHead.size();
        int keySize = key.size();

        if (dataHeadSize != keySize) {
            throw new Exception("表头dataHead 和 key 长度不一致，请检查");
        }

        // 创建Excel的工作书册 Workbook,对应到一个excel文档
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("sheet1");// 创建Excel的工作sheet,对应到一个excel文档的tab
        Row row;//excel中的行
        Cell cell;//excel行中的单元格

        // CellStyle normalStyle = null;//单元格样式:普通格式

        Font font;//字体样式
        font = wb.createFont();
        font.setFontName("微软雅黑");
        font.setBoldweight((short) 100);
        font.setFontHeight((short) 300);
        // normalStyle.setFont(font);// 设置字体

        //创建excel的表头
        row = sheet.createRow(0);//创建表头行
        row.setHeight((short) 500);// 设定行的高度
        for (int cellIndex = 0; cellIndex < dataHeadSize; cellIndex++) {
            //循环创建列
            cell = row.createCell(cellIndex);// 创建一个Excel的单元格
            // cell.setCellStyle(normalStyle);
            cell.setCellValue(dataHead.get(cellIndex));
        }

        // normalStyle.setWrapText(true);// 自动换行

        // 循环创建Excel的sheet的数据行
        for (int rowindex = 1; rowindex <= dataList.size(); rowindex++) {
            row = sheet.createRow(rowindex);//创建行
            row.setHeight((short) 500);// 设定行的高度

            Map<String, Object> rowData = dataList.get(rowindex - 1);//拿到当前行对应的数据map
            for (int cellIndex = 0; cellIndex < keySize; cellIndex++) {
                //循环创建列
                cell = row.createCell(cellIndex);// 创建一个Excel的单元格
                // cell.setCellStyle(normalStyle);
                String cellKey = key.get(cellIndex);
                Object cellValue = rowData.get(cellKey);
                if (cellValue instanceof Number) {
                    cell.setCellValue(Double.valueOf(String.valueOf(cellValue)));
                } else {
                    cell.setCellValue(String.valueOf(cellValue));
                }
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            wb.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            wb.close();
        }

        return new ByteArrayInputStream(baos.toByteArray());

    }

}