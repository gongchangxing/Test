package com.dhcc.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;





/**
 * 
 * 有模板，文件头标题都写好，只要循环添加数据的方法 
 * 
 * 
 * @author zx
 * @createDate 2014-4-11
 * @since TODO: 来源版本
 *
 */

public class PoiTestExcel {

	public static void main(String[] args) throws IOException {
		InputStream in = new FileInputStream("D:\\workbook.xls");
		Workbook wb = new HSSFWorkbook(in);

		// 得到excel的第0张表
		Sheet sheet = wb.getSheetAt(0);

		// 得到第一行第一个单元格的样式
		Row rowCellStyle = sheet.getRow(0);
		//CellStyle columnOne = rowCellStyle.getCell(0).getCellStyle();
		// 这里面的行和列的数法与计算机里的一样，从0开始是第一
		// 填充title数据
		Row row = sheet.getRow(0);
		Cell cell = row.getCell(0);
		cell.setCellValue("2014年海投花名册");
		int i = 2;
		int number  = 0;
		for (; i < 10; i++) {
			row = sheet.createRow(i);// 得到行  
            cell = row.createCell(0);// 得到第0个单元格  
            cell.setCellValue("琳"+i);// 填充值  
            //cell.setCellStyle(columnOne);// 填充样式  
            cell = row.createCell(1);  
            cell.setCellValue("女");  
           // cell.setCellStyle(columnOne);// 填充样式  
            cell = row.createCell(2);  
            cell.setCellValue(i+20);  
            //cell.setCellStyle(columnOne);// 填充样式  
            // .....给每个单元格填充数据和样式  
            number++;  

		}
		 //创建每个单元格，添加样式，最后合并  
        row = sheet.createRow(i);  
        cell = row.createCell(0);  
        cell.setCellValue("总计：" + number + "个学生");// 填充值  
        //cell.setCellStyle(columnOne);  
        cell = row.createCell(1);  
        //cell.setCellStyle(columnOne);  
        cell = row.createCell(2);  
       // cell.setCellStyle(columnOne);  
        // 合并单元格  
        sheet.addMergedRegion(new CellRangeAddress(i,i,0,2));  
        FileOutputStream os = new FileOutputStream("D:\\workbook2.xls");  
        wb.write(os);  
        os.close();  

	}

}
