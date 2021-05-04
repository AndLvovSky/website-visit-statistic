package com.andlvovsky.wvs.service.statistics.impl;

import com.andlvovsky.wvs.data.DateTimeInterval;
import com.andlvovsky.wvs.dto.statistics.BrowserVisitsDto;
import com.andlvovsky.wvs.entity.VisitEntity;
import com.andlvovsky.wvs.meta.Browser;
import com.andlvovsky.wvs.service.VisitServiceLocal;
import com.andlvovsky.wvs.service.statistics.BrowserStatisticsService;
import com.andlvovsky.wvs.service.statistics.DateTimeService;

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
public class DefaultBrowserStatisticsService implements BrowserStatisticsService {

  private final VisitServiceLocal visitServiceLocal;
  private final DateTimeService dateTimeService;

  @Override
  public List<BrowserVisitsDto> getVisitsPerBrowserForTheLastWeek(Long siteId) {
    DateTimeInterval lastWeekInterval = dateTimeService.getLastWeekInterval();
    List<VisitEntity> visitsForTheLastWeek = visitServiceLocal.getVisits(siteId, lastWeekInterval);
    return getVisitsPerBrowser(visitsForTheLastWeek);
  }

  @Override
  public List<BrowserVisitsDto> getVisitsPerBrowserForTheLastMonth(Long siteId) {
    DateTimeInterval lastMonthInterval = dateTimeService.getLastMonthInterval();
    List<VisitEntity> visitsForTheLastMonth = visitServiceLocal.getVisits(siteId, lastMonthInterval);
    return getVisitsPerBrowser(visitsForTheLastMonth);
  }

  @Override
  public List<BrowserVisitsDto> getVisitsPerBrowser(Long siteId, String fromDate, String toDate) {
    DateTimeInterval interval = dateTimeService.getInterval(fromDate, toDate);
    List<VisitEntity> visits = visitServiceLocal.getVisits(siteId, interval);
    return getVisitsPerBrowser(visits);
  }

  private List<BrowserVisitsDto> getVisitsPerBrowser(List<VisitEntity> visits) {
    return visits.stream()
        .map(visit -> visit.getBrowser() == null ? Browser.OTHER : visit.getBrowser())
        .collect(groupingBy(Function.identity(), counting()))
        .entrySet().stream()
        .map(item -> new BrowserVisitsDto(item.getKey(), item.getValue().intValue()))
        .sorted(comparing(BrowserVisitsDto::getBrowser))
        .collect(toList());
  }
}
