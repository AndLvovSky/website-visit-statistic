package com.andlvovsky.wvs.service.statistics;

import com.andlvovsky.wvs.dto.statistics.TimeVisitsDto;

import java.util.List;

public interface TimeStatisticsService {
  List<TimeVisitsDto> getVisitsPerDayOfWeek(Long siteId);

  List<TimeVisitsDto> getVisitsForLastMonth(Long siteId);
}
