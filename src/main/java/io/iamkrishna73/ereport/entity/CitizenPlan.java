package io.iamkrishna73.ereport.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "citizen_plan_info")
public class CitizenPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer citizenId;
    private String citizenName;
    private String gender;
    private String planName;
    private String planStatus;
    private String planStartDate;
    private String planEndDate;
    private Double benefitsAmount;
    private String denialReason;
    private String terminateDate;
    private String terminatedReason;
}
