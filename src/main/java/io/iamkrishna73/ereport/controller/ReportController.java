package io.iamkrishna73.ereport.controller;

import io.iamkrishna73.ereport.entity.CitizenPlan;
import io.iamkrishna73.ereport.service.IReportService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
    @GetMapping("/download/excel")
    private ResponseEntity<InputStreamResource> downloadToExcel() throws IOException {
        String fileName ="plans.xlsx";
        ByteArrayInputStream inputStream = reportService.downloadToExcel();
        InputStreamResource    response = new InputStreamResource(inputStream);

        ResponseEntity<InputStreamResource> responseEntity = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(response);
        return responseEntity;
    }

    @GetMapping(value = "/download/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> downloadToPdf() throws IOException {
        ByteArrayInputStream bis = reportService.downloadToPdf();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Plans.pdf"); // Change to 'attachment' for download

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
