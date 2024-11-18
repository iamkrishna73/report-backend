package io.iamkrishna73.ereport.service;


import io.iamkrishna73.ereport.entity.CitizenPlan;
import io.iamkrishna73.ereport.request.SearchRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IReportService {
    List<String> getPlanNames();
    List<String> getPlanStatus();
    ByteArrayInputStream downloadToExcel() throws IOException;    //  List<CitizenPlan> sendData();

    List<CitizenPlan> searchPlan(String planName, String planStatus, String gender, String planStartDate, String planEndDate);

    ByteArrayInputStream downloadToPdf()throws IOException;
}
