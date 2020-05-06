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
}
