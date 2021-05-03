package com.andlvovsky.wvs.service.statistics.impl;

import com.andlvovsky.wvs.data.DateTimeInterval;
import com.andlvovsky.wvs.dto.statistics.VisitSummaryDto;
import com.andlvovsky.wvs.entity.VisitEntity;
import com.andlvovsky.wvs.service.VisitServiceLocal;
import com.andlvovsky.wvs.service.statistics.DateTimeService;
import com.andlvovsky.wvs.service.statistics.VisitSummaryService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultVisitSummaryService implements VisitSummaryService {

  private final VisitServiceLocal visitServiceLocal;
  private final DateTimeService dateTimeService;

  @Override
  public VisitSummaryDto getVisitSummaryForTheLastWeek(Long siteId) {
    DateTimeInterval lastWeekInterval = dateTimeService.getLastWeekInterval();
    List<VisitEntity> visitsForTheLastWeek = visitServiceLocal.getVisits(siteId, lastWeekInterval);
    return getVisitSummary(visitsForTheLastWeek, lastWeekInterval);
  }

  @Override
  public VisitSummaryDto getVisitSummaryForTheLastMonth(Long siteId) {
    DateTimeInterval lastMonthInterval = dateTimeService.getLastMonthInterval();
    List<VisitEntity> visitsForTheLastMonth = visitServiceLocal.getVisits(siteId, lastMonthInterval);
    return getVisitSummary(visitsForTheLastMonth, lastMonthInterval);
  }

  @Override
  public VisitSummaryDto getVisitSummary(Long siteId, String fromDate, String toDate) {
    DateTimeInterval interval = dateTimeService.getInterval(fromDate, toDate);
    List<VisitEntity> visits = visitServiceLocal.getVisits(siteId, interval);
    return getVisitSummary(visits, interval);
  }

  private VisitSummaryDto getVisitSummary(List<VisitEntity> visits, DateTimeInterval interval) {
    if (visits.isEmpty()) {
      return new VisitSummaryDto();
    }

    VisitSummaryDto visitSummary = new VisitSummaryDto();
    Map<LocalDate, List<String>> dateToIp = visits.stream()
        .collect(groupingBy(visit -> visit.getTime().toLocalDate(), mapping(VisitEntity::getIp, toList())));
    List<Integer> visitCount = dateToIp.values().stream()
        .map(List::size)
        .collect(toList());
    List<Integer> uniqueVisitCount = dateToIp.values().stream()
        .map(list -> new HashSet<>(list).size())
        .collect(toList());
    int numberOfDays = getNumberOfDays(interval);

    int totalVisits = visitCount.stream()
        .reduce(0, Integer::sum, Integer::sum);
    float averageVisits = round((float) totalVisits / numberOfDays);
    visitSummary.setAverageVisits(averageVisits);

    int totalUniqueVisits = uniqueVisitCount.stream()
        .reduce(0, Integer::sum, Integer::sum);
    float averageUniqueVisits = round((float) totalUniqueVisits / numberOfDays);
    visitSummary.setAverageUniqueVisits(averageUniqueVisits);

    int minVisits = visitCount.stream()
        .reduce(Integer.MAX_VALUE, Math::min, Math::min);
    visitSummary.setMinVisits(minVisits);

    int minUniqueVisits = uniqueVisitCount.stream()
        .reduce(Integer.MAX_VALUE, Math::min, Math::min);
    visitSummary.setMinUniqueVisits(minUniqueVisits);

    int maxVisits = visitCount.stream()
        .reduce(0, Math::max, Math::max);
    visitSummary.setMaxVisits(maxVisits);

    int maxUniqueVisits = uniqueVisitCount.stream()
        .reduce(0, Math::max, Math::max);
    visitSummary.setMaxUniqueVisits(maxUniqueVisits);

    float visitsStandardDeviation = round(getStandardDeviation(visitCount, averageVisits));
    visitSummary.setVisitsStandardDeviation(visitsStandardDeviation);

    float uniqueVisitsStandardDeviation = round(getStandardDeviation(uniqueVisitCount, averageVisits));
    visitSummary.setUniqueVisitsStandardDeviation(uniqueVisitsStandardDeviation);

    return visitSummary;
  }

  private float getStandardDeviation(List<Integer> numbers, float average) {
    float sumOfSquareDifference = numbers.stream()
        .reduce(0f, (sum, value) -> sum + (average - value) * (average - value), Float::sum);
    return (float) Math.sqrt((double) sumOfSquareDifference / numbers.size());
  }

  private int getNumberOfDays(DateTimeInterval interval) {
    return (int) DAYS.between(interval.getStart().toLocalDate(), interval.getEnd().toLocalDate());
  }

  private float round(float number) {
    return (float) Math.round(number * 10) / 10;
  }
}
