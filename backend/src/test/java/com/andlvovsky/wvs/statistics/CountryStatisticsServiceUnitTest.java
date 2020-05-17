package com.andlvovsky.wvs.statistics;

import com.andlvovsky.wvs.data.DateTimeInterval;
import com.andlvovsky.wvs.dto.statistics.CountryVisitsDto;
import com.andlvovsky.wvs.entity.VisitEntity;
import com.andlvovsky.wvs.service.VisitServiceLocal;
import com.andlvovsky.wvs.service.statistics.CountryStatisticsService;
import com.andlvovsky.wvs.service.statistics.DateTimeService;
import com.andlvovsky.wvs.service.statistics.impl.DefaultCountryStatisticsService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CountryStatisticsServiceUnitTest {
  private VisitServiceLocal visitServiceLocal = Mockito.mock(VisitServiceLocal.class);
  private DateTimeService dateTimeService =  Mockito.mock(DateTimeService.class);
  private CountryStatisticsService countryStatisticsService = new DefaultCountryStatisticsService(visitServiceLocal, dateTimeService);

  @Test
  public void shouldGetVisitsPerCountryForTheLastWeek() {
    // given
    List<VisitEntity> visitEntities = Arrays.asList(
        VisitEntity.builder().country("UA").build(),
        VisitEntity.builder().country("UA").build(),
        VisitEntity.builder().country("US").build()
    );
    when(visitServiceLocal.getVisits(any(), any()))
        .thenReturn(visitEntities);
    when(dateTimeService.getLastWeekInterval()).thenReturn(new DateTimeInterval(LocalDateTime.now(), LocalDateTime.now()));

    // when
    List<CountryVisitsDto> timeVisitsDtos = countryStatisticsService.getVisitsPerCountryForTheLastWeek(1L);

    // then
    assertThat(timeVisitsDtos).isEqualTo(Arrays.asList(
        new CountryVisitsDto("UA", 2),
        new CountryVisitsDto("US", 1)
    ));
  }

  @Test
  public void shouldGetVisitsPerCountryForTheLastMonth() {
    // given
    List<VisitEntity> visitEntities = Arrays.asList(
        VisitEntity.builder().country("UA").build(),
        VisitEntity.builder().country("UA").build(),
        VisitEntity.builder().country("US").build()
    );
    when(visitServiceLocal.getVisits(any(), any()))
        .thenReturn(visitEntities);
    when(dateTimeService.getLastMonthInterval()).thenReturn(new DateTimeInterval(LocalDateTime.now(), LocalDateTime.now()));

    // when
    List<CountryVisitsDto> timeVisitsDtos = countryStatisticsService.getVisitsPerCountryForTheLastMonth(1L);

    // then
    assertThat(timeVisitsDtos).isEqualTo(Arrays.asList(
        new CountryVisitsDto("UA", 2),
        new CountryVisitsDto("US", 1)
    ));
  }
}
