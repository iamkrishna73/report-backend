package io.iamkrishna73.ereport.service;

import io.iamkrishna73.ereport.entity.CitizenPlan;
import io.iamkrishna73.ereport.repos.CitizenPlanRepository;
import io.iamkrishna73.ereport.request.SearchRequest;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportService implements IReportService {
    private final CitizenPlanRepository citizenPlanRepository;

    public ReportService(CitizenPlanRepository citizenPlanRepository) {
        this.citizenPlanRepository = citizenPlanRepository;
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
    public boolean exportPdf() {
        return false;
    }

    @Override
    public boolean exportExcel() {
        return false;
    }

    @Override
    public List<CitizenPlan> sendData(String planName, String planStatus, String gender, String planStartDate, String planEndDate) {
        return citizenPlanRepository.findCitizenWithSpecificFields(planName, planStatus, gender, planStartDate,planEndDate);

    }

}
