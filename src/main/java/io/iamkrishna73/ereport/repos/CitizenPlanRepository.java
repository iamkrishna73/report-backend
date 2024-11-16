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
    @Query(value = "SELECT c FROM CitizenPlan c " +
            "WHERE (:planName IS NULL OR :planName = '' OR c.planName = :planName) " +
            "AND (:planStatus IS NULL OR :planStatus = '' OR c.planStatus = :planStatus) " +
            "AND (:gender IS NULL OR :gender = '' OR c.gender = :gender) " +
            "AND (:planStartDate IS NULL OR :planStartDate = '' OR c.planStartDate >= :planStartDate) " +
            "AND (:planEndDate IS NULL OR :planEndDate = '' OR c.planEndDate <= :planEndDate)")
    List<CitizenPlan> findCitizenWithSpecificFields(
            @Param("planName") String planName,
            @Param("planStatus") String planStatus,
            @Param("gender") String gender,
            @Param("planStartDate") String planStartDate,
            @Param("planEndDate") String planEndDate);

}
