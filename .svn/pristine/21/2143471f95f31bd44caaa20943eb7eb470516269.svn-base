package com.plat.acoal.utils;

import com.plat.acoal.model.AlarmModel;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class PoiUtils {
    public static ResponseEntity<byte[]> exportAlarmModel2Excel(List<AlarmModel> alarmModelList){
        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;
        try {
            //1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.创建文档摘要
            workbook.createInformationProperties();
            //3.获取文档信息，并配置
            DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
            //3.1文档类别
            dsi.setCategory("报警信息");
            //3.2设置文档管理员
            dsi.setManager("");
            //3.3设置组织机构
            dsi.setCompany("智慧煤场应急管理云平台");
            //4.获取摘要信息并配置
            SummaryInformation si = workbook.getSummaryInformation();
            //4.1设置文档主题
            si.setSubject("报警信息表");
            //4.2.设置文档标题
            si.setTitle("报警信息");
            //4.3 设置文档作者
            si.setAuthor("智慧煤场应急管理云平台");
            //4.4设置文档备注
            si.setComments("备注信息暂无");
            //创建Excel表单
            HSSFSheet sheet = workbook.createSheet("报警信息表");
            //创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            //创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //定义列的宽度
            /*sheet.setColumnWidth(0, 5 * 256);
            sheet.setColumnWidth(1, 12 * 256);
            sheet.setColumnWidth(2, 10 * 256);
            sheet.setColumnWidth(3, 5 * 256);
            sheet.setColumnWidth(4, 12 * 256);
            sheet.setColumnWidth(5, 20 * 256);
            sheet.setColumnWidth(6, 10 * 256);
            sheet.setColumnWidth(7, 10 * 256);
            sheet.setColumnWidth(8, 16 * 256);
            sheet.setColumnWidth(9, 12 * 256);
            sheet.setColumnWidth(10, 15 * 256);
            sheet.setColumnWidth(11, 20 * 256);
            sheet.setColumnWidth(12, 16 * 256);
            sheet.setColumnWidth(13, 14 * 256);
            sheet.setColumnWidth(14, 14 * 256);
            sheet.setColumnWidth(15, 12 * 256);
            sheet.setColumnWidth(16, 8 * 256);
            sheet.setColumnWidth(17, 16 * 256);
            sheet.setColumnWidth(18, 20 * 256);
            sheet.setColumnWidth(19, 12 * 256);
            sheet.setColumnWidth(20, 8 * 256);
            sheet.setColumnWidth(21, 25 * 256);
            sheet.setColumnWidth(22, 14 * 256);
            sheet.setColumnWidth(23, 12 * 256);
            sheet.setColumnWidth(24, 12 * 256);*/
            //5.设置表头
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell0 = headerRow.createCell(0);
            cell0.setCellValue("报警类型");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell1 = headerRow.createCell(1);
            cell1.setCellValue("报警时间");
            cell1.setCellStyle(headerStyle);
            HSSFCell cell2 = headerRow.createCell(2);
            cell2.setCellValue("报警数据");
            cell2.setCellStyle(headerStyle);
            HSSFCell cell3 = headerRow.createCell(3);
            cell3.setCellValue("报警次数");
            cell3.setCellStyle(headerStyle);
            HSSFCell cell4 = headerRow.createCell(4);
            cell4.setCellValue("报警级别");
            cell4.setCellStyle(headerStyle);
            HSSFCell cell5 = headerRow.createCell(5);
            cell5.setCellValue("设备");
            cell5.setCellStyle(headerStyle);
            HSSFCell cell6 = headerRow.createCell(6);
            cell6.setCellValue("区域");
            cell6.setCellStyle(headerStyle);
            HSSFCell cell7 = headerRow.createCell(7);
            cell7.setCellValue("位置");
            cell7.setCellStyle(headerStyle);
            HSSFCell cell8 = headerRow.createCell(8);
            cell8.setCellValue("是否处理");
            cell8.setCellStyle(headerStyle);
            HSSFCell cell9 = headerRow.createCell(9);
            cell9.setCellValue("信息");
            cell9.setCellStyle(headerStyle);

            HSSFCellStyle redStyle = workbook.createCellStyle();
            redStyle.setFillForegroundColor(IndexedColors.RED.index);
            redStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            //6.装数据
            for (int i = 0; i < alarmModelList.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                AlarmModel alarmModel = alarmModelList.get(i);
                row.createCell(0).setCellValue(alarmModel.getAlarmTypeName());
                HSSFCell alarmTimeCell = row.createCell(1);
                alarmTimeCell.setCellValue(alarmModel.getAlarmTime());
                alarmTimeCell.setCellStyle(dateCellStyle);
                row.createCell(2).setCellValue(alarmModel.getAlarmData());
                row.createCell(3).setCellValue(alarmModel.getAlarmCount());
                row.createCell(4).setCellValue(alarmModel.getAlarmGradeName());
                row.createCell(5).setCellValue(alarmModel.getDevName());
                row.createCell(6).setCellValue(alarmModel.getRegionName());
                row.createCell(7).setCellValue(alarmModel.getDevSite());
                if(alarmModel.getAlarmDis() == 0){
                    row.createCell(8).setCellStyle(redStyle);
                    row.getCell(8).setCellValue("未处理");
                }else{
                    row.createCell(8).setCellValue("已处理");
                }
                row.createCell(9).setCellValue(alarmModel.getAlarmInfo());
            }
            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", new String("报警信息表.xls".getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos = new ByteArrayOutputStream();
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }
}
