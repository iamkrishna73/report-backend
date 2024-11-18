package io.iamkrishna73.ereport.service;


import io.iamkrishna73.ereport.entity.CitizenPlan;

import java.io.IOException;
import java.util.List;

public interface IReportService {
    List<String> getPlanNames();

    List<String> getPlanStatus();

    List<CitizenPlan> searchPlan(String planName, String planStatus, String gender, String planStartDate, String planEndDate);


    void sendExcelReportByEmail() throws IOException;

    void sendPdfReportByEmail() throws IOException;
}
