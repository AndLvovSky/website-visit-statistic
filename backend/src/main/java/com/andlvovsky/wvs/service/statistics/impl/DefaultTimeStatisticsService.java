package com.andlvovsky.wvs.service.statistics.impl;

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
    Map<LocalDate, Long> visitsPerDate = visitsForTheLastWeek.stream()
        .map(VisitEntity::getTime)
        .collect(groupingBy(LocalDateTime::toLocalDate, counting()));
    return Stream.iterate(timeWeekAgo.toLocalDate(), date -> date.plusDays(1))
        .limit(DayOfWeek.values().length)
        .map(date -> createTimeVisits(visitsPerDate, date))
        .collect(toList());
  }

  private TimeVisitsDto createTimeVisits(Map<LocalDate, Long> visitsPerDate, LocalDate date) {
    int visitCount = visitsPerDate.getOrDefault(date, 0L).intValue();
    return new TimeVisitsDto(date.getDayOfWeek().name(), visitCount);
  }
}
