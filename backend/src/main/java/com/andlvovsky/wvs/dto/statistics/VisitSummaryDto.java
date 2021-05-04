package com.andlvovsky.wvs.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitSummaryDto {
  private float averageVisits;
  private float averageUniqueVisits;
  private int minVisits;
  private int minUniqueVisits;
  private int maxVisits;
  private int maxUniqueVisits;
  private float visitsStandardDeviation;
  private float uniqueVisitsStandardDeviation;
  private int activeUsers;
}
