package io.iamkrishna73.ereport.controller;

import io.iamkrishna73.ereport.entity.CitizenPlan;
import io.iamkrishna73.ereport.service.IReportService;
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
    public List<CitizenPlan> getReport(@RequestParam(required = false) String planName, @RequestParam(required = false) String planStatus, @RequestParam(required = false) String gender, @RequestParam(required = false) String planStartDate, @RequestParam(required = false) String planEndDate) {

        System.out.println("Received Plan Name: " + planName);
        System.out.println("Received Plan Status: " + planStatus);
        System.out.println("Gender: " + gender);
        System.out.println("Start Date: " + planStartDate);
        System.out.println("End Date: " + planEndDate);

        List<CitizenPlan> data = reportService.sendData(planName, planStatus, gender, planStartDate, planEndDate);
        System.out.println(data);

        return data;
    }
}
