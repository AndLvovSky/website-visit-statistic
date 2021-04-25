package com.andlvovsky.wvs.statistics;

import com.andlvovsky.wvs.data.DateTimeInterval;
import com.andlvovsky.wvs.dto.statistics.TimeVisitsDto;
import com.andlvovsky.wvs.entity.VisitEntity;
import com.andlvovsky.wvs.service.VisitServiceLocal;
import com.andlvovsky.wvs.service.statistics.DateTimeService;
import com.andlvovsky.wvs.service.statistics.TimeStatisticsService;
import com.andlvovsky.wvs.service.statistics.impl.DefaultTimeStatisticsService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TimeStatisticsServiceUnitTest {

  private VisitServiceLocal visitServiceLocal = Mockito.mock(VisitServiceLocal.class);
  private DateTimeService dateTimeService =  Mockito.mock(DateTimeService.class);
  private TimeStatisticsService timeStatisticsService = new DefaultTimeStatisticsService(visitServiceLocal, dateTimeService);

  @Test
  public void shouldGetVisitsPerDayOfWeek() {
    // given
    List<VisitEntity> visitEntities = Arrays.asList(
        VisitEntity.builder().time(LocalDate.of(2020, 5, 14).atStartOfDay()).build(),
        VisitEntity.builder().time(LocalDate.of(2020, 5, 12).atStartOfDay()).build(),
        VisitEntity.builder().time(LocalDate.of(2020, 5, 11).atStartOfDay()).build(),
        VisitEntity.builder().time(LocalDate.of(2020, 5, 11).atStartOfDay()).build(),
        VisitEntity.builder().time(LocalDate.of(2020, 5, 8).atStartOfDay()).build()
    );
    when(visitServiceLocal.getVisits(any(), any()))
        .thenReturn(visitEntities);
    when(dateTimeService.getLastWeekInterval()).thenReturn(new DateTimeInterval(
        LocalDateTime.of(2020, 5, 8, 0, 0, 0),
        LocalDateTime.of(2020, 5, 15, 0, 0, 0) // Friday
    ));

    // when
    List<TimeVisitsDto> timeVisitsDtos = timeStatisticsService.getVisitsPerDayOfWeek(1L);

    // then
    assertThat(timeVisitsDtos).isEqualTo(Arrays.asList(
       new TimeVisitsDto("FRIDAY", 1),
       new TimeVisitsDto("SATURDAY", 0),
       new TimeVisitsDto("SUNDAY", 0),
       new TimeVisitsDto("MONDAY", 2),
       new TimeVisitsDto("TUESDAY", 1),
       new TimeVisitsDto("WEDNESDAY", 0),
       new TimeVisitsDto("THURSDAY", 1)
    ));
  }

  @Test
  public void shouldGetVisitsPerDayOfMonth() {
    // given
    List<VisitEntity> visitEntities = Arrays.asList(
        VisitEntity.builder().time(LocalDate.of(2020, 5, 14).atStartOfDay()).build(),
        VisitEntity.builder().time(LocalDate.of(2020, 5, 12).atStartOfDay()).build(),
        VisitEntity.builder().time(LocalDate.of(2020, 5, 11).atStartOfDay()).build(),
        VisitEntity.builder().time(LocalDate.of(2020, 5, 11).atStartOfDay()).build(),
        VisitEntity.builder().time(LocalDate.of(2020, 5, 4).atStartOfDay()).build(),
        VisitEntity.builder().time(LocalDate.of(2020, 4, 15).atStartOfDay()).build()
    );
    when(visitServiceLocal.getVisits(any(), any()))
        .thenReturn(visitEntities);
    when(dateTimeService.getLastMonthInterval()).thenReturn(new DateTimeInterval(
        LocalDateTime.of(2020, 4, 15, 0, 0, 0),
        LocalDateTime.of(2020, 5, 15, 0, 0, 0)
    ));

    // when
    List<TimeVisitsDto> timeVisitsDtos = timeStatisticsService.getVisitsForLastMonth(1L);

    // then
    assertThat(timeVisitsDtos.size()).isEqualTo(30);
    int totalVisits = timeVisitsDtos.stream()
        .mapToInt(TimeVisitsDto::getVisits)
        .sum();
    assertThat(totalVisits).isEqualTo(6);
    assertThat(timeVisitsDtos).contains(
        new TimeVisitsDto("15", 1),
        new TimeVisitsDto("4", 1),
        new TimeVisitsDto("11", 2),
        new TimeVisitsDto("12", 1),
        new TimeVisitsDto("14", 1)
    );
    assertThat(timeVisitsDtos.get(0).getTime()).isEqualTo("15");
    assertThat(timeVisitsDtos.get(29).getTime()).isEqualTo("14");
  }

  @Test
  public void shouldGetVisitsPerHour() {
    // given
    List<VisitEntity> visitEntities = Arrays.asList(
        VisitEntity.builder().time(LocalDateTime.of(2020, 5, 14, 6, 0, 0)).build(),
        VisitEntity.builder().time(LocalDateTime.of(2020, 5, 14, 9, 0, 0)).build(),
        VisitEntity.builder().time(LocalDateTime.of(2020, 5, 14, 9, 0, 0)).build(),
        VisitEntity.builder().time(LocalDateTime.of(2020, 5, 14, 15, 0, 0)).build(),
        VisitEntity.builder().time(LocalDateTime.of(2020, 5, 14, 20, 0, 0)).build()
    );
    when(visitServiceLocal.getVisits(any()))
        .thenReturn(visitEntities);

    // when
    List<TimeVisitsDto> timeVisitsDtos = timeStatisticsService.getVisitsPerHour(1L);

    // then
    assertThat(timeVisitsDtos.size()).isEqualTo(24);
    int totalVisits = timeVisitsDtos.stream()
        .mapToInt(TimeVisitsDto::getVisits)
        .sum();
    assertThat(totalVisits).isEqualTo(5);
    assertThat(timeVisitsDtos).contains(
        new TimeVisitsDto("06 am", 1),
        new TimeVisitsDto("09 am", 2),
        new TimeVisitsDto("03 pm", 1),
        new TimeVisitsDto("08 pm", 1)
    );
    assertThat(timeVisitsDtos.get(0).getTime()).isEqualTo("12 am");
    assertThat(timeVisitsDtos.get(23).getTime()).isEqualTo("11 pm");
  }
}
