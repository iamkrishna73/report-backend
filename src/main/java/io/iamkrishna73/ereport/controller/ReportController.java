package io.iamkrishna73.ereport.controller;

import io.iamkrishna73.ereport.entity.CitizenPlan;
import io.iamkrishna73.ereport.service.IReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    private final IReportService reportService;

    public ReportController(IReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/plan-name")
    public List<String> getPlaneName() {
        return reportService.getPlanNames();
    }

    @GetMapping("/plan-status")
    public List<String> getPlanStatus() {
        return reportService.getPlanStatus();
    }

    @GetMapping("/search")
    public ResponseEntity<?> getPlan(@RequestParam(required = false) String planName, @RequestParam(required = false) String planStatus, @RequestParam(required = false) String gender, @RequestParam(required = false) String planStartDate, @RequestParam(required = false) String planEndDate) {

        System.out.println("Received Plan Name: " + planName);
        System.out.println("Received Plan Status: " + planStatus);
        System.out.println("Gender: " + gender);
        System.out.println("Start Date: " + planStartDate);
        System.out.println("End Date: " + planEndDate);

        List<CitizenPlan> citizenPlanList = reportService.searchPlan(planName, planStatus, gender, planStartDate, planEndDate);
        System.out.println(citizenPlanList);
        //return null;
        return new ResponseEntity<>(citizenPlanList, HttpStatus.ACCEPTED);
    }

    @GetMapping("/send-excel")
    public ResponseEntity<String> sendExcelByEmail() {
        try {
            // Call the service to generate and send the Excel file via email
            reportService.sendExcelReportByEmail();
            // Return a success response
            return ResponseEntity.ok("Excel report has been sent via email.");
        } catch (Exception e) {
            e.printStackTrace();
            // Return an error response if something goes wrong
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send the Excel report via email.");
        }
    }
    @GetMapping("/send-pdf")
    public ResponseEntity<String> sendPdfByEmail() {
        try {
            // Call the service to generate and send the PDF via email
            reportService.sendPdfReportByEmail();

            // Return a success response
            return ResponseEntity.ok("PDF report has been sent via email.");
        } catch (Exception e) {
            e.printStackTrace();
            // Return an error response if something goes wrong
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send the PDF report via email.");
        }
    }


}
