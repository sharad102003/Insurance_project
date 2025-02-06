package com.example.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Policy {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String policyID;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "premium_amount", nullable = false)
    private int premiumAmount;

    @Column(name = "coverage_details", length = 255)
    private String coverageDetails;

    @Column(name = "validity_period", nullable = false)
    private LocalDate validityPeriod;
}
