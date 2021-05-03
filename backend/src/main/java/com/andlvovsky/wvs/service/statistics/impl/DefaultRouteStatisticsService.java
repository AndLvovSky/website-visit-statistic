package com.andlvovsky.wvs.service.statistics.impl;

import com.andlvovsky.wvs.data.DateTimeInterval;
import com.andlvovsky.wvs.dto.statistics.RouteVisitsDto;
import com.andlvovsky.wvs.entity.VisitEntity;
import com.andlvovsky.wvs.service.VisitServiceLocal;
import com.andlvovsky.wvs.service.statistics.DateTimeService;
import com.andlvovsky.wvs.service.statistics.RouteStatisticsService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

import lombok.RequiredArgsConstructor;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultRouteStatisticsService implements RouteStatisticsService {
  private static final String DEFAULT_PATH = "/";

  private final VisitServiceLocal visitServiceLocal;
  private final DateTimeService dateTimeService;

  @Override
  public List<RouteVisitsDto> getRouteVisitsForTheLastWeek(Long siteId) {
    DateTimeInterval lastWeekInterval = dateTimeService.getLastWeekInterval();
    List<VisitEntity> visitsForTheLastWeek = visitServiceLocal.getVisits(siteId, lastWeekInterval);
    return getRouteVisits(visitsForTheLastWeek);
  }

  @Override
  public List<RouteVisitsDto> getRouteVisitsForTheLastMonth(Long siteId) {
    DateTimeInterval lastMonthInterval = dateTimeService.getLastMonthInterval();
    List<VisitEntity> visitsForTheLastMonth = visitServiceLocal.getVisits(siteId, lastMonthInterval);
    return getRouteVisits(visitsForTheLastMonth);
  }

  private List<RouteVisitsDto> getRouteVisits(List<VisitEntity> visits) {
    return visits.stream()
        .map(visit -> visit.getPath() == null ? DEFAULT_PATH : visit.getPath())
        .collect(groupingBy(Function.identity(), counting()))
        .entrySet().stream()
        .map(item -> new RouteVisitsDto(item.getKey(), item.getValue().intValue()))
        .sorted(comparing(RouteVisitsDto::getPath))
        .collect(toList());
  }
}
