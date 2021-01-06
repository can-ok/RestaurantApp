package com.resturant.restapi.external;

import com.resturant.restapi.Model.Customer;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class ExcelFileExporter {



    @SneakyThrows
    public static ByteArrayInputStream exportCustomerList(List<Customer> customerList){

        Workbook workbook=new XSSFWorkbook();
        Sheet sheet =workbook.createSheet("Customer");

        Row headerRow=sheet.createRow(0);

        CellStyle headerCellStyle=workbook.createCellStyle();
        headerCellStyle.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Cell cell=headerRow.createCell(0);
        cell.setCellValue("FIRSTNAME");
        cell.setCellStyle(headerCellStyle);

        cell=headerRow.createCell(1);
        cell.setCellValue("LASTNAME");
        cell.setCellStyle(headerCellStyle);

        cell=headerRow.createCell(2);
        cell.setCellValue("CITY");
        cell.setCellStyle(headerCellStyle);


        cell=headerRow.createCell(3);
        cell.setCellValue("ADDRESS");
        cell.setCellStyle(headerCellStyle);

        cell=headerRow.createCell(4);
        cell.setCellValue("PHONENUMBER");
        cell.setCellStyle(headerCellStyle);


        for(int i=0; i<customerList.size(); i++){
            Row dataRow=sheet.createRow(i+1);
            dataRow.createCell(0).setCellValue(customerList.get(i).getFirstName());
            dataRow.createCell(1).setCellValue(customerList.get(i).getLastName());
            dataRow.createCell(2).setCellValue(customerList.get(i).getCity());
            dataRow.createCell(3).setCellValue(customerList.get(i).getAddress());
            dataRow.createCell(4).setCellValue(customerList.get(i).getPhoneNumber());
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        workbook.write(outputStream);

        
        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
