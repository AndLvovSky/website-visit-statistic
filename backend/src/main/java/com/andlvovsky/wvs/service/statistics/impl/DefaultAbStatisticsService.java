package com.andlvovsky.wvs.service.statistics.impl;

import com.andlvovsky.wvs.data.DateTimeInterval;
import com.andlvovsky.wvs.dto.statistics.AbVisitsDto;
import com.andlvovsky.wvs.entity.VisitEntity;
import com.andlvovsky.wvs.service.VisitServiceLocal;
import com.andlvovsky.wvs.service.statistics.AbStatisticsService;
import com.andlvovsky.wvs.service.statistics.DateTimeService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import lombok.RequiredArgsConstructor;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultAbStatisticsService implements AbStatisticsService {

  private final VisitServiceLocal visitServiceLocal;
  private final DateTimeService dateTimeService;

  @Override
  public List<AbVisitsDto> getVisitsPerWebsiteVersionForTheLastWeek(Long siteId) {
    DateTimeInterval lastWeekInterval = dateTimeService.getLastWeekInterval();
    List<VisitEntity> visitsForTheLastWeek = visitServiceLocal.getVisits(siteId, lastWeekInterval);
    return getVisitsPerWebsiteVersion(visitsForTheLastWeek);
  }

  @Override
  public List<AbVisitsDto> getVisitsPerWebsiteVersionForTheLastMonth(Long siteId) {
    DateTimeInterval lastMonthInterval = dateTimeService.getLastMonthInterval();
    List<VisitEntity> visitsForTheLastMonth = visitServiceLocal.getVisits(siteId, lastMonthInterval);
    return getVisitsPerWebsiteVersion(visitsForTheLastMonth);
  }

  private List<AbVisitsDto> getVisitsPerWebsiteVersion(List<VisitEntity> visits) {
    return visits.stream()
        .filter(visit -> visit.getWebsiteVersion() != null)
        .collect(groupingBy(VisitEntity::getWebsiteVersion, counting()))
        .entrySet().stream()
        .map(item -> new AbVisitsDto(item.getKey(), item.getValue().intValue()))
        .sorted(comparing(AbVisitsDto::getVersion))
        .collect(toList());
  }
}
