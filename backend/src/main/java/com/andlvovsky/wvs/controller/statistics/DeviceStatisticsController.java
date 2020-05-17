package com.andlvovsky.wvs.controller.statistics;

import com.andlvovsky.wvs.dto.statistics.DeviceVisitsDto;
import com.andlvovsky.wvs.meta.Endpoint;
import com.andlvovsky.wvs.service.statistics.DeviceStatisticsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoint.DEVICE_STATISTICS)
public class DeviceStatisticsController {

  private final DeviceStatisticsService deviceStatisticsService;

  @GetMapping("/visits/week/{siteId}")
  public List<DeviceVisitsDto> getVisitsPerDeviceForTheLastWeek(@PathVariable Long siteId) {
    return deviceStatisticsService.getVisitsPerDeviceForTheLastWeek(siteId);
  }

  @GetMapping("/visits/month/{siteId}")
  public List<DeviceVisitsDto> getVisitsPerDeviceForTheLastMonth(@PathVariable Long siteId) {
    return deviceStatisticsService.getVisitsPerDeviceForTheLastMonth(siteId);
  }
}
