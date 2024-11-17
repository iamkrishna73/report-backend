package io.iamkrishna73.ereport.utils;


import io.iamkrishna73.ereport.entity.CitizenPlan;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtil {
    public static String HEADER[] = {"id", "citizen name", "plan name", "plan status", "plan start date", "plan end date", "benefits amount"};
    public static String SHEET_NAME = "PlanSheet";
    public static ByteArrayInputStream dataToExcel(List<CitizenPlan> citizenPlanList) throws IOException {

        Workbook workbook  = new XSSFWorkbook();

        ByteArrayOutputStream byteArrayOutputStream  = new ByteArrayOutputStream();
        try {
            Sheet sheet = workbook.createSheet(SHEET_NAME);
            Row row = sheet.createRow(0);

            for (int i  =0; i< HEADER.length;i++){

                Cell cell = row.createCell(i);
                cell.setCellValue(HEADER[i]);
            }

            int dataRowIndex = 1;
            for (CitizenPlan citizenPlan :citizenPlanList){
                Row dataRow = sheet.createRow(dataRowIndex);
                dataRow.createCell(0).setCellValue(citizenPlan.getCitizenId());
                dataRow.createCell(1).setCellValue(citizenPlan.getCitizenName());
                dataRow.createCell(2).setCellValue(citizenPlan.getPlanName());
                dataRow.createCell(3).setCellValue(citizenPlan.getPlanStatus());
                if (null != citizenPlan.getPlanStartDate()) {
                    dataRow.createCell(4).setCellValue(citizenPlan.getPlanStartDate());
                } else {
                    dataRow.createCell(4).setCellValue("N/A");

                }
                if (null != citizenPlan.getPlanEndDate()) {
                    dataRow.createCell(5).setCellValue(citizenPlan.getPlanEndDate());
                } else {
                    dataRow.createCell(5).setCellValue("N/A");
                }
                if (null != citizenPlan.getBenefitsAmount()) {
                    dataRow.createCell(6).setCellValue(citizenPlan.getBenefitsAmount());
                } else {
                    dataRow.createCell(6).setCellValue("N/A");

                }
                dataRowIndex++;
            }

            workbook.write(byteArrayOutputStream);
            return  new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            workbook.close();
            byteArrayOutputStream.close();
        }
    }
}