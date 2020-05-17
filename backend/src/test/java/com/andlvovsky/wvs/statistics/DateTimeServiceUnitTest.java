package com.andlvovsky.wvs.statistics;

import com.andlvovsky.wvs.data.DateTimeInterval;
import com.andlvovsky.wvs.service.statistics.DateTimeService;
import com.andlvovsky.wvs.service.statistics.impl.DefaultDateTimeService;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeServiceUnitTest {

  private Clock clock = Clock.fixed(LocalDateTime.of(2020, 5, 15, 9, 0, 0)
      .atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
  private DateTimeService dateTimeService = new DefaultDateTimeService(clock);

  @Test
  public void shouldReturnLastWeekInterval() {
    // when
    DateTimeInterval dateTimeInterval = dateTimeService.getLastWeekInterval();

    // then
    assertThat(dateTimeInterval.getStart()).isEqualTo(LocalDateTime.of(2020, 5, 8, 0, 0, 0));
    assertThat(dateTimeInterval.getEnd()).isEqualTo(LocalDateTime.of(2020, 5, 15, 0, 0, 0));
  }

  @Test
  public void shouldReturnLastMonthInterval() {
    // when
    DateTimeInterval dateTimeInterval = dateTimeService.getLastMonthInterval();

    // then
    assertThat(dateTimeInterval.getStart()).isEqualTo(LocalDateTime.of(2020, 4, 15, 0, 0, 0));
    assertThat(dateTimeInterval.getEnd()).isEqualTo(LocalDateTime.of(2020, 5, 15, 0, 0, 0));
  }
}
