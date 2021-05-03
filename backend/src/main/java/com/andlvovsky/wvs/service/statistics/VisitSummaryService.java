package com.andlvovsky.wvs.service.statistics;

import com.andlvovsky.wvs.dto.statistics.VisitSummaryDto;

public interface VisitSummaryService {
  VisitSummaryDto getVisitSummaryForTheLastWeek(Long siteId);

  VisitSummaryDto getVisitSummaryForTheLastMonth(Long siteId);

  VisitSummaryDto getVisitSummary(Long siteId, String fromDate, String toDate);
}
