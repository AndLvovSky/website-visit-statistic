package com.andlvovsky.wvs.service.statistics;

import com.andlvovsky.wvs.dto.statistics.RouteVisitsDto;

import java.util.List;

public interface RouteStatisticsService {
  List<RouteVisitsDto> getRouteVisitsForTheLastWeek(Long siteId);

  List<RouteVisitsDto> getRouteVisitsForTheLastMonth(Long siteId);
}
