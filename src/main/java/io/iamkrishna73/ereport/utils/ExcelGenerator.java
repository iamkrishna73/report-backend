package io.iamkrishna73.ereport.utils;

import io.iamkrishna73.ereport.entity.CitizenPlan;
import io.iamkrishna73.ereport.repos.CitizenPlanRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ExcelGenerator {
    private final CitizenPlanRepository citizenPlanRepository;

    public ExcelGenerator(CitizenPlanRepository citizenPlanRepository) {
        this.citizenPlanRepository = citizenPlanRepository;
    }

    public boolean exportToExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");

        String currentDateTime = dateFormatter.format(new Date());
        String fileName = "PlanData";

        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName + "_" + currentDateTime + ".xls");
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("plans-data");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Citizen Name");
        headerRow.createCell(2).setCellValue("Plan Name");
        headerRow.createCell(3).setCellValue("Plan Status");
        headerRow.createCell(4).setCellValue("Plan Start Date");
        headerRow.createCell(5).setCellValue("Plan End Date");
        headerRow.createCell(6).setCellValue("Benefits Amount");
        List<CitizenPlan> records = citizenPlanRepository.findAll();
        int dataRowIndex = 1;
        for (CitizenPlan record : records) {
            Row dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(record.getCitizenId());
            dataRow.createCell(1).setCellValue(record.getCitizenName());
            dataRow.createCell(2).setCellValue(record.getPlanName());
            dataRow.createCell(3).setCellValue(record.getPlanStatus());
            if (null != record.getPlanStartDate()) {
                dataRow.createCell(4).setCellValue(record.getPlanStartDate());
            } else {
                dataRow.createCell(6).setCellValue("N/A");

            }
            if (null != record.getPlanEndDate()) {
                dataRow.createCell(5).setCellValue(record.getPlanEndDate());
            } else {
                dataRow.createCell(6).setCellValue("N/A");
            }
            if (null != record.getBenefitsAmount()) {
                dataRow.createCell(6).setCellValue(record.getBenefitsAmount());
            } else {
                dataRow.createCell(6).setCellValue("N/A");

            }
            dataRowIndex++;
        }
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return true;
    }
}
