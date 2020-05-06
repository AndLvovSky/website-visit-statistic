package com.andlvovsky.wvs.statistics;

import com.andlvovsky.wvs.data.DateTimeInterval;
import com.andlvovsky.wvs.dto.statistics.DeviceVisitsDto;
import com.andlvovsky.wvs.entity.VisitEntity;
import com.andlvovsky.wvs.meta.Device;
import com.andlvovsky.wvs.service.VisitServiceLocal;
import com.andlvovsky.wvs.service.statistics.DateTimeService;
import com.andlvovsky.wvs.service.statistics.DeviceStatisticsService;
import com.andlvovsky.wvs.service.statistics.impl.DefaultDeviceStatisticsService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DeviceStatisticsUnitTest {
  private VisitServiceLocal visitServiceLocal = Mockito.mock(VisitServiceLocal.class);
  private DateTimeService dateTimeService =  Mockito.mock(DateTimeService.class);
  private DeviceStatisticsService deviceStatisticsService = new DefaultDeviceStatisticsService(visitServiceLocal, dateTimeService);

  @Test
  public void shouldGetVisitsPerDeviceForTheLastWeek() {
    // given
    List<VisitEntity> visitEntities = Arrays.asList(
        VisitEntity.builder().device(Device.PC).build(),
        VisitEntity.builder().device(Device.PC).build(),
        VisitEntity.builder().device(Device.MOBILE).build()
    );
    when(visitServiceLocal.getVisits(any(), any()))
        .thenReturn(visitEntities);
    when(dateTimeService.getLastWeekInterval()).thenReturn(new DateTimeInterval(LocalDateTime.now(), LocalDateTime.now()));

    // when
    List<DeviceVisitsDto> timeVisitsDtos = deviceStatisticsService.getVisitsPerDeviceForTheLastWeek(1L);

    // then
    assertThat(timeVisitsDtos).isEqualTo(Arrays.asList(
        new DeviceVisitsDto(Device.PC, 2),
        new DeviceVisitsDto(Device.MOBILE, 1)
    ));
  }
}
