package com.andlvovsky.wvs.service.statistics.impl;

import com.andlvovsky.wvs.data.DateTimeInterval;
import com.andlvovsky.wvs.dto.statistics.TimeVisitsDto;
import com.andlvovsky.wvs.entity.VisitEntity;
import com.andlvovsky.wvs.service.VisitServiceLocal;
import com.andlvovsky.wvs.service.statistics.DateTimeService;
import com.andlvovsky.wvs.service.statistics.TimeStatisticsService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultTimeStatisticsService implements TimeStatisticsService {

  private final VisitServiceLocal visitServiceLocal;
  private final DateTimeService dateTimeService;

  @Override
  public List<TimeVisitsDto> getVisitsPerDayOfWeek(Long siteId, boolean unique) {
    DateTimeInterval lastWeekInterval = dateTimeService.getLastWeekInterval();
    List<VisitEntity> visitsForTheLastWeek = visitServiceLocal.getVisits(siteId, lastWeekInterval);
    Map<LocalDate, Long> visitsPerDate = visitsForTheLastWeek.stream()
        .collect(groupingBy(visit -> visit.getTime().toLocalDate(), toList()))
        .entrySet()
        .stream()
        .collect(toMap(Map.Entry::getKey, entry -> getVisits(entry.getValue(), unique)));
    LocalDate dateWeekAgo = lastWeekInterval.getStart().toLocalDate();
    return Stream.iterate(dateWeekAgo, date -> date.plusDays(1))
        .limit(DayOfWeek.values().length)
        .map(date -> createTimeVisitsForWeek(visitsPerDate, date))
        .collect(toList());
  }

  private long getVisits(List<VisitEntity> visits, boolean unique) {
    List<String> ips = visits.stream()
        .map(VisitEntity::getIp)
        .collect(toList());
    return unique ? new HashSet<>(ips).size() : ips.size();
  }

  @Override
  public List<TimeVisitsDto> getVisitsForLastMonth(Long siteId, boolean unique) {
    DateTimeInterval lastMonthInterval = dateTimeService.getLastMonthInterval();
    List<VisitEntity> visitsForTheLastMonth = visitServiceLocal.getVisits(siteId, lastMonthInterval);
    Map<LocalDate, Long> visitsPerDate = visitsForTheLastMonth.stream()
        .collect(groupingBy(visit -> visit.getTime().toLocalDate(), toList()))
        .entrySet()
        .stream()
        .collect(toMap(Map.Entry::getKey, entry -> getVisits(entry.getValue(), unique)));
    LocalDate dateMonthAgo = lastMonthInterval.getStart().toLocalDate();
    LocalDate dateToday = lastMonthInterval.getEnd().toLocalDate();
    List<TimeVisitsDto> timeVisits = new ArrayList<>();
    for (LocalDate date = dateMonthAgo; date.isBefore(dateToday); date = date.plusDays(1)) {
      timeVisits.add(createTimeVisitsForMonth(visitsPerDate, date));
    }
    return timeVisits;
  }

  @Override
  public List<TimeVisitsDto> getVisitsPerHour(Long siteId, boolean unique) {
    List<VisitEntity> visits = visitServiceLocal.getVisits(siteId);
    Map<Integer, Long> hourVisits = visits.stream()
        .collect(groupingBy(visit -> visit.getTime().getHour(), toList()))
        .entrySet()
        .stream()
        .collect(toMap(Map.Entry::getKey, entry -> getVisits(entry.getValue(), unique)));
    IntStream.range(0, 24)
        .forEach(hour -> hourVisits.putIfAbsent(hour, 0L));
    return hourVisits.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .map(this::createTimeVisitsPerHour)
        .collect(toList());
  }

  private TimeVisitsDto createTimeVisitsPerHour(Map.Entry<Integer, Long> hourVisits) {
    SimpleDateFormat inputFormat = new SimpleDateFormat("HH");
    SimpleDateFormat outputFormat = new SimpleDateFormat("hh a");
    String hour = String.format("%02d", hourVisits.getKey());
    Date inputDate;
    try {
      inputDate = inputFormat.parse(hour);
    } catch (ParseException e) {
      throw new IllegalArgumentException(e.getMessage(), e);
    }
    String formattedHour = outputFormat.format(inputDate).toUpperCase();
    return new TimeVisitsDto(formattedHour, hourVisits.getValue().intValue());
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
