package io.iamkrishna73.ereport.service;


import io.iamkrishna73.ereport.entity.CitizenPlan;
import io.iamkrishna73.ereport.request.SearchRequest;

import java.util.List;
import java.util.Map;

public interface IReportService {
    List<String> getPlanNames();
    List<String> getPlanStatus();
    List<CitizenPlan> searchPlan(SearchRequest searchRequest);
    boolean exportPdf();
    boolean exportExcel();

    List<CitizenPlan> sendData(String planName, String planStatus, String gender, String planStartDate, String planEndDate);
    //  List<CitizenPlan> sendData();
}
