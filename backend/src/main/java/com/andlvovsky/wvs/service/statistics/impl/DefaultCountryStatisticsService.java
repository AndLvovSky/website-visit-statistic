package com.andlvovsky.wvs.service.statistics.impl;

import com.andlvovsky.wvs.data.DateTimeInterval;
import com.andlvovsky.wvs.dto.statistics.CountryVisitsDto;
import com.andlvovsky.wvs.entity.VisitEntity;
import com.andlvovsky.wvs.service.VisitServiceLocal;
import com.andlvovsky.wvs.service.statistics.CountryStatisticsService;
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
public class DefaultCountryStatisticsService implements CountryStatisticsService {

  private final VisitServiceLocal visitServiceLocal;
  private final DateTimeService dateTimeService;

  @Override
  public List<CountryVisitsDto> getVisitsPerCountryForTheLastWeek(Long siteId) {
    DateTimeInterval lastWeekInterval = dateTimeService.getLastWeekInterval();
    List<VisitEntity> visitsForTheLastWeek = visitServiceLocal.getVisits(siteId, lastWeekInterval);
    return getCountryVisits(visitsForTheLastWeek);
  }

  @Override
  public List<CountryVisitsDto> getVisitsPerCountryForTheLastMonth(Long siteId) {
    DateTimeInterval lastMonthInterval = dateTimeService.getLastMonthInterval();
    List<VisitEntity> visitsForTheLastMonth = visitServiceLocal.getVisits(siteId, lastMonthInterval);
    return getCountryVisits(visitsForTheLastMonth);
  }

  private List<CountryVisitsDto> getCountryVisits(List<VisitEntity> visits) {
    return visits.stream()
        .collect(groupingBy(VisitEntity::getCountry, counting()))
        .entrySet().stream()
        .map(item -> new CountryVisitsDto(item.getKey(), item.getValue().intValue()))
        .sorted(comparing(CountryVisitsDto::getCountry))
        .collect(toList());
  }
}
