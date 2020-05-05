package com.andlvovsky.wvs.controller.statistics;

import com.andlvovsky.wvs.dto.statistics.TimeVisitsDto;
import com.andlvovsky.wvs.meta.Endpoint;
import com.andlvovsky.wvs.service.statistics.TimeStatisticsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoint.TIME_STATISTICS)
public class TimeStatisticsController {

  private final TimeStatisticsService timeStatisticsService;

  @GetMapping("/visits-per-day-of-week/{siteId}")
  public List<TimeVisitsDto> getVisitsPerDayOfWeek(@PathVariable Long siteId) {
    return timeStatisticsService.getVisitsPerDayOfWeek(siteId);
  }
}