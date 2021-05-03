package com.andlvovsky.wvs.service.statistics;

import com.andlvovsky.wvs.dto.statistics.AbVisitsDto;

import java.util.List;

public interface AbStatisticsService {
  List<AbVisitsDto> getVisitsPerWebsiteVersionForTheLastWeek(Long siteId);

  List<AbVisitsDto> getVisitsPerWebsiteVersionForTheLastMonth(Long siteId);

  List<AbVisitsDto> getVisitsPerWebsiteVersion(Long siteId, String fromDate, String toDate);
}
