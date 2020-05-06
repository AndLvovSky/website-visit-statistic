package com.andlvovsky.wvs.service.statistics;

import com.andlvovsky.wvs.dto.statistics.DeviceVisitsDto;

import java.util.List;

public interface DeviceStatisticsService {
  List<DeviceVisitsDto> getVisitsPerDeviceForTheLastWeek(Long siteId);
}
