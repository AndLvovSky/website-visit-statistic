package com.andlvovsky.wvs.service.statistics.impl;

import com.andlvovsky.wvs.data.DateTimeInterval;
import com.andlvovsky.wvs.service.statistics.DateTimeService;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
