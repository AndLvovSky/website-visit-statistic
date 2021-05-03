package com.andlvovsky.wvs.service.statistics;

import com.andlvovsky.wvs.dto.statistics.VisitSummaryDto;

import java.io.File;

public interface VisitSummaryService {
  VisitSummaryDto getVisitSummaryForTheLastWeek(Long siteId);

  VisitSummaryDto getVisitSummaryForTheLastMonth(Long siteId);

  VisitSummaryDto getVisitSummary(Long siteId, String fromDate, String toDate);

  File exportVisitSummaryForTheLastWeekToExcel(Long siteId);

  File exportVisitSummaryForTheLastMonthToExcel(Long siteId);

  File exportVisitSummaryToExcel(Long siteId, String fromDate, String toDate);
}
