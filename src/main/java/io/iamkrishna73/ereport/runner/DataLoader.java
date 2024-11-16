package io.iamkrishna73.ereport.runner;

import io.iamkrishna73.ereport.entity.CitizenPlan;
import io.iamkrishna73.ereport.repos.CitizenPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataLoader implements ApplicationRunner {

    private final CitizenPlanRepository citizenPlanRepository;

    @Autowired
    public DataLoader(CitizenPlanRepository citizenPlanRepository) {
        this.citizenPlanRepository = citizenPlanRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        citizenPlanRepository.deleteAll();

        CitizenPlan cp1 = new CitizenPlan();
        cp1.setCitizenName("John");
        cp1.setGender("Male");
        cp1.setPlanName("Cash");
        cp1.setPlanStatus("Approved");
        cp1.setPlanStartDate(LocalDate.now());
        cp1.setPlanEndDate(LocalDate.now().plusMonths(6));
        cp1.setBenefitsAmount(1500.00);

        CitizenPlan cp2 = new CitizenPlan();
        cp2.setCitizenName("Jane");
        cp2.setGender("Female");
        cp2.setPlanName("Food");
        cp2.setPlanStatus("Denied");
        cp2.setDenialReason("Income too high");

        CitizenPlan cp3 = new CitizenPlan();
        cp3.setCitizenName("Robert");
        cp3.setGender("Male");
        cp3.setPlanName("Medical");
        cp3.setPlanStatus("Terminated");
        cp3.setPlanStartDate(LocalDate.now().minusMonths(4));
        cp3.setPlanEndDate(LocalDate.now().plusMonths(6));
        cp3.setBenefitsAmount(2000.00);
        cp3.setTerminateDate(LocalDate.now());
        cp3.setTerminatedReason("Violation of terms");

        CitizenPlan cp4 = new CitizenPlan();
        cp4.setCitizenName("Emily");
        cp4.setGender("Female");
        cp4.setPlanName("Employment");
        cp4.setPlanStatus("Approved");
        cp4.setPlanStartDate(LocalDate.now());
        cp4.setPlanEndDate(LocalDate.now().plusMonths(6));
        cp4.setBenefitsAmount(3000.00);

        CitizenPlan cp5 = new CitizenPlan();
        cp5.setCitizenName("Michael");
        cp5.setGender("Male");
        cp5.setPlanName("Cash");
        cp5.setPlanStatus("Denied");
        cp5.setDenialReason("Not eligible");

        CitizenPlan cp6 = new CitizenPlan();
        cp6.setCitizenName("Sarah");
        cp6.setGender("Female");
        cp6.setPlanName("Medical");
        cp6.setPlanStatus("Approved");
        cp6.setPlanStartDate(LocalDate.now());
        cp6.setPlanEndDate(LocalDate.now().plusMonths(6));
        cp6.setBenefitsAmount(2500.00);

        CitizenPlan cp7 = new CitizenPlan();
        cp7.setCitizenName("David");
        cp7.setGender("Male");
        cp7.setPlanName("Food");
        cp7.setPlanStatus("Terminated");
        cp7.setPlanStartDate(LocalDate.now().minusMonths(4));
        cp7.setPlanEndDate(LocalDate.now().plusMonths(6));
        cp7.setBenefitsAmount(1800.00);
        cp7.setTerminateDate(LocalDate.now());
        cp7.setTerminatedReason("Moved out of area");

        CitizenPlan cp8 = new CitizenPlan();
        cp8.setCitizenName("Laura");
        cp8.setGender("Female");
        cp8.setPlanName("Employment");
        cp8.setPlanStatus("Denied");
        cp8.setDenialReason("Failed background check");

        CitizenPlan cp9 = new CitizenPlan();
        cp9.setCitizenName("Chris");
        cp9.setGender("Male");
        cp9.setPlanName("Medical");
        cp9.setPlanStatus("Approved");
        cp9.setPlanStartDate(LocalDate.now());
        cp9.setPlanEndDate(LocalDate.now().plusMonths(6));
        cp9.setBenefitsAmount(2200.00);

        // Save all instances to the database
        citizenPlanRepository.saveAll(Arrays.asList(cp1, cp2, cp3, cp4, cp5, cp6, cp7, cp8, cp9));
    }
}
