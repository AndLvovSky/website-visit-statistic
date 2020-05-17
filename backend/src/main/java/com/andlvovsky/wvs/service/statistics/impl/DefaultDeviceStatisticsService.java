package com.andlvovsky.wvs.service.statistics.impl;

import com.andlvovsky.wvs.data.DateTimeInterval;
import com.andlvovsky.wvs.dto.statistics.DeviceVisitsDto;
import com.andlvovsky.wvs.entity.VisitEntity;
import com.andlvovsky.wvs.service.VisitServiceLocal;
import com.andlvovsky.wvs.service.statistics.DateTimeService;
import com.andlvovsky.wvs.service.statistics.DeviceStatisticsService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import lombok.RequiredArgsConstructor;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultDeviceStatisticsService implements DeviceStatisticsService {

  private final VisitServiceLocal visitServiceLocal;
  private final DateTimeService dateTimeService;

  @Override
  public List<DeviceVisitsDto> getVisitsPerDeviceForTheLastWeek(Long siteId) {
    DateTimeInterval lastWeekInterval = dateTimeService.getLastWeekInterval();
    List<VisitEntity> visitsForTheLastWeek = visitServiceLocal.getVisits(siteId, lastWeekInterval);
    return getDeviceVisits(visitsForTheLastWeek);
  }

  @Override
  public List<DeviceVisitsDto> getVisitsPerDeviceForTheLastMonth(Long siteId) {
    DateTimeInterval lastMonthInterval = dateTimeService.getLastMonthInterval();
    List<VisitEntity> visitsForTheLastMonth = visitServiceLocal.getVisits(siteId, lastMonthInterval);
    return getDeviceVisits(visitsForTheLastMonth);
  }

  private List<DeviceVisitsDto> getDeviceVisits(List<VisitEntity> visits) {
    return visits.stream()
        .collect(groupingBy(VisitEntity::getDevice, counting()))
        .entrySet().stream()
        .map(item -> new DeviceVisitsDto(item.getKey(), item.getValue().intValue()))
        .sorted(comparing(DeviceVisitsDto::getDevice))
        .collect(toList());
  }
}
