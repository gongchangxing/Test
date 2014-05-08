package com.dhcc.poi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;


public class PoiCreateExcel {

	public static void main(String[] args) throws IOException {

		// 创建excel工作书册workbook，对应到一个excel文档.
		HSSFWorkbook wb = new HSSFWorkbook();

		// 创建excel的工作sheet，对应excel文档的tab
		HSSFSheet sheet = wb.createSheet("测试sheet");

		// 设置excel每列宽度.
		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 3000);

		// 创建字体样式.
		HSSFFont font = wb.createFont();
		font.setFontName("微软雅黑");
		font.setBoldweight((short) 100);
		font.setFontHeight((short) 300);
		font.setColor(HSSFColor.BLUE.index);

		// 创建单元格样式.
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(HSSFColor.GREEN.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// 设置边框
		style.setBottomBorderColor(HSSFColor.RED.index);
		style.setBorderBottom(HSSFCellStyle.BORDER_THICK);
		style.setBorderLeft(HSSFCellStyle.BORDER_THICK);
		style.setBorderRight(HSSFCellStyle.BORDER_THICK);
		style.setBorderTop(HSSFCellStyle.BORDER_THICK);

		// 设置字体
		style.setFont(font);

		// 创建excel的sheet的一行.
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 500);// 设置行高

		// 创建excel的一个单元格
		HSSFCell cell = row.createCell(0);

		// 合并单元格(startRow，endRow，startColumn，endColumn)
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));

		// 给单元格设置样式和赋值
		cell.setCellStyle(style);
		cell.setCellValue("hello poi");

		// 设置单元格内容格式
		HSSFCellStyle style1 = wb.createCellStyle();
		style1.setDataFormat(HSSFDataFormat.getBuiltinFormat("h:mm:ss"));

		style1.setWrapText(true);// 自动换行.

		row = sheet.createRow(1);
		
		//设置单元格的样式格式
		cell = row.createCell(0);
		cell.setCellStyle(style1);
		cell.setCellValue(new Date());
		
		//创建超链接
		HSSFHyperlink link = new HSSFHyperlink(HSSFHyperlink.LINK_URL);
		link.setAddress("http://www.baidu.com");
		cell = row.createCell(1);
		cell.setCellValue("百度");
		cell.setHyperlink(link); //设定单元格的超链接.
		
		FileOutputStream out = new FileOutputStream("D:\\workbook.xls");
		wb.write(out);
		out.close();

	}

}
