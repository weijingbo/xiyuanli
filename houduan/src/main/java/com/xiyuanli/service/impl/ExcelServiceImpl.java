package com.xiyuanli.service.impl;

import com.xiyuanli.entity.VO.WaterShell;
import com.xiyuanli.mapper.WaterMapper;
import com.xiyuanli.service.ExcelService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/*
垂直水平居中风格
 */
@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private WaterMapper waterMapper;

    private static CellStyle CreateStyle(Workbook wb) {
        final CellStyle style = wb.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直
        style.setAlignment(HorizontalAlignment.CENTER);//水平
        style.setWrapText(true);//指定当前单元格内容显示不下时自动换行
        return style;
    }

    private static List<CellRangeAddress> cellRangeAddressList = new ArrayList<CellRangeAddress>();

    private static void mergeCells(final Sheet sheet, final int firstRow, final int lastRow, final int firstCol, final int lastCol) {
        CellRangeAddress cellAddress = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        sheet.addMergedRegion(cellAddress);
        cellRangeAddressList.add(cellAddress);
    }

    @Override
    public void createWaterSheel(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8082");
        response.setHeader("Sec-Fetch-Dest", "document");
        //设置sheet名称，并创建新的sheet对象
        String sheetName = "水费用户表";
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=water.xlsx");
        response.setContentType("application/vnd.ms-excel");
        List<WaterShell> waterShells = waterMapper.waterShell();
        XSSFWorkbook wb = new XSSFWorkbook();
        //创建新的表格
        XSSFSheet assSheet = wb.createSheet(sheetName);
        //格式设置，存入wb

        CellStyle style = CreateStyle(wb);

        //创建表头
        Row titleRow = assSheet.createRow(0);
        //标题字段
        String[] title = {"月份", "业主编号", "业主姓名", "楼栋", "门牌号", "用水量", "水费", "状态"};
        //把已写好的标题写入excel中
        for (int i = 0; i < title.length; i++) {
            Cell cell = titleRow.createCell(i);
            cell.setCellStyle(style);
            //存入名称
            cell.setCellValue(title[i]);
        }

        Row row = null;
        for (int i = 0; i < waterShells.size(); i++) {
            assSheet.autoSizeColumn(i, true);
            //单元格的列宽设置
            row = assSheet.createRow(i + 1);
            assSheet.setColumnWidth(0, 256 * 20);
            assSheet.setColumnWidth(1, 256 * 20);
            assSheet.setColumnWidth(2, 256 * 20);
            assSheet.setColumnWidth(3, 256 * 20);
            assSheet.setColumnWidth(4, 256 * 20);
            assSheet.setColumnWidth(5, 256 * 20);
            assSheet.setColumnWidth(6, 256 * 20);
            assSheet.setColumnWidth(7, 256 * 20);
            //把值写进单元格
            row.createCell(1).setCellValue(waterShells.get(i).getUid());
            row.createCell(2).setCellValue(waterShells.get(i).getUname());
            row.createCell(3).setCellValue(waterShells.get(i).getBname());
            row.createCell(4).setCellValue(waterShells.get(i).getNumber());
            //自动换行
            row.getCell(4).setCellStyle(style);
        }


        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        outputStream.close();
        wb.close();

    }

    @Override
    public void createPropertySheel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置sheet名称，并创建新的sheet对象
        String sheetName = "物业费用户表";
        response.setCharacterEncoding("UTF-8");

        response.setHeader("Content-Disposition", "attachment;filename=property.xlsx");
        response.setContentType("application/vnd.ms-excel");
        List<WaterShell> waterShells = waterMapper.waterShell();
        XSSFWorkbook wb = new XSSFWorkbook();
        //创建新的表格
        XSSFSheet assSheet = wb.createSheet(sheetName);
        //格式设置，存入wb

        CellStyle style = CreateStyle(wb);

        //创建表头
        Row titleRow = assSheet.createRow(0);
        //标题字段
        String[] title = {"年份", "业主编号", "业主姓名", "楼栋", "门牌号", "房屋大小", "物业费", "状态"};
        //把已写好的标题写入excel中
        for (int i = 0; i < title.length; i++) {
            Cell cell = titleRow.createCell(i);
            cell.setCellStyle(style);
            //存入名称
            cell.setCellValue(title[i]);
        }

        Row row = null;
        for (int i = 0; i < waterShells.size(); i++) {
            assSheet.autoSizeColumn(i, true);
            //单元格的列宽设置
            row = assSheet.createRow(i + 1);
            assSheet.setColumnWidth(0, 256 * 20);
            assSheet.setColumnWidth(1, 256 * 20);
            assSheet.setColumnWidth(2, 256 * 20);
            assSheet.setColumnWidth(3, 256 * 20);
            assSheet.setColumnWidth(4, 256 * 20);
            assSheet.setColumnWidth(5, 256 * 20);
            assSheet.setColumnWidth(6, 256 * 20);
            assSheet.setColumnWidth(7, 256 * 20);
            //把值写进单元格
            row.createCell(1).setCellValue(waterShells.get(i).getUid());
            row.createCell(2).setCellValue(waterShells.get(i).getUname());
            row.createCell(3).setCellValue(waterShells.get(i).getBname());
            row.createCell(4).setCellValue(waterShells.get(i).getNumber());
            row.createCell(5).setCellValue(Double.parseDouble(String.valueOf(waterShells.get(i).getSize())));
            //自动换行
            row.getCell(5).setCellStyle(style);
        }
        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        outputStream.close();
        wb.close();

    }
}
