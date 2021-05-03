package com.andlvovsky.wvs.service.statistics.impl;

import com.andlvovsky.wvs.data.DateTimeInterval;
import com.andlvovsky.wvs.service.statistics.DateTimeService;
import com.andlvovsky.wvs.util.DateUtils;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DefaultDateTimeService implements DateTimeService {

  private final Clock clock;

  @Override
  public DateTimeInterval getLastWeekInterval() {
    LocalDateTime todayStart = LocalDate.now(clock).atStartOfDay();
    LocalDateTime weekAgoDayStart = todayStart.minusWeeks(1);
    return new DateTimeInterval(weekAgoDayStart, todayStart);
  }

  @Override
  public DateTimeInterval getLastMonthInterval() {
    LocalDateTime todayStart = LocalDate.now(clock).atStartOfDay();
    LocalDateTime monthAgoDayStart = todayStart.minusMonths(1);
    return new DateTimeInterval(monthAgoDayStart, todayStart);
  }

  @Override
  public DateTimeInterval getInterval(String fromDate, String toDate) {
    LocalDateTime fromDateTime = DateUtils.mapToDate(fromDate).atStartOfDay();
    LocalDateTime toDateTime = DateUtils.mapToDate(toDate).atTime(LocalTime.MAX);
    return new DateTimeInterval(fromDateTime, toDateTime);
  }
}
