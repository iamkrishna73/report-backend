package io.iamkrishna73.ereport.service;

import io.iamkrishna73.ereport.entity.CitizenPlan;
import io.iamkrishna73.ereport.repos.CitizenPlanRepository;
import io.iamkrishna73.ereport.utils.EmailUtils;
import io.iamkrishna73.ereport.utils.ExcelUtils;
import io.iamkrishna73.ereport.utils.PdfUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService implements IReportService {
    private final CitizenPlanRepository citizenPlanRepository;
    private final EmailUtils emailUtil;

    public ReportService(CitizenPlanRepository citizenPlanRepository, EmailUtils emailUtil) {
        this.citizenPlanRepository = citizenPlanRepository;
        this.emailUtil = emailUtil;
    }

    @Override
    public List<String> getPlanNames() {
        return citizenPlanRepository.getPlanName();
    }
    @Override
    public List<String> getPlanStatus() {
        return citizenPlanRepository.getPlanStatus();
    }

    @Override
    public List<CitizenPlan> searchPlan(String planName, String planStatus, String gender, String planStartDate, String planEndDate) {
        return citizenPlanRepository.findCitizenWithSpecificFields(planName, planStatus, gender, planStartDate,planEndDate);
    }
    @Override
    public void sendExcelReportByEmail() throws IOException {
        List<CitizenPlan> plans = citizenPlanRepository.findAll();

        // Generate the Excel file as a ByteArrayInputStream
        ByteArrayInputStream data = ExcelUtils.downloadToExcel(plans);

        // Prepare email details
        String subject = "Test mail subject";
        String body = "<h1>Please find the attached Excel report.</h1>";
        String to = "krishnasingj137333@gmail.com";

        // Send the email with the ByteArrayInputStream as an attachment
        emailUtil.sendEmail(subject, body, to, "plans.xlsx", data);
    }

//    @Override
//    public ByteArrayInputStream downloadToPdf() throws IOException {
//        File file = new File("Plan.pdf");
//
//        List<CitizenPlan> plans = citizenPlanRepository.findAll();
//        ByteArrayInputStream data = PdfUtils.downloadToPdf(plans, file);
//        String subject = "Test mail subject";
//        String body = "<h1>Please find the Report data attached pdf file.</h1>";
//        String to = "krishnasingj137333@gmail.com";
//
//        emailUtil.sendEmail(subject, body, to, file);
//        file.delete();
//        return data;
//    }
    @Override
    public void sendPdfReportByEmail() throws IOException {
        List<CitizenPlan> plans = citizenPlanRepository.findAll();

        // Generate the PDF as a ByteArrayInputStream
        ByteArrayInputStream data = PdfUtils.downloadToPdf(plans);

        // Prepare email details
        String subject = "Test mail subject";
        String body = "<h1>Please find the attached PDF report.</h1>";
        String to = "krishnasingj137333@gmail.com";

        // Send the email with the ByteArrayInputStream as an attachment
        emailUtil.sendEmail(subject, body, to, "Plans.pdf", data);
    }

}
