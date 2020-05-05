package com.andlvovsky.wvs.statistics;

import com.andlvovsky.wvs.dto.statistics.TimeVisitsDto;
import com.andlvovsky.wvs.entity.VisitEntity;
import com.andlvovsky.wvs.repository.VisitRepository;
import com.andlvovsky.wvs.service.statistics.TimeStatisticsService;
import com.andlvovsky.wvs.service.statistics.impl.DefaultTimeStatisticsService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TimeStatisticsServiceUnitTest {

  private VisitRepository visitRepository = Mockito.mock(VisitRepository.class);
  // Friday
  private Clock clock = Clock.fixed(LocalDateTime.of(2020, 5, 15, 9, 0, 0)
    .atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
  private TimeStatisticsService timeStatisticsService = new DefaultTimeStatisticsService(visitRepository, clock);

  @Test
  public void shouldGetVisitsPerDayOfWeek() {
    // given
    List<VisitEntity> visitEntities = Arrays.asList(
        VisitEntity.builder().time(LocalDate.of(2020, 5, 12).atStartOfDay()).build(),
        VisitEntity.builder().time(LocalDate.of(2020, 5, 11).atStartOfDay()).build(),
        VisitEntity.builder().time(LocalDate.of(2020, 5, 11).atStartOfDay()).build()
    );
    when(visitRepository.findBySiteIdAndTimeBetween(any(), any(), any()))
        .thenReturn(visitEntities);

    // when
    List<TimeVisitsDto> timeVisitsDtos = timeStatisticsService.getVisitsPerDayOfWeek(1L);

    // then
    assertThat(timeVisitsDtos).isEqualTo(Arrays.asList(
       new TimeVisitsDto("FRIDAY", 0),
       new TimeVisitsDto("SATURDAY", 0),
       new TimeVisitsDto("SUNDAY", 0),
       new TimeVisitsDto("MONDAY", 2),
       new TimeVisitsDto("TUESDAY", 1),
       new TimeVisitsDto("WEDNESDAY", 0),
       new TimeVisitsDto("THURSDAY", 0)
    ));
    verify(visitRepository).findBySiteIdAndTimeBetween(
        eq(1L),
        eq(LocalDateTime.of(2020, 5, 8, 0, 0, 0)),
        eq(LocalDateTime.of(2020, 5, 15, 0, 0, 0))
    );
  }
}
