package io.iamkrishna73.ereport.repos;

import io.iamkrishna73.ereport.entity.CitizenPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitizenPlanRepository extends JpaRepository<CitizenPlan, Integer> {

    @Query("select distinct(planName) from CitizenPlan")
    List<String> getPlanName();
    @Query("select distinct(planStatus) from CitizenPlan")
    List<String> getPlanStatus();

    @Query(value = "SELECT * FROM citizen_plan_info c WHERE " +
            "(:planName IS NULL OR c.plan_name = :planName) AND " +
            "(:planStatus IS NULL OR c.plan_status = :planStatus) AND " +
            "(:gender IS NULL OR c.gender = :gender) AND " +
            "(:planStartDate IS NULL OR c.plan_start_date >= :planStartDate) AND " +
            "(:planEndDate IS NULL OR c.plan_end_date <= :planEndDate)", nativeQuery = true)
    List<CitizenPlan> findCitizenWithSpecificFields(
            @Param("planName") String planName,
            @Param("planStatus") String planStatus,
            @Param("gender") String gender,
            @Param("planStartDate") String planStartDate,
            @Param("planEndDate") String planEndDate);
}
