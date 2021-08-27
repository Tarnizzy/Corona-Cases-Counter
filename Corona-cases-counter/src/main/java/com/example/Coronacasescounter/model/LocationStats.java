package com.example.Coronacasescounter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class LocationStats {
    private String country;
    private int latestReportedTotal;
    private int numOfDeaths;
    private int numOfActiveCases;
   private int numOfRecoveredCases;
   // private long case_Fatality_Ratio;

}
