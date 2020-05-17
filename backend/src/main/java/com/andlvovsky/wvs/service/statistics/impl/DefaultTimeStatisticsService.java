package com.andlvovsky.wvs.service.statistics.impl;

import com.andlvovsky.wvs.data.DateTimeInterval;
import com.andlvovsky.wvs.dto.statistics.TimeVisitsDto;
import com.andlvovsky.wvs.entity.VisitEntity;
import com.andlvovsky.wvs.service.VisitServiceLocal;
import com.andlvovsky.wvs.service.statistics.DateTimeService;
import com.andlvovsky.wvs.service.statistics.TimeStatisticsService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultTimeStatisticsService implements TimeStatisticsService {

  private final VisitServiceLocal visitServiceLocal;
  private final DateTimeService dateTimeService;

  @Override
  public List<TimeVisitsDto> getVisitsPerDayOfWeek(Long siteId) {
    DateTimeInterval lastWeekInterval = dateTimeService.getLastWeekInterval();
    List<VisitEntity> visitsForTheLastWeek = visitServiceLocal.getVisits(siteId, lastWeekInterval);
    Map<LocalDate, Long> visitsPerDate = visitsForTheLastWeek.stream()
        .map(VisitEntity::getTime)
        .collect(groupingBy(LocalDateTime::toLocalDate, counting()));
    LocalDate dateWeekAgo = lastWeekInterval.getStart().toLocalDate();
    return Stream.iterate(dateWeekAgo, date -> date.plusDays(1))
        .limit(DayOfWeek.values().length)
        .map(date -> createTimeVisitsForWeek(visitsPerDate, date))
        .collect(toList());
  }

  @Override
  public List<TimeVisitsDto> getVisitsForLastMonth(Long siteId) {
    DateTimeInterval lastMonthInterval = dateTimeService.getLastMonthInterval();
    List<VisitEntity> visitsForTheLastMonth = visitServiceLocal.getVisits(siteId, lastMonthInterval);
    Map<LocalDate, Long> visitsPerDate = visitsForTheLastMonth.stream()
        .map(VisitEntity::getTime)
        .collect(groupingBy(LocalDateTime::toLocalDate, counting()));
    LocalDate dateMonthAgo = lastMonthInterval.getStart().toLocalDate();
    LocalDate dateToday = lastMonthInterval.getEnd().toLocalDate();
    List<TimeVisitsDto> timeVisits = new ArrayList<>();
    for (LocalDate date = dateMonthAgo; date.isBefore(dateToday); date = date.plusDays(1)) {
      timeVisits.add(createTimeVisitsForMonth(visitsPerDate, date));
    }
    return timeVisits;
  }

  private TimeVisitsDto createTimeVisitsForWeek(Map<LocalDate, Long> visitsPerDate, LocalDate date) {
    int visitCount = visitsPerDate.getOrDefault(date, 0L).intValue();
    return new TimeVisitsDto(date.getDayOfWeek().name(), visitCount);
  }

  private TimeVisitsDto createTimeVisitsForMonth(Map<LocalDate, Long> visitsPerDate, LocalDate date) {
    int visitCount = visitsPerDate.getOrDefault(date, 0L).intValue();
    return new TimeVisitsDto(String.valueOf(date.getDayOfMonth()), visitCount);
  }
}
