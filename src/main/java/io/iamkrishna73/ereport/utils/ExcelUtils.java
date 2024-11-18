package io.iamkrishna73.ereport.utils;


import io.iamkrishna73.ereport.entity.CitizenPlan;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

public class ExcelUtils {

    public static ByteArrayInputStream downloadToExcel(List<CitizenPlan> plans) {
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Sheet sheet = workbook.createSheet("Citizen Plans");

            // Create Header Row
            Row headerRow = sheet.createRow(0);
            String[] columns = {"ID", "Citizen Name", "Plan Name", "Plan Status", "Plan Start Date", "Plan End Date", "Benefits Amount"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Populate Rows with Data
            int rowIndex = 1;
            for (CitizenPlan plan : plans) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(plan.getCitizenId());
                row.createCell(1).setCellValue(plan.getCitizenName());
                row.createCell(2).setCellValue(plan.getPlanName());
                row.createCell(3).setCellValue(plan.getPlanStatus());
                row.createCell(4).setCellValue(plan.getPlanStartDate() != null ? plan.getPlanStartDate().toString() : "N/A");
                row.createCell(5).setCellValue(plan.getPlanEndDate() != null ? plan.getPlanEndDate().toString() : "N/A");
                row.createCell(6).setCellValue(plan.getBenefitsAmount() != null ? plan.getBenefitsAmount().toString() : "N/A");
            }

            // Write to Output Stream
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
