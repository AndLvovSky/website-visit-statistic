package com.andlvovsky.wvs.service.statistics.impl;

import com.andlvovsky.wvs.data.DayOfWeekVisits;
import com.andlvovsky.wvs.dto.statistics.TimeVisitsDto;
import com.andlvovsky.wvs.entity.VisitEntity;
import com.andlvovsky.wvs.repository.VisitRepository;
import com.andlvovsky.wvs.service.statistics.TimeStatisticsService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
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

  private final VisitRepository visitRepository;
  private final Clock clock;

  @Override
  public List<TimeVisitsDto> getVisitsPerDayOfWeek(Long siteId) {
    LocalDateTime todayStart = LocalDate.now(clock).atStartOfDay();
    LocalDateTime timeWeekAgo = todayStart.minusWeeks(1);
    List<VisitEntity> visitsForTheLastWeek = visitRepository.findBySiteIdAndTimeBetween(siteId, timeWeekAgo, todayStart);
    Map<DayOfWeek, Long> dayOfWeekToVisits = visitsForTheLastWeek.stream()
        .map(VisitEntity::getTime)
        .collect(groupingBy(LocalDateTime::getDayOfWeek, counting()));
    DayOfWeek yesterdayDayOfWeek = todayStart.minusDays(1).getDayOfWeek();
    List<DayOfWeekVisits> timeVisits = getOrderedVisitsPerDayOfWeek(dayOfWeekToVisits, yesterdayDayOfWeek);
    return timeVisits.stream()
        .map(item -> new TimeVisitsDto(item.getDayOfWeek().toString(), item.getVisits()))
        .collect(toList());
  }

  private List<DayOfWeekVisits> getOrderedVisitsPerDayOfWeek(Map<DayOfWeek, Long> dayOfWeekToVisits, DayOfWeek lastDayOfWeek) {
    Stream.of(DayOfWeek.values())
        .forEach(day -> dayOfWeekToVisits.putIfAbsent(day, 0L));
    List<DayOfWeekVisits> timeVisits = dayOfWeekToVisits.entrySet().stream()
        .map(item -> new DayOfWeekVisits(item.getKey(), item.getValue().intValue()))
        .sorted(Comparator.comparing(item -> item.getDayOfWeek().ordinal()))
        .collect(toList());
    Collections.rotate(timeVisits, DayOfWeek.values().length - lastDayOfWeek.getValue());
    return timeVisits;
  }
}
