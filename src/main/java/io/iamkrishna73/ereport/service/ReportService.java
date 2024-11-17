package io.iamkrishna73.ereport.service;

import io.iamkrishna73.ereport.entity.CitizenPlan;
import io.iamkrishna73.ereport.repos.CitizenPlanRepository;
import io.iamkrishna73.ereport.request.SearchRequest;
import io.iamkrishna73.ereport.utils.ExcelGenerator;
import io.iamkrishna73.ereport.utils.ExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService implements IReportService {
    private final CitizenPlanRepository citizenPlanRepository;
    private final ExcelGenerator excelGenerator;

    public ReportService(CitizenPlanRepository citizenPlanRepository, ExcelGenerator excelGenerator) {
        this.citizenPlanRepository = citizenPlanRepository;
        this.excelGenerator = excelGenerator;
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
    public List<CitizenPlan> searchPlan(SearchRequest searchRequest) {

        return citizenPlanRepository.findAll();
    }
    @Override
    public ByteArrayInputStream getDataDownloaded() throws IOException {
        List<CitizenPlan> plans = citizenPlanRepository.findAll();
        ByteArrayInputStream data = ExcelUtil.dataToExcel(plans);
        return data;
    }

    @Override
    public List<CitizenPlan> sendData(String planName, String planStatus, String gender, String planStartDate, String planEndDate) {
        return citizenPlanRepository.findCitizenWithSpecificFields(planName, planStatus, gender, planStartDate,planEndDate);
    }
}
