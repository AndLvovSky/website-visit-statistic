package com.andlvovsky.wvs.service.statistics;

import com.andlvovsky.wvs.dto.statistics.BrowserVisitsDto;

import java.util.List;

public interface BrowserStatisticsService {
  List<BrowserVisitsDto> getVisitsPerBrowserForTheLastWeek(Long siteId);

  List<BrowserVisitsDto> getVisitsPerBrowserForTheLastMonth(Long siteId);

  List<BrowserVisitsDto> getVisitsPerBrowser(Long siteId, String fromDate, String toDate);
}
