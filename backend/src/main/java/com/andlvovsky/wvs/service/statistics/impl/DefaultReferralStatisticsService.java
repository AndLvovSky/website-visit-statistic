package com.andlvovsky.wvs.service.statistics.impl;

import com.andlvovsky.wvs.data.DateTimeInterval;
import com.andlvovsky.wvs.dto.statistics.ReferralWebsiteDto;
import com.andlvovsky.wvs.entity.VisitEntity;
import com.andlvovsky.wvs.service.VisitServiceLocal;
import com.andlvovsky.wvs.service.statistics.DateTimeService;
import com.andlvovsky.wvs.service.statistics.ReferralStatisticsService;

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
public class DefaultReferralStatisticsService implements ReferralStatisticsService {

  private static final String DIRECT_OPEN = "direct";

  private final VisitServiceLocal visitServiceLocal;
  private final DateTimeService dateTimeService;

  @Override
  public List<ReferralWebsiteDto> getReferralWebsitesForTheLastWeek(Long siteId) {
    DateTimeInterval lastWeekInterval = dateTimeService.getLastWeekInterval();
    List<VisitEntity> visitsForTheLastWeek = visitServiceLocal.getVisits(siteId, lastWeekInterval);
    return getReferralWebsites(visitsForTheLastWeek);
  }

  @Override
  public List<ReferralWebsiteDto> getReferralWebsitesForTheLastMonth(Long siteId) {
    DateTimeInterval lastMonthInterval = dateTimeService.getLastMonthInterval();
    List<VisitEntity> visitsForTheLastMonth = visitServiceLocal.getVisits(siteId, lastMonthInterval);
    return getReferralWebsites(visitsForTheLastMonth);
  }

  @Override
  public List<ReferralWebsiteDto> getReferralWebsites(Long siteId, String fromDate, String toDate) {
    DateTimeInterval interval = dateTimeService.getInterval(fromDate, toDate);
    List<VisitEntity> visits = visitServiceLocal.getVisits(siteId, interval);
    return getReferralWebsites(visits);
  }

  private List<ReferralWebsiteDto> getReferralWebsites(List<VisitEntity> visits) {
    return visits.stream()
        .map(visit -> visit.getReferralWebsite() == null ? DIRECT_OPEN : visit.getReferralWebsite())
        .collect(groupingBy(Function.identity(), counting()))
        .entrySet().stream()
        .map(item -> new ReferralWebsiteDto(item.getKey(), item.getValue().intValue()))
        .sorted(comparing(ReferralWebsiteDto::getWebsite))
        .collect(toList());
  }
}
